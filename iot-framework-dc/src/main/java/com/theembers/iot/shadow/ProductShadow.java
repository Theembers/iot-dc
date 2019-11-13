package com.theembers.iot.shadow;


import com.theembers.iot.router.Rule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 15:25
 */
public class ProductShadow<P> implements Shadow {
    private Rule rule;
    private P product;

    public Rule getRule() {
        return rule;
    }

    public ProductShadow<P> setRule(Rule rule) {
        this.rule = rule;
        return this;
    }

    public P getProduct() {
        return product;
    }

    public ProductShadow<P> setProduct(P product) {
        this.product = product;
        return this;
    }
}
