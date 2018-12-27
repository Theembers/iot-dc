package com.theembers.iot.utils;

/**
 * key 工具类
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-26 17:01
 */
public class KeyUtils {
    public static String buildKey(String ip, Integer port) {
        return buildKey("iot-ms:collect_model_mapping", ip, port);
    }

    public static String buildKey(String platformName, String ip, Integer port) {
        return platformName + ":" + ip + ":" + port;
    }
}
