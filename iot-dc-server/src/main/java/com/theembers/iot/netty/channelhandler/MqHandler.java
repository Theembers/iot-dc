package com.theembers.iot.netty.channelhandler;

import com.theembers.iot.RTUInfo;
import com.theembers.iot.mq.MQSender;
import com.theembers.iot.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 消息队列
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-19 16:37
 */
@Component
public class MqHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqHandler.class);

    public MqHandler() {
    }

    @Autowired
    private MQSender mqSender;

    private static MqHandler mqHandler;

    @PostConstruct
    public void init() {
        mqHandler = this;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof RTUInfo)) {
            return;
        }
        RTUInfo rtuInfo = (RTUInfo) msg;
        if (!rtuInfo.isPublish()) {
            LOGGER.info("unpublished sendMsg: {}", rtuInfo);
            return;
        }
        String dataStr = JsonUtils.str2Json(msg);
        LOGGER.info("sn: {} send..", rtuInfo.getSn());
        mqHandler.mqSender.send(rtuInfo.getMqExchange(), dataStr);
    }
}
