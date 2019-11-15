package com.theembers.iot.shadow;


import com.theembers.iot.router.rule.Rule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 15:33
 */
public abstract class DeviceShadowSnapshot<D, P> implements Shadow {
    private Rule rule;
    private DeviceShadow<D, P> deviceShadow;

    public Rule getRule() {
        if (null == rule) {
            return deviceShadow.getRule();
        }
        return rule;
    }

    public DeviceShadowSnapshot<D, P> setRule(Rule rule) {
        this.rule = rule;
        return this;
    }

    public DeviceShadow<D, P> getDeviceShadow() {
        return deviceShadow;
    }

    public DeviceShadowSnapshot<D, P> setDeviceShadow(DeviceShadow<D, P> deviceShadow) {
        this.deviceShadow = deviceShadow;
        return this;
    }
}
