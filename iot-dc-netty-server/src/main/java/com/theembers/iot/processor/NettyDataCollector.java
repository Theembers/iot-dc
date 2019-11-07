package com.theembers.iot.processor;

import com.theembers.iot.config.NettyConfig;
import com.theembers.iot.netty.RTUPortListener;
import com.theembers.iot.redis.IoTService;
import com.theembers.iot.collector.DataCollector;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-06 17:52
 */
@Component
public class NettyDataCollector implements DataCollector<NettyConfig> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyDataCollector.class);
    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Autowired
    private IoTService ioTService;

    @Override
    public void executor(NettyConfig nettyConfig) {
        LOGGER.info("Netty Start..");
        Integer port = nettyConfig.getPort();
        if (port == null) {
            LOGGER.warn("port is empty.");
            return;
        }
        // 获取配置
        try {
            ioTService.loadIotMapper2Global(InetAddress.getLocalHost().getHostAddress(), port);
            new RTUPortListener(port, bossGroup, workerGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
