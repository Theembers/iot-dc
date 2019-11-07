package com.theembers.iot;

import com.theembers.iot.collection.AbstractCollector;
import com.theembers.iot.config.NettyConfig;
import com.theembers.iot.server.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 基于 netty 实现的采集器
 * 也就是之前s-0.1这的netty实现方案
 *
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
    public NettyConfig setCollectorConfig() {
        return nettyConfig;
    }

    @Override
    public DataProcessor setDataProcessor() {
        return processor;
    }
}
