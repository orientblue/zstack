package org.zstack.longjob;

import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.zstack.core.Platform;
import org.zstack.core.cloudbus.CloudBus;
import org.zstack.core.cloudbus.CloudBusCallBack;
import org.zstack.core.cloudbus.ResourceDestinationMaker;
import org.zstack.core.componentloader.PluginRegistry;
import org.zstack.core.config.GlobalConfigException;
import org.zstack.core.config.GlobalConfigValidatorExtensionPoint;
import org.zstack.core.db.*;
import org.zstack.core.defer.Defer;
import org.zstack.core.defer.Deferred;
import org.zstack.core.progress.ProgressReportService;
import org.zstack.core.thread.ChainTask;
import org.zstack.core.thread.SyncTaskChain;
import org.zstack.core.thread.ThreadFacade;
import org.zstack.core.timeout.ApiTimeoutExtensionPoint;
import org.zstack.core.timeout.ApiTimeoutManager;
import org.zstack.header.AbstractService;
import org.zstack.header.Constants;
import org.zstack.header.core.AsyncBackup;
import org.zstack.header.core.Completion;
import org.zstack.header.core.ReturnValueCompletion;
import org.zstack.header.errorcode.ErrorCode;
import org.zstack.header.exception.CloudRuntimeException;
import org.zstack.header.identity.APIDeleteAccountEvent;
import org.zstack.header.longjob.*;
import org.zstack.header.managementnode.ManagementNodeChangeListener;
import org.zstack.header.managementnode.ManagementNodeInventory;
import org.zstack.header.managementnode.ManagementNodeReadyExtensionPoint;
import org.zstack.header.message.APIEvent;
import org.zstack.header.message.APIMessage;
import org.zstack.header.message.Message;
import org.zstack.header.message.MessageReply;
import org.zstack.tag.TagManager;
import org.zstack.utils.BeanUtils;
import org.zstack.utils.ThreadContextUtils;
import org.zstack.utils.Utils;
import org.zstack.utils.logging.CLogger;

import java.sql.SQLNonTransientConnectionException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.zstack.core.db.DBSourceUtils.isDBConnected;
import static org.zstack.core.db.DBSourceUtils.waitDBConnected;
import static org.zstack.core.progress.ProgressReportService.reportProgress;
import static org.zstack.header.longjob.LongJobConstants.LongJobOperation;
import static org.zstack.longjob.LongJobUtils.*;

/**
 * Created by GuoYi on 11/14/17.
 */
