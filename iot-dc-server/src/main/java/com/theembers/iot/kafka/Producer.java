package com.theembers.iot.kafka;

import com.theembers.iot.enums.EMqExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * kafka 生产者
 *
 * @author TheEmbers Guo
 * createTime 2019-08-01 10:59
 */
@Component
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    public void send(EMqExchange[] mqExchange, String message) {
        if (mqExchange == null || mqExchange.length == 0) {
            return;
        }
        Arrays.asList(mqExchange).stream().forEach(eMqExchange -> {
            LOGGER.info("send -> {} -> {}", eMqExchange.name(), message);
            kafkaTemplate.send(eMqExchange.getMqFanoutExchange(), "", message.getBytes());
        });
    }
}
