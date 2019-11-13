package com.theembers.iot.router;

/**
 * 抽象 导航
 *
 * @author TheEmbers Guo
 * createTime 2019-11-13 14:53
 */
public abstract class AbstractRoute<P, R extends Rule> implements Route<R> {
    protected R rule;
    protected P processors;

    protected AbstractRoute(R rule) {
        this.rule = rule;
    }

    public R getRule() {
        return rule;
    }

    public P getProcessors() {
        return processors;
    }
}
