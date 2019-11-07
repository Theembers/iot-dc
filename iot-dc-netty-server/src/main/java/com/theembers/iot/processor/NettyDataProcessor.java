package com.theembers.iot.processor;

import com.theembers.iot.server.DataProcessor;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import com.theembers.iot.config.NettyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-06 17:52
 */
@Component
public class NettyDataProcessor implements DataProcessor<NettyConfig> {
    private static final Logger logger = LoggerFactory.getLogger(NettyDataProcessor.class);
    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();


    @Override
    public void executor(NettyConfig nettyConfig) {
        logger.debug("NettyDataProcessor:executor...");
    }
}
