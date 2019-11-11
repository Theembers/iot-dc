package com.theembers.iot.router;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:14
 */
public interface Selector {
    void setRouter(Router router);

    Route getRoute(Rule rule);
}
