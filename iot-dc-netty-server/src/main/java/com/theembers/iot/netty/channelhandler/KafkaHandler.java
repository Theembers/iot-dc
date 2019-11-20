package com.theembers.iot.netty.channelhandler;

import com.theembers.iot.RTUInfo;
import com.theembers.iot.kafka.Producer;
import com.theembers.iot.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author TheEmbers Guo
 * createTime 2019-08-01 11:17
 */
//@Component
public class KafkaHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqHandler.class);

    public KafkaHandler() {
    }

    @Autowired
    private Producer producer;

    private static KafkaHandler kafkaHandler;

    @PostConstruct
    public void init() {
        kafkaHandler = this;
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
        kafkaHandler.producer.send(rtuInfo.getMqExchange(), dataStr);
    }
}
