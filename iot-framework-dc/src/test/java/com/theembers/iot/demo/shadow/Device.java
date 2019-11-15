package com.theembers.iot.demo.shadow;

import com.theembers.iot.shadow.DeviceShadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-15 15:20
 */
public class Device extends DeviceShadow<Device, Product> {
    private String sn;
    private Long time;

    public String getSn() {
        return sn;
    }

    public Device setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public Device setTime(Long time) {
        this.time = time;
        return this;
    }
}
