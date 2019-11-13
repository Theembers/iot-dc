package com.theembers.iot.shadow;


import com.yunding.iot.framework.router.Rule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 15:33
 */
public class DeviceShadowSnapshot<D, P, S> implements Shadow {
    private Rule rule;
    private S data;
    private DeviceShadow<D, P> deviceShadow;

    public Rule getRule() {
        if (null == rule) {
            return deviceShadow.getRule();
        }
        return rule;
    }

    public DeviceShadowSnapshot<D, P, S> setRule(Rule rule) {
        this.rule = rule;
        return this;
    }

    public S getData() {
        return data;
    }

    public DeviceShadowSnapshot<D, P, S> setData(S data) {
        this.data = data;
        return this;
    }

    public DeviceShadow<D, P> getDeviceShadow() {
        return deviceShadow;
    }

    public DeviceShadowSnapshot<D, P, S> setDeviceShadow(DeviceShadow<D, P> deviceShadow) {
        this.deviceShadow = deviceShadow;
        return this;
    }
}
