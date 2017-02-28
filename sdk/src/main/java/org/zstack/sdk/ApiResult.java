package org.zstack.sdk;

import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;

/**
 * Created by xing5 on 2016/12/9.
 */
public class ApiResult {
    ErrorCode error;
    private String resultString;

    public ErrorCode getError() {
        return error;
    }

    void setError(ErrorCode error) {
        this.error = error;
    }

    void setResultString(String resultString) {
        this.resultString = resultString;
    }

    <T> T getResult(Class<T> clz) {
        if (resultString == null || resultString.isEmpty()) {
            return null;
        }

        Map m = ZSClient.gson.fromJson(resultString, LinkedHashMap.class);
        T ret = ZSClient.gson.fromJson(resultString, clz);
        if (!m.containsKey("schema")) {
            return ret;
        }

        Map schema = (Map) m.get("schema");
        try {
            List<String> paths = new ArrayList();
            paths.addAll(schema.keySet());
            Collections.sort(paths);

            for (String path : paths) {
                String src = (String) schema.get(path);
                String dst = SourceClassMap.srcToDstMapping.get(src);

                if (dst == null) {
                    //TODO: warning
                    continue;
                }

                Object bean = PropertyUtils.getProperty(ret, path);
                if (bean.getClass().getName().equals(dst)) {
                    // not an inherent object
                    continue;
                }

                Class dstClz = Class.forName(dst);

                Object source;
                if (path.contains("[")) {
                    // there is a list in the path,
                    // to get list in a map, we must use the path like
                    // (inventories)[0]
                    String[] pps = path.split("\\.");
                    List<String> lst = new ArrayList<>(pps.length);
                    for (String pp : pps) {
                        if (!pp.contains("[")) {
                            lst.add(pp);
                            continue;
                        }

                        String[] word = pp.split("\\[");
                        lst.add(String.format("(%s)[%s", word[0], word[1]));
                    }

                    String nPath = ZSClient.join(lst, ".");

                    source = PropertyUtils.getProperty(m, nPath);
                } else {
                    source = PropertyUtils.getProperty(m, path);
                }

                Object dstBean = ZSClient.gson.fromJson(ZSClient.gson.toJson(source), dstClz);
                PropertyUtils.setProperty(ret, path, dstBean);
            }

            return ret;
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }
}
