package com.theembers.iot;

import com.theembers.iot.collection.AbstractCollector;
import com.theembers.iot.config.NettyConfig;
import com.theembers.iot.server.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-06 14:46
 */
@Component
public class NettyCollector extends AbstractCollector<NettyConfig> {
    @Autowired
    private NettyConfig nettyConfig;
    @Autowired
    private DataProcessor<NettyConfig> processor;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setCollectorConfig(nettyConfig);
        super.setDataProcessor(processor);
    }
}
