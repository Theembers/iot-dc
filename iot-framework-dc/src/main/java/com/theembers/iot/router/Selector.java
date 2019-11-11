package com.theembers.iot.router;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:14
 */
public interface Selector<R extends Route> {
    void setRouter(Router router);

    R getRoute(Rule rule);
}
