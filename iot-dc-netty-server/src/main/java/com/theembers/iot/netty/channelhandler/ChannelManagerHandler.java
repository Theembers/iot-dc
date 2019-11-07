package com.theembers.iot.netty.channelhandler;

import com.theembers.iot.GlobalInfo;
import com.theembers.iot.IotInfo;
import com.theembers.iot.RTUChannelInfo;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 链路管理 handler
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-25 15:18
 */
@Sharable
public class ChannelManagerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelManagerHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("new channel coming! ----> {}", ctx.channel());
        ChannelId channelId = ctx.channel().id();
        RTUChannelInfo channelInfo = GlobalInfo.CHANNEL_INFO_MAP.getOrDefault(channelId, RTUChannelInfo.build("unknownSN", channelId));
        GlobalInfo.CHANNEL_INFO_MAP.put(channelId, channelInfo);
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("channel out! ----> {}", ctx.channel());
        ChannelId channelId = ctx.channel().id();
        RTUChannelInfo channelInfo = GlobalInfo.CHANNEL_INFO_MAP.remove(channelId);
        GlobalInfo.SN_CHANNEL_INFO_MAP.remove(channelInfo.getSn());
        LOGGER.info("remove channel: {}", channelInfo);
        ctx.fireChannelUnregistered();
    }

    /**
     * 补全 链路信息：根据 channelId 获取 channelInfo 并写入 sn 和 物联网信息
     *
     * @param ctx
     * @param sn
     * @return
     */
    public static void setRTUChannelInfo(ChannelHandlerContext ctx, String sn) {
        ChannelId channelId = ctx.channel().id();
        IotInfo iot = GlobalInfo.iotMapper.get(sn);
        GlobalInfo.CHANNEL_INFO_MAP.get(channelId)
                .setSn(sn).setIotInfo(iot).setChannel(ctx.channel());

        RTUChannelInfo channelInfo = GlobalInfo.SN_CHANNEL_INFO_MAP.getOrDefault(sn, RTUChannelInfo.build(sn, channelId));
        channelInfo.setIotInfo(iot).setChannel(ctx.channel());
        GlobalInfo.SN_CHANNEL_INFO_MAP.put(sn, channelInfo);
        LOGGER.info("sn: {} in the house.", sn);
    }

    /**
     * 刷新 链路信息
     */
    public static void refreshRTUChannelInfo() {
        LOGGER.info("refresh GlobalInfo...");
        GlobalInfo.CHANNEL_INFO_MAP.forEach((channelId, channelInfo) -> {
            String sn = channelInfo.getSn();
            channelInfo.setIotInfo(GlobalInfo.iotMapper.get(sn));
        });
        GlobalInfo.SN_CHANNEL_INFO_MAP.forEach((sn, channelInfo) -> channelInfo.setIotInfo(GlobalInfo.iotMapper.get(sn)));
    }
}
