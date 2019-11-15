package com.theembers.iot.shadow;


import com.theembers.iot.router.rule.Rule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 15:32
 */
public abstract class DeviceShadow<D, P> implements Shadow {
    private Rule rule;
    private D device;
    private ProductShadow<P> productShadow;

    public Rule getRule() {
        if (null == rule) {
            return productShadow.getRule();
        }
        return rule;
    }

    public DeviceShadow<D, P> setRule(Rule rule) {
        this.rule = rule;
        return this;
    }

    public D getDevice() {
        return device;
    }

    public DeviceShadow<D, P> setDevice(D device) {
        this.device = device;
        return this;
    }

    public ProductShadow<P> getProductShadow() {
        return productShadow;
    }

    public DeviceShadow<D, P> setProductShadow(ProductShadow<P> productShadow) {
        this.productShadow = productShadow;
        return this;
    }
}
