package com.theembers.iot.redis;

import com.theembers.iot.GlobalInfo;
import com.theembers.iot.IotInfo;
import com.theembers.iot.utils.JsonUtils;
import com.theembers.iot.utils.KeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.theembers.iot.GlobalInfo.Global_Iot_Redis_Key;

/**
 * 物联网 service
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-26 16:36
 */
@Service
public class IoTService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IoTService.class);

    @Autowired
    private RedisService redisService;

    /**
     * 加载 IoT 映射信息到 全局
     *
     * @param host
     * @param port
     * @return
     */
    public boolean loadIotMapper2Global(String host, Integer port) throws Exception {
        if (GlobalInfo.iotMapper == null) {
            Global_Iot_Redis_Key = KeyUtils.buildKey(host, port);
            GlobalInfo.iotMapper = this.getIoTSnIdMapper(Global_Iot_Redis_Key);
        }
        if (CollectionUtils.isEmpty(GlobalInfo.iotMapper)) {
            return false;
        }
        return true;
    }

    /**
     * 刷新 全局 IoT 映射信息
     *
     * @return
     * @throws Exception
     */
    public boolean refreshIotMapper2Global() throws Exception {
        GlobalInfo.iotMapper = this.getIoTSnIdMapper(Global_Iot_Redis_Key);
        if (CollectionUtils.isEmpty(GlobalInfo.iotMapper)) {
            return false;
        }
        return true;
    }

    /**
     * 获取 IoT 映射信息
     */
    private Map<String, IotInfo> getIoTSnIdMapper(String key) throws Exception {
        LOGGER.info("get IoT SnId Mapper... key is [{}]", key);
        Map<Object, Object> mapperMap = redisService.hGetAll(key);
        if (CollectionUtils.isEmpty(mapperMap)) {
            LOGGER.warn("the IoTSnIdMapper is empty!");
            return new HashMap<>(0);
        }

        Map<String, IotInfo> mapperStrMap = new HashMap<>(mapperMap.size());
        Set<Object> keySet = mapperMap.keySet();
        for (Object sn : keySet) {
            Object data = mapperMap.get(sn);
            IotInfo dataMap = JsonUtils.jsonStr2Obj(String.valueOf(data), IotInfo.class);
            mapperStrMap.put(String.valueOf(sn), dataMap);
        }
        return mapperStrMap;
    }
}
