package com.theembers.iot.demo.bizprocessor;

import com.yunding.common.utils.JacksonUtil;
import com.theembers.iot.collector.SourceData;
import com.theembers.iot.demo.bean.TestAppData;
import com.theembers.iot.demo.bean.TestIotData;
import com.theembers.iot.demo.shadow.IotShadow;
import com.theembers.iot.processor.AbstractProcessor;
import com.theembers.iot.processor.Input;
import com.theembers.iot.processor.Output;
import com.theembers.iot.processor.SlotData;

import java.util.Date;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-14 15:01
 */
public class TestProcessor extends AbstractProcessor<IotShadow, TestIotData, TestAppData> {


    @Override
    public Input<TestIotData> beforeTransform(IotShadow shadow, SourceData srcData) throws Exception {
        System.out.println(Thread.currentThread().getName() + " beforeTransform >> " + srcData.getData());
        return JacksonUtil.fromString((String) srcData.getData(), TestIotData.class);
    }

    @Override
    public Output<TestAppData> transform(IotShadow shadow, Input<TestIotData> input) throws Exception {
        System.out.println(Thread.currentThread().getName() + " transform >> ...");
        TestIotData data = input.get();
        TestAppData appData = new TestAppData();
        appData.setSn(data.getSn() + "_" + new Date().getTime());
        return appData;
    }

    @Override
    public Output<TestAppData> afterTransform(IotShadow shadow, TestAppData output) throws Exception {
        System.out.println(Thread.currentThread().getName() + " afterTransform << " + output.getSn());
        return null;
    }

    @Override
    public void except(Exception e, String... msg) {
        System.out.println(e);
    }

    @Override
    public Input<TestIotData> relay(IotShadow shadow, SlotData slotData) {
        System.out.println(Thread.currentThread().getName() + " relay >> ");
        if (slotData instanceof TestAppData) {
            TestAppData testAppData = (TestAppData) slotData.get();
            TestIotData iotData = new TestIotData();

            iotData.setSn(testAppData.getSn());
            return iotData;
        }
        except(new Exception("bad data type."));
        return null;
    }

    @Override
    public SlotData passOn(IotShadow shadow, Output<TestAppData> output) {
        System.out.println(Thread.currentThread().getName() + " passOn <<======");
        return output.get();
    }
}
