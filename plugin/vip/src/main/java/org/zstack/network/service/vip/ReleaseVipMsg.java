package org.zstack.network.service.vip;

import org.zstack.header.message.NeedReplyMessage;

/**
 * Created by xing5 on 2016/11/20.
 */
public class ReleaseVipMsg extends NeedReplyMessage implements VipMessage {
    private String vipUuid;

    @Override
    public String getVipUuid() {
        return vipUuid;
    }
}
