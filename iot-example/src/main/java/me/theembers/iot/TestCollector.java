package me.theembers.iot;

import com.alibaba.fastjson.JSON;
import com.theembers.iot.router.Router;
import me.theembers.iot.bean.IotMsgData;
import me.theembers.iot.bean.TestIotData;
import me.theembers.iot.rule.CacheRule;
import me.theembers.iot.shadow.Device;
import me.theembers.iot.shadow.IotShadow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-15 13:59
 */
public class TestCollector {


    private Router router = new TestRouter();

    public void execute() {
        try {
            IotMsgData iotData = buildIotMsg();
            IotShadow shadow = iotData.getShadow();
            router.getSelector().selectRoute(shadow.getRule()).run(shadow, iotData);
        } catch (Exception e) {
            throw e;
        }
    }


    public static void main(String[] args) {
        TestCollector collector = new TestCollector();
        collector.execute();
    }


    private IotMsgData buildIotMsg() {
// ==================================================
        // 构建 影子
        CacheRule rule = new CacheRule();
        String[] pArr = {"TestProcessor", "Test2Processor"};
        String[] pArr2 = {"Test2Processor", "Test2Processor"};
        Map<String, String[]> keys = new HashMap<>();
        keys.put("t1", pArr);
        keys.put("t2", pArr2);
        rule.setKeys(keys);


        IotShadow shadow = new IotShadow();
        Device device = new Device();
        device.setRule(rule);
        shadow.setDeviceShadow(device);

// =======================================================
        TestIotData testIotData = new TestIotData();
        testIotData.setSn("sn12355211");
        testIotData.setName("设备001");


        IotMsgData iotData = new IotMsgData();
        iotData.setDataJson(JSON.toJSONString(testIotData));
        iotData.setShadow(shadow);

        return iotData;
    }


}
