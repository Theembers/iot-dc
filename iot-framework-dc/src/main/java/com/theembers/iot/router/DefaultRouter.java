package com.theembers.iot.router;


import com.theembers.iot.processor.DataProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.beans.Introspector;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:58
 */
@Component
public class DefaultRouter extends AbstractRouter implements InitializingBean, ApplicationContextAware {

    @Override
    public void scanAndBuildMap() {
        initDataProcessor();
    }

    @Override
    public SimpleRouteSelector getSelector() {
        return (SimpleRouteSelector) super.getSelector();
    }

    @Override
    public Map<String, DataProcessor> getMap() {
        return DATA_PROCESSOR_MAP;
    }

    private static ApplicationContext applicationContext;
    /**
     * Map<beanName,DataProcessor>
     */

    private void register(String beanName, DataProcessor dataProcessor) {
        DATA_PROCESSOR_MAP.put(beanName, dataProcessor);
    }



    @Override
    public void afterPropertiesSet() {
        initRouter(new SimpleRouteSelector(this));
    }



    private void initDataProcessor() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DefaultRouter.applicationContext = applicationContext;
    }
}
