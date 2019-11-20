package com.theembers.iot.mq;

import com.theembers.iot.enums.EMqExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * mq
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-22 14:37
 */
//@Component
public class MQSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(EMqExchange[] mqExchange, String content) {
        Message message = MessageBuilder.withBody(content.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .build();
        if (mqExchange == null || mqExchange.length == 0) {
            return;
        }
        Arrays.asList(mqExchange).stream().forEach(eMqExchange -> {
            LOGGER.info("send -> {} -> {}", eMqExchange.name(), message);
            this.rabbitTemplate.convertAndSend(eMqExchange.getMqFanoutExchange(), "", message);
        });
    }
}
