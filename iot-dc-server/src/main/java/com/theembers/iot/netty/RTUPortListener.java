package com.theembers.iot.netty;

import com.theembers.iot.netty.channelhandler.CommandHandler;
import com.theembers.iot.netty.channelhandler.ProcessorHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * RTU 接口监听器
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-09-30 2:29 PM
 */
public class RTUPortListener extends PortListenerAbstract {
    public RTUPortListener(int port, EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
        super(port, bossGroup, workerGroup);
        super.bind();
    }

    @Override
    ChannelHandler settingChannelInitializerHandler() {
        return new ChildChannelHandler();
    }

    private class ChildChannelHandler extends BaseHandler {
        @Override
        ChannelPipeline extHandler(ChannelPipeline pipeline) {
            pipeline
                    //  数据拆帧 & 头信息过滤
                    .addLast(new LengthFieldBasedFrameDecoder(10240, 2, 2, -4, 4, true))
                    // 数据封装
                    .addLast(new ProcessorHandler())
                    // 指令下发
                    .addFirst(new CommandHandler())
            ;
            return pipeline;
        }
    }

}
