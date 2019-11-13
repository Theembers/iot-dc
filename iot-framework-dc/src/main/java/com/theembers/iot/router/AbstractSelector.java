package com.theembers.iot.router;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-13 10:44
 */
public abstract class AbstractSelector implements Selector {
    private Router router;


    protected AbstractSelector(Router router) {
        this.router = router;
    }

    @Override
    public void selectRoute( Route route) {
        route = route.buildRoute(router);
    }
}
