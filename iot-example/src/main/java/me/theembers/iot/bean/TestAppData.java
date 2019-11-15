package me.theembers.iot.bean;

import com.theembers.iot.processor.OutputData;
import com.theembers.iot.processor.SlotData;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-14 15:02
 */
public class TestAppData extends OutputData<TestAppData> implements SlotData<TestAppData> {
    private String sn;

    public String getSn() {
        return sn;
    }

    public TestAppData setSn(String sn) {
        this.sn = sn;
        return this;
    }

    @Override
    public TestAppData get() {
        return this;
    }
}
