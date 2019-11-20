package com.theembers.iot.router.selector;

import com.theembers.iot.router.Router;
import com.theembers.iot.router.route.LinkedRoute;
import com.theembers.iot.router.route.MultipleRoute;
import com.theembers.iot.router.route.Route;
import com.theembers.iot.router.route.SimpleRoute;
import com.theembers.iot.router.rule.LinkedRule;
import com.theembers.iot.router.rule.MultipleRule;
import com.theembers.iot.router.rule.Rule;
import com.theembers.iot.router.rule.SimpleRule;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-15 17:28
 */
public class AutoSelector extends AbstractSelector {

    public AutoSelector(Router router) {
        super(router);
    }

    public Route selectRoute(Rule rule) {
        Route route = getRoute(rule);
        return super.selectRoute(route, rule);
    }

    private Route getRoute(Rule rule) {

        if (rule instanceof SimpleRule) {
            return new SimpleRoute();
        } else if (rule instanceof LinkedRule) {
            return new LinkedRoute();
        } else if (rule instanceof MultipleRule) {
            return new MultipleRoute();
        } else {
            return null;
        }
    }

}
