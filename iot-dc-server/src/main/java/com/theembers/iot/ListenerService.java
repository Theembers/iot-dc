package com.theembers.iot;

import com.theembers.iot.config.NettyConfig;
import com.theembers.iot.netty.RTUPortListener;
import com.theembers.iot.redis.IoTService;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 接口监听服务
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-09-30 2:48 PM
 */
@Component
public class ListenerService implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerService.class);
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    @Autowired
    private NettyConfig nettyConfig;
    @Autowired
    private IoTService ioTService;

    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Override
    public void run(String... args) throws Exception {
        Integer port = nettyConfig.getPort();
        if (port == null) {
            LOGGER.warn("port is empty.");
            return;
        }
        // 获取配置
        ioTService.loadIotMapper2Global(InetAddress.getLocalHost().getHostAddress(), port);
        // 端口监听
        singleThreadExecutor.submit(() -> {
            new RTUPortListener(port, bossGroup, workerGroup);
        });
    }
}