public class LongJobManagerImpl extends AbstractService implements LongJobManager, ManagementNodeReadyExtensionPoint
        , ManagementNodeChangeListener, ApiTimeoutExtensionPoint {
    private static final CLogger logger = Utils.getLogger(LongJobManagerImpl.class);

    @Autowired
    private CloudBus bus;
    @Autowired
    private DatabaseFacade dbf;
    @Autowired
    private ThreadFacade thdf;
    @Autowired
    private TagManager tagMgr;
    @Autowired
    private ProgressReportService progRpt;
    @Autowired
    protected ApiTimeoutManager timeoutMgr;
    @Autowired
    private PluginRegistry pluginRgty;
    private List<LongJobExtensionPoint> exts = new ArrayList<>();
    @Autowired
    private transient ResourceDestinationMaker destinationMaker;

    // we need a longjob factory to produce LongJob based on JobName
    @Autowired
    private LongJobFactory longJobFactory;

    private List<String> longJobClasses = new ArrayList<String>();
    private Map<String, Class<? extends APIMessage>> useApiTimeout = new HashMap<>();
    private Map<String, Function<APIEvent, Void>> longJobCallBacks = new ConcurrentHashMap<>();

    private void collectLongJobs() {
        Set<Class<?>> subs = BeanUtils.reflections.getTypesAnnotatedWith(LongJobFor.class);
        for (Class sub : subs) {
            UseApiTimeout timeout = (UseApiTimeout) sub.getAnnotation(UseApiTimeout.class);
            if (timeout != null) {
                useApiTimeout.put(sub.toString(), timeout.value());
            }

            longJobClasses.add(sub.toString());
        }
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg instanceof APIMessage) {
            handleApiMessage((APIMessage) msg);
        } else {
            handleLocalMessage(msg);
        }
    }

    private void handleApiMessage(APIMessage msg) {
        if (msg instanceof APISubmitLongJobMsg) {
            handle((APISubmitLongJobMsg) msg);
        } else if (msg instanceof APICancelLongJobMsg) {
            handle((APICancelLongJobMsg) msg);
        } else if (msg instanceof APIDeleteLongJobMsg) {
            handle((APIDeleteLongJobMsg) msg);
        } else if (msg instanceof APIRerunLongJobMsg) {
            handle((APIRerunLongJobMsg) msg);
        } else {
            bus.dealWithUnknownMessage(msg);
        }
    }

    private void handleLocalMessage(Message msg) {
        if (msg instanceof SubmitLongJobMsg) {
            handle((SubmitLongJobMsg) msg);
        } else if (msg instanceof CancelLongJobMsg) {
            handle((CancelLongJobMsg) msg);
        } else {
            bus.dealWithUnknownMessage(msg);
        }
    }

    private void handle(APIRerunLongJobMsg msg) {
        APIRerunLongJobEvent evt = new APIRerunLongJobEvent(msg.getId());
        SubmitLongJobMsg smsg = new SubmitLongJobMsg();
        LongJobVO job = dbf.findByUuid(msg.getUuid(), LongJobVO.class);
        smsg.setJobUuid(job.getUuid());
        smsg.setDescription(job.getDescription());
        smsg.setJobData(job.getJobData());
        smsg.setJobName(job.getJobName());
        smsg.setName(job.getName());
        smsg.setTargetResourceUuid(job.getTargetResourceUuid());
        smsg.setResourceUuid(job.getUuid());
        smsg.setSystemTags(msg.getSystemTags());
        smsg.setUserTags(msg.getUserTags());
        smsg.setAccountUuid(msg.getSession().getAccountUuid());
        bus.makeLocalServiceId(smsg, LongJobConstants.SERVICE_ID);
        bus.send(smsg, new CloudBusCallBack(msg) {
            @Override
            public void run(MessageReply rly) {
                SubmitLongJobReply reply = rly.castReply();
                evt.setInventory(reply.getInventory());
                bus.publish(evt);
            }
        });
    }

    private void handle(APIDeleteLongJobMsg msg) {
        thdf.chainSubmit(new ChainTask(msg) {
            @Override
            public String getSyncSignature() {
                return "longjob-" + msg.getUuid();
            }

            @Override
            public void run(SyncTaskChain chain) {
                final APIDeleteAccountEvent evt = new APIDeleteAccountEvent(msg.getId());
                LongJobVO vo = dbf.findByUuid(msg.getUuid(), LongJobVO.class);
                dbf.remove(vo);
                logger.info(String.format("longjob [uuid:%s, name:%s] has been deleted", vo.getUuid(), vo.getName()));
                bus.publish(evt);

                chain.next();
            }

            @Override
            public String getName() {
                return getSyncSignature();
            }
        });
    }

    private void handle(APICancelLongJobMsg msg) {
        thdf.chainSubmit(new ChainTask(msg) {
            @Override
            public String getSyncSignature() {
                return "longjob-" + msg.getUuid();
            }

            @Override
            public void run(SyncTaskChain chain) {
                final APICancelLongJobEvent evt = new APICancelLongJobEvent(msg.getId());
                cancelLongJob(msg.getUuid(), new Completion(chain) {
                    @Override
                    public void success() {
                        bus.publish(evt);
                        chain.next();
                    }

                    @Override
                    public void fail(ErrorCode errorCode) {
                        evt.setError(errorCode);
                        bus.publish(evt);
                        chain.next();
                    }
                });
                chain.next();
            }

            @Override
            public String getName() {
                return getSyncSignature();
            }
        });
    }

    private void handle(CancelLongJobMsg msg) {
        thdf.chainSubmit(new ChainTask(msg) {
            @Override
            public String getSyncSignature() {
                return "longjob-" + msg.getUuid();
            }

            @Override
            public void run(SyncTaskChain chain) {
                final CancelLongJobReply reply = new CancelLongJobReply();
                cancelLongJob(msg.getUuid(), new Completion(chain) {
                    @Override
                    public void success() {
                        bus.reply(msg, reply);
                        chain.next();
                    }

                    @Override
                    public void fail(ErrorCode errorCode) {
                        reply.setError(errorCode);
                        bus.reply(msg, reply);
                        chain.next();
                    }
                });
            }

            @Override
            public String getName() {
                return getSyncSignature();
            }
        });
    }

    private void cancelLongJob(String uuid, Completion completion) {
        if (Q.New(LongJobVO.class).eq(LongJobVO_.uuid, uuid).select(LongJobVO_.state).findValue() == LongJobState.Canceled) {
            logger.info(String.format("longjob [uuid:%s] has been canceled before", uuid));
            completion.success();
            return;
        }

        LongJobVO vo = changeState(uuid, LongJobStateEvent.canceling);
        LongJob job = longJobFactory.getLongJob(vo.getJobName());
        logger.info(String.format("longjob [uuid:%s, name:%s] has been marked canceling", vo.getUuid(), vo.getName()));

        job.cancel(vo, new ReturnValueCompletion<Boolean>(completion) {
            @Override
            public void success(Boolean cancelled) {
                if (cancelled) {
                    changeState(uuid, LongJobStateEvent.canceled);
                    logger.info(String.format("longjob [uuid:%s, name:%s] has been canceled", vo.getUuid(), vo.getName()));
                } else {
                    logger.debug(String.format("wait for canceling longjob [uuid:%s, name:%s] rollback", vo.getUuid(), vo.getName()));
                }
                completion.success();
            }

            @Override
            public void fail(ErrorCode errorCode) {
                logger.error(String.format("failed to cancel longjob [uuid:%s, name:%s]", vo.getUuid(), vo.getName()));
                completion.fail(errorCode);
            }
        });
    }

    private void handle(APISubmitLongJobMsg msg) {
        APISubmitLongJobEvent evt = new APISubmitLongJobEvent(msg.getId());
        SubmitLongJobMsg smsg = SubmitLongJobMsg.valueOf(msg);
        bus.makeLocalServiceId(smsg, LongJobConstants.SERVICE_ID);
        bus.send(smsg, new CloudBusCallBack(msg) {
            @Override
            public void run(MessageReply rly) {
                SubmitLongJobReply reply = rly.castReply();
                evt.setInventory(reply.getInventory());
                evt.setNeedAudit(reply.isNeedAudit());
                bus.publish(evt);
            }
        });
    }

    private void handle(SubmitLongJobMsg msg) {
        // create new LongJobVO or get old LongJobVO
        LongJobVO vo = null;
        if (msg.getResourceUuid() != null) {
            vo = dbf.findByUuid(msg.getResourceUuid(), LongJobVO.class);
        }

        if (vo != null) {
            vo.setApiId(ThreadContext.getImmutableContext().get(Constants.THREAD_CONTEXT_API));
            vo.setState(LongJobState.Waiting);
            vo.setExecuteTime(null);
            vo.setJobResult(null);
            vo.setManagementNodeUuid(Platform.getManagementServerId());
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            vo.setCreateDate(now);
            vo.setLastOpDate(now);
            vo = dbf.updateAndRefresh(vo);
            logger.info(String.format("longjob [uuid:%s, name:%s] has been re-submitted", vo.getUuid(), vo.getName()));
        } else {
            vo = new LongJobVO();
            if (msg.getResourceUuid() != null) {
                vo.setUuid(msg.getResourceUuid());
            } else {
                vo.setUuid(Platform.getUuid());
            }
            if (msg.getName() != null) {
                vo.setName(msg.getName());
            } else {
                vo.setName(msg.getJobName());
            }

            String apiId = ThreadContext.getImmutableContext().get(Constants.THREAD_CONTEXT_API) != null ?
                    ThreadContext.getImmutableContext().get(Constants.THREAD_CONTEXT_API) : msg.getJobRequestUuid();
            vo.setDescription(msg.getDescription());
            vo.setApiId(apiId);
            vo.setJobName(msg.getJobName());
            vo.setJobData(msg.getJobData());
            vo.setState(LongJobState.Waiting);
            vo.setTargetResourceUuid(msg.getTargetResourceUuid());
            vo.setManagementNodeUuid(Platform.getManagementServerId());
            vo.setAccountUuid(msg.getAccountUuid());
            vo = dbf.persistAndRefresh(vo);
            msg.setJobUuid(vo.getUuid());
            tagMgr.createTags(msg.getSystemTags(), msg.getUserTags(), vo.getUuid(), LongJobVO.class.getSimpleName());
            logger.info(String.format("new longjob [uuid:%s, name:%s] has been created", vo.getUuid(), vo.getName()));
        }

        // wait in line
        thdf.chainSubmit(new ChainTask(msg) {
            @Override
            public String getSyncSignature() {
                return "longjob-" + msg.getJobUuid();
            }

            @Override
            public void run(SyncTaskChain chain) {
                SubmitLongJobReply reply = new SubmitLongJobReply();
                LongJobVO vo = changeState(msg.getJobUuid(), LongJobStateEvent.start);
                // launch the long job right now
                ThreadContext.put(Constants.THREAD_CONTEXT_API, vo.getApiId());
                LongJob job = longJobFactory.getLongJob(vo.getJobName());
                ThreadContext.put(Constants.THREAD_CONTEXT_TASK_NAME, job.getClass().toString());
                doStartJob(job, vo, msg);

                reply.setInventory(LongJobInventory.valueOf(vo));
                if (job.getAuditType() != null) {
                    reply.setNeedAudit(true);
                }
                logger.info(String.format("longjob [uuid:%s, name:%s] has been started", vo.getUuid(), vo.getName()));
                bus.reply(msg, reply);

                chain.next();
            }

            @Override
            public String getName() {
                return getSyncSignature();
            }
        });
    }

    private void doStartJob(LongJob job, LongJobVO vo, AsyncBackup async) {
        String longJobUuid = vo.getUuid();
        job.start(vo, new ReturnValueCompletion<APIEvent>(async) {
            @Override
            public void success(APIEvent evt) {
                reportProgress("100");
                changeState(longJobUuid, LongJobStateEvent.succeed, it -> {
                    if (Strings.isEmpty(it.getJobResult())) {
                        it.setJobResult("Succeeded");
                    }
                });

                if (evt != null) {
                    exts.forEach(ext -> ext.afterJobFinished(job, vo, evt));
                    Optional.ofNullable(longJobCallBacks.remove(vo.getApiId())).ifPresent(it -> it.apply(evt));
                }

                logger.info(String.format("successfully run longjob [uuid:%s, name:%s]", vo.getUuid(), vo.getName()));
            }

            @Override
            public void fail(ErrorCode errorCode) {
                LongJobVO vo = changeState(longJobUuid, getEventOnError(errorCode), it -> {
                    if (Strings.isEmpty(it.getJobResult())) {
                        it.setJobResult("Failed : " + wrapErrorCode(it, errorCode).toString());
                    }
                });

                APIEvent evt = new APIEvent(ThreadContext.get(Constants.THREAD_CONTEXT_API));
                evt.setError(errorCode);

                exts.forEach(ext -> ext.afterJobFailed(job, vo, evt));
                Optional.ofNullable(longJobCallBacks.remove(vo.getApiId())).ifPresent(it -> it.apply(evt));

                logger.info(String.format("failed to run longjob [uuid:%s, name:%s]", vo.getUuid(), vo.getName()));
            }

            private ErrorCode wrapErrorCode(LongJobVO job, ErrorCode err) {
                if (Arrays.asList(LongJobState.Canceling, LongJobState.Canceled).contains(vo.getState()) && !err.isError(LongJobErrors.CANCELED)) {
                    return cancelErr(job.getUuid(), err);
                } else {
                    return err;
                }
            }
        });
    }

    @Override
    public void submitLongJob(SubmitLongJobMsg msg, CloudBusCallBack submitCallBack, Function<APIEvent, Void> jobCallBack) {
        String apiId;
        if (msg.getId() == null) {
            apiId = Platform.getUuid();
        } else {
            apiId = msg.getId();
        }

        String originApiId = ThreadContext.get(Constants.THREAD_CONTEXT_API);
        ThreadContext.put(Constants.THREAD_CONTEXT_API, apiId);
        longJobCallBacks.put(apiId, jobCallBack);
        bus.makeLocalServiceId(msg, LongJobConstants.SERVICE_ID);
        bus.send(msg, new CloudBusCallBack(submitCallBack) {
            @Override
            public void run(MessageReply reply) {
                if (!reply.isSuccess()) {
                    longJobCallBacks.remove(apiId);
                }

                if (submitCallBack != null) {
                    submitCallBack.run(reply);
                }

                if (originApiId == null) {
                    ThreadContext.remove(Constants.THREAD_CONTEXT_API);
                } else {
                    ThreadContext.put(Constants.THREAD_CONTEXT_API, originApiId);
                }
            }
        });
    }

    @Override
    public String getId() {
        return bus.makeLocalServiceId(LongJobConstants.SERVICE_ID);
    }

    @Override
    public boolean start() {
        collectLongJobs();

        LongJobGlobalConfig.LONG_JOB_DEFAULT_TIMEOUT.installValidateExtension(new GlobalConfigValidatorExtensionPoint() {
            @Override
            public void validateGlobalConfig(String category, String name, String oldValue, String newValue) throws GlobalConfigException {
                Long v = Long.valueOf(newValue);
                if (v < 10800) {
                    throw new GlobalConfigException("long job timeout must be larger than 10800s");
                }
            }
        });

        dbf.installEntityLifeCycleCallback(LongJobVO.class, EntityEvent.PRE_UPDATE, (evt, o) -> {
            LongJobVO job = (LongJobVO) o;
            if (job.getExecuteTime() == null && jobCompleted(job)) {
                long time = (System.currentTimeMillis() - job.getCreateDate().getTime()) / 1000;
                job.setExecuteTime(time);
                logger.info(String.format("longjob [uuid:%s] set execute time:%d", job.getUuid(), time));
            }
        });

        populateExtensions();

        return true;
    }

    private void populateExtensions() {
        exts = pluginRgty.getExtensionList(LongJobExtensionPoint.class);
    }

    @Override
    public boolean stop() {
        return true;
    }

    @Override
    public void nodeJoin(ManagementNodeInventory inv) {

    }

    @Override
    public void nodeLeft(ManagementNodeInventory inv) {
        logger.debug(String.format("Management node[uuid:%s] left, node[uuid:%s] starts to take over longjobs", inv.getUuid(), Platform.getManagementServerId()));
        takeOverLongJob();
    }

    @Override
    public void iAmDead(ManagementNodeInventory inv) {

    }

    @Override
    public void iJoin(ManagementNodeInventory inv) {
    }

    private void takeOverLongJob() {
        logger.debug("Starting to take over long jobs");
        final int group = 1000;
        long amount = dbf.count(LongJobVO.class);
        int times = (int) ((amount + group - 1)/group);
        int start = 0;
        for (int i = 0; i < times; i++) {
            List<String> uuids = Q.New(LongJobVO.class)
                    .select(LongJobVO_.uuid)
                    .isNull(LongJobVO_.managementNodeUuid)
                    .limit(group).start(start).listValues();
            for (String uuid : uuids) {
                if (destinationMaker.isManagedByUs(uuid)) {
                    retryTakeOverLongJob(uuid);
                }
            }
            start += group;
        }
    }

    private void retryTakeOverLongJob(String uuid) {
        LongJobOperation operation = null;
        try {
            LongJobVO vo = updateByUuid(uuid, it -> it.setManagementNodeUuid(Platform.getManagementServerId()));
            operation = getLoadOperation(vo);
            doLoadLongJob(vo, operation);
        } catch (Throwable t) {
            if (!(t instanceof SQLNonTransientConnectionException) && isDBConnected()) {
                throw t;
            }

            if (!waitDBConnected(5, 5)) {
                throw t;
            }

            LongJobVO vo = updateByUuid(uuid, it -> it.setManagementNodeUuid(Platform.getManagementServerId()));
            doLoadLongJob(vo, operation);
        }
    }

    @Override
    public void loadLongJob() {
        List<LongJobVO> managedByUsJobs = new SQLBatchWithReturn< List<LongJobVO>>() {
            @Override
            protected List<LongJobVO> scripts() {
                List<LongJobVO> vos = Q.New(LongJobVO.class).isNull(LongJobVO_.managementNodeUuid).list();
                vos.removeIf(it -> !destinationMaker.isManagedByUs(it.getUuid()));
                vos.forEach(it -> {
                    it.setManagementNodeUuid(Platform.getManagementServerId());
                    merge(it);
                });

                return vos;
            }
        }.execute();

        managedByUsJobs.forEach(this::doLoadLongJob);
    }

    private void doLoadLongJob(LongJobVO vo) {
        doLoadLongJob(vo, null);
    }

    @Deferred
    private void doLoadLongJob(LongJobVO vo, LongJobOperation operation) {
        if (operation == null) {
            operation = getLoadOperation(vo);
        }

        Runnable cleanup = ThreadContextUtils.saveThreadContext();
        Defer.defer(cleanup);
        // launch the waiting jobs
        ThreadContext.put(Constants.THREAD_CONTEXT_API, vo.getApiId());
        LongJob job = longJobFactory.getLongJob(vo.getJobName());
        ThreadContext.put(Constants.THREAD_CONTEXT_TASK_NAME, job.getClass().toString());

        if (operation == LongJobOperation.Start) {
            LongJobUtils.changeState(vo.getUuid(), LongJobStateEvent.start);
            doStartJob(job, vo, null);
        } else if (operation == LongJobOperation.Resume) {
            logger.info(String.format("start to resume longjob [uuid:%s, name:%s]", vo.getUuid(), vo.getName()));
            job.resume(vo);
            dbf.update(vo);
        } else if (operation == LongJobOperation.Cancel) {
            LongJobUtils.changeState(vo.getUuid(), LongJobStateEvent.canceled);
        }
    }

    private LongJobOperation getLoadOperation(LongJobVO vo) {
        if (vo.getState() == LongJobState.Waiting) {
            return LongJobOperation.Start;
        } else if (vo.getState() == LongJobState.Running || vo.getState() == LongJobState.Suspended) {
            return LongJobOperation.Resume;
        } else if (vo.getState() == LongJobState.Canceling) {
            return LongJobOperation.Cancel;
        }
        return null;
    }

    @Override
    public void managementNodeReady() {
        logger.debug(String.format("Management node[uuid:%s] is ready, starts to load longjobs", Platform.getManagementServerId()));
        loadLongJob();
    }

    @Override
    public Long getApiTimeout() {
        String type = ThreadContext.get(Constants.THREAD_CONTEXT_TASK_NAME);
        if (type != null && longJobClasses.contains(type)) {
            Class<? extends APIMessage> batchJobFor = useApiTimeout.get(type);
            if (batchJobFor != null) {
                return getMessageTimeout(batchJobFor);
            }

            // default input unit is second should be changed to millis
            return TimeUnit.SECONDS.toMillis(LongJobGlobalConfig.LONG_JOB_DEFAULT_TIMEOUT.value(Long.class));
        }

        return null;
    }

    private long getMessageTimeout(Class<? extends APIMessage> clz) {
        try {
            return timeoutMgr.getMessageTimeout(clz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CloudRuntimeException(e);
        }
    }
}
