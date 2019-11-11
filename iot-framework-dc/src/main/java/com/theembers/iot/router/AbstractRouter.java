package com.theembers.iot.router;



import com.theembers.iot.processor.Processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:01
 */
public abstract class AbstractRouter implements Router {
    private Selector selector;
    protected static final Map<String, Processor> PROCESSOR_MAP = new ConcurrentHashMap<>();


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
    public Map getMap() {
        return PROCESSOR_MAP;
    }
}
