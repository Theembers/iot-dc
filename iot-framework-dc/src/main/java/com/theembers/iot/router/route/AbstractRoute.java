package com.theembers.iot.router.route;


import com.theembers.iot.router.Router;
import com.theembers.iot.router.rule.Rule;

/**
 * 抽象 导航
 *
 * @author TheEmbers Guo
 * createTime 2019-11-13 14:53
 */
public abstract class AbstractRoute<T, R extends Rule> implements Route<T, R> {
    protected R rule;
    protected T dispatchers;


    public R getRule() {
        return rule;
    }

    public T getDispatchers() {
        return dispatchers;
    }

    @Override
    public Route buildRoute(Router router, R rule) {
        this.rule = rule;
        buildDispatcher(router, rule, this.dispatchers);
        return this;
    }

    abstract void buildDispatcher(Router router, R rule, T dispatchers);
}
