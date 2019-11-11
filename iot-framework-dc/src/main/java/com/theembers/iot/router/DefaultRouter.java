package com.theembers.iot.router;


import com.theembers.iot.processor.DataProcessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.Introspector;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:58
 */
@Component
public class DefaultRouter extends AbstractRouter implements InitializingBean {



    @Override
    public void scanAndBuildMap() {
        init();
    }

    @Override
    public SimpleRouteSelector getSelector() {
        return (SimpleRouteSelector) super.getSelector();
    }

    @Override
    public Map<String, DataProcessor> getMap() {
        return DATA_PROCESSOR_MAP;
    }

    /**
     * Map<dataProcessorClassName,appTopics>
     */
    private static final ConcurrentMap<String, String[]> APP_TOPICS_MAP = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() {
        initRouter(new SimpleRouteSelector(this));
    }

    public String[] getAppTopics(String name) {
        return APP_TOPICS_MAP.get(name);
    }

    private void init() {

    }


}
