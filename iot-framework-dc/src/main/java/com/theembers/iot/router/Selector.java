package com.theembers.iot.router;

import com.theembers.iot.router.route.Route;
import com.theembers.iot.router.rule.Rule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:14
 */
public interface Selector {
    Route selectRoute(Route route, Rule rule);

    Route selectRoute(Rule rule);
}
