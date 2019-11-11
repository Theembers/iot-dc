package com.theembers.iot.router;


import com.theembers.iot.processor.Processor;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 11:02
 */
public class SimpleRouteSelector implements Selector<SimpleRoute> {
    private Router router;

    public SimpleRouteSelector(Router router) {
        setRouter(router);
    }

    @Override
    public void setRouter(Router router) {
        this.router = router;
    }

    @Override
    public SimpleRoute getRoute(Rule rule) {
        String key = rule.key();
        Processor processor = router.getMap().get(key);
        return new SimpleRoute(key, processor);
    }
}
