package com.theembers.iot.netty;


import com.theembers.iot.netty.channelhandler.ChannelManagerHandler;
import com.theembers.iot.netty.channelhandler.ExceptionHandler;
import com.theembers.iot.netty.channelhandler.MqHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 端口监听
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-09-30 1:54 PM
 */
abstract class PortListenerAbstract {
    private static final Logger LOGGER = LoggerFactory.getLogger(PortListenerAbstract.class);
    /**
     * 端口
     */
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    PortListenerAbstract(int port, EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
        this.port = port;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    /**
     * channelhandler 设置 抽象方法
     *
     * @return
     */
    abstract ChannelHandler settingChannelInitializerHandler();

    /**
     * 接口绑定
     */
    void bind() {
        if (port == 0 || this.workerGroup == null || this.bossGroup == null) {
            throw new RuntimeException("'port','bossGroup' and 'workerGroup' had to be initialized before bind.");
        }

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(this.bossGroup, this.workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(this.settingChannelInitializerHandler());
            ChannelFuture channelFuture = bootstrap.bind(this.port).sync();
            LOGGER.info("port:{} bind successful.", port);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 基础
     */
    abstract class BaseHandler extends ChannelInitializer<SocketChannel> {
        /**
         * 拓展
         *
         * @param pipeline
         * @return
         */
        abstract ChannelPipeline extHandler(ChannelPipeline pipeline);

        @Override
        protected void initChannel(SocketChannel socketChannel) {
            ChannelPipeline pipeline = socketChannel.pipeline();
            pipeline
                    // log
                    .addLast("logging", new LoggingHandler(LogLevel.INFO))
                    // 心跳检测
//                    .addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS))
//                    .addLast(new HeartBeatHandler())
                    //  链路管理
                    .addLast(new ChannelManagerHandler())
            ;
            // 拓展
            extHandler(socketChannel.pipeline());
            pipeline.addLast(new MqHandler())
                    // 异常管理
                    .addLast(new ExceptionHandler())
            ;
        }
    }
}
