package com.theembers.iot.demo.bean;

import com.theembers.iot.processor.InputData;

import java.util.List;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-14 14:59
 */
public class TestIotData extends InputData<TestIotData> {
    private String sn;
    private String name;
    private List<String> list;

    public String getSn() {
        return sn;
    }

    public TestIotData setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestIotData setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getList() {
        return list;
    }

    public TestIotData setList(List<String> list) {
        this.list = list;
        return this;
    }

    @Override
    public TestIotData get() {
        return this;
    }
}
