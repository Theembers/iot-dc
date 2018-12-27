package com.theembers.iot.enums;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-05 16:04
 */
public enum EMqExchange {
    RTU_SIGNAL("rtu_signal_fx"),
    RTU_HEART("rtu_heart_fx"),
    RTU_DATA("rtu_data_fx"),
    RTU_UNREGISTERED("rtu_unregistered_fx"),
    RTU_INSTRUCTION_EXCHANGE("rtu-instruction-exchange");
    private String mqFanoutExchange;

    EMqExchange(String mqFanoutExchange) {
        this.mqFanoutExchange = mqFanoutExchange;
    }

    public String getMqFanoutExchange() {
        return mqFanoutExchange;
    }

    public void setMqFanoutExchange(String mqFanoutExchange) {
        this.mqFanoutExchange = mqFanoutExchange;
    }
}
