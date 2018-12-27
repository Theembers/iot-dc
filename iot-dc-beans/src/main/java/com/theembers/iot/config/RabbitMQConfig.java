package com.theembers.iot.config;

import com.theembers.iot.enums.EMqExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-05 16:29
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EMqExchange.RTU_DATA.getMqFanoutExchange());
    }

    @Bean
    FanoutExchange fanoutExchange1() {
        return new FanoutExchange(EMqExchange.RTU_HEART.getMqFanoutExchange());
    }

    @Bean
    FanoutExchange fanoutExchange2() {
        return new FanoutExchange(EMqExchange.RTU_SIGNAL.getMqFanoutExchange());
    }

    @Bean
    FanoutExchange fanoutExchange3() {
        return new FanoutExchange(EMqExchange.RTU_UNREGISTERED.getMqFanoutExchange());
    }
}
