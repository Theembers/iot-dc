package com.theembers.iot.collection;

import com.theembers.iot.server.DataProcessor;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 接口监听服务
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-09-30 2:48 PM
 */
public abstract class AbstractCollector<C> implements CollectorRunner, InitializingBean {
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private C collectorConfig;
    private DataProcessor dataProcessor;

    public AbstractCollector setCollectorConfig(C collectorConfig) {
        this.collectorConfig = collectorConfig;
        return this;
    }

    public AbstractCollector setDataProcessor(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
        return this;
    }

    @Override
    public void run(String... args) throws Exception {
        DataProcessor processor = this.dataProcessor;
        singleThreadExecutor.execute(() -> processor.executor(this.collectorConfig));
    }
}