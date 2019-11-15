package com.theembers.iot.shadow;


import com.theembers.iot.router.rule.Rule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 15:25
 */
public abstract class ProductShadow<P> implements Shadow {
    private Rule rule;
    private P product;
    private String[] topics;

    public String[] getTopics() {
        return topics;
    }

    public ProductShadow<P> setTopics(String[] topics) {
        this.topics = topics;
        return this;
    }

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
