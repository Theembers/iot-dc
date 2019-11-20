package com.theembers.iot.router.selector;


import com.theembers.iot.router.Router;
import com.theembers.iot.router.route.Route;
import com.theembers.iot.router.rule.Rule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-13 10:44
 */
public abstract class AbstractSelector implements Selector {
    protected Router router;


    protected AbstractSelector(Router router) {
        this.router = router;
    }

    @Override
    public Route selectRoute(Route route, Rule rule) {
        return route.buildRoute(router, rule);
    }
}
