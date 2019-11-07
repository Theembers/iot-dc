package com.theembers.iot.netty.channelhandler;

import com.theembers.iot.GlobalInfo;
import com.theembers.iot.RTUCommandInfo;
import com.theembers.iot.utils.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * 指令下发
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-09 13:35
 */
@Sharable
public class CommandHandler extends MessageToByteEncoder<ByteBuf> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandler.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        out.writeBytes(msg);
    }

    public static void writeCommand(String sn, String command, int commandType) {
        if (CollectionUtils.isEmpty(GlobalInfo.SN_CHANNEL_INFO_MAP)) {
            LOGGER.warn("global snChannelInfo is empty.");
            return;
        }
        if (GlobalInfo.SN_CHANNEL_INFO_MAP.containsKey(sn)) {
            Channel channel = GlobalInfo.SN_CHANNEL_INFO_MAP.get(sn).getChannel();
            LOGGER.info("\n command: ({}) \n type: ({}) \n sn: ({})", command, commandType, sn);

            ByteBuf byteBuf = Unpooled.buffer();
            if (commandType == RTUCommandInfo.EInstructionType.JSON.getType()) {
                byteBuf.writeBytes(command.getBytes());
            } else if (commandType == RTUCommandInfo.EInstructionType.HEX.getType()) {
                try {
                    byteBuf.writeBytes(ByteUtils.hex2byte(command));
                } catch (Exception e) {
                    LOGGER.error("bad command to hex: {} ", command,e);
                    return;
                }
            }

            channel.writeAndFlush(byteBuf).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    LOGGER.info("ok");
                } else {
                    LOGGER.error("send data to client exception occur: {}", future.cause());
                }
            });
        } else {
            LOGGER.warn("no channel in global channelInfo by this sn:{}", sn);
        }
    }
}
