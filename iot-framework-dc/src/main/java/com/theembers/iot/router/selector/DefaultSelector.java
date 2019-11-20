package com.theembers.iot.router.selector;

import com.theembers.iot.router.Router;
import com.theembers.iot.router.route.Route;
import com.theembers.iot.router.rule.Rule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-13 15:33
 */
public class DefaultSelector extends AbstractSelector {
    public DefaultSelector(Router router) {
        super(router);
    }

    @Override
    public Route selectRoute(Rule rule) {
        return null;
    }
}
