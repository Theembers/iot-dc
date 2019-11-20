package com.theembers.iot.router;

import com.theembers.iot.router.selector.Selector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象 路由
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:01
 */
public abstract class AbstractRouter<P> implements Router<P> {
    private Selector selector;
    protected final Map<String, P> PROCESSOR_MAP = new ConcurrentHashMap<>();

    @Override
    public Router initRouter(Selector selector) {
        setSelector(selector);
        scanAndBuildMap();
        return this;
    }

    @Override
    public abstract void scanAndBuildMap();

    @Override
    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    @Override
    public Selector getSelector() {
        return this.selector;
    }

    @Override
    public Map<String, P> getMap() {
        return PROCESSOR_MAP;
    }
}
