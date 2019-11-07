package com.theembers.iot.collection;

import com.theembers.iot.config.DataCollectorConfig;
import com.theembers.iot.server.DataProcessor;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 抽象采集器
 * 实现了CollectorRunner & InitializingBean接口
 * <p>
 * InitializingBean接口
 * 负责加载指定的 collectorConfig配置对象 和 dataProcessor数据处理器对象
 * <p>
 * CollectorRunner接口
 * CollectorRunner接口继承了 CommandLineRunner接口
 * 模板模式 [final method] 调用处理器
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-09-30 2:48 PM
 */
public abstract class AbstractCollector<C extends DataCollectorConfig> implements CollectorRunner, InitializingBean {
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private C collectorConfig;
    private DataProcessor dataProcessor;

    public abstract C setCollectorConfig();

    public abstract DataProcessor setDataProcessor();


    @Override
    public void afterPropertiesSet() throws Exception {
        collectorConfig = setCollectorConfig();
        dataProcessor = setDataProcessor();
    }

    @Override
    public final void run(String... args) throws Exception {
        singleThreadExecutor.execute(() -> this.dataProcessor.executor(this.collectorConfig));
    }
}