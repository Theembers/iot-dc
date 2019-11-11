package com.theembers.iot.router;


import com.theembers.iot.processor.Processor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

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
    public Map<String, Processor> getMap() {
        return PROCESSOR_MAP;
    }


    @Override
    public void afterPropertiesSet() {
        initRouter(new SimpleRouteSelector(this));
    }

    private void init() {
        // todo  初始化方法
    }

}
