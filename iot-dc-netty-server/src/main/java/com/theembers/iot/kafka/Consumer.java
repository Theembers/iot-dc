package com.theembers.iot.kafka;

import com.theembers.iot.GlobalInfo;
import com.theembers.iot.RTUCommandInfo;
import com.theembers.iot.netty.channelhandler.ChannelManagerHandler;
import com.theembers.iot.netty.channelhandler.CommandHandler;
import com.theembers.iot.redis.IoTService;
import com.theembers.iot.utils.JsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * kafka 消费者
 *
 * @author TheEmbers Guo
 * createTime 2019-08-01 11:06
 */
//@Component
public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private IoTService ioTService;
    /**
     * 指令队列
     *
     * @param record
     * @throws Exception
     */
    @KafkaListener(topics = "rtu_inst_topic")
    public void listen(ConsumerRecord<String, byte[]> record) throws Exception {
        String msg = new String(record.value());
        LOGGER.info("rtu-command: {}", msg);
        RTUCommandInfo commandInfo = JsonUtils.jsonStr2Obj(msg, RTUCommandInfo.class);
        if (commandInfo == null ||
                StringUtils.isEmpty(commandInfo.getSn()) ||
                StringUtils.isEmpty(commandInfo.getInstruction()) ||
                StringUtils.isEmpty(commandInfo.getInstructionType())) {
            LOGGER.warn("bad command: {}", commandInfo);
            return;
        }
        CommandHandler.writeCommand(commandInfo.getSn(), commandInfo.getInstruction(), commandInfo.getInstructionType());
    }

    /**
     * 刷新 iot信息
     *
     * @param record
     * @throws Exception
     */
    @KafkaListener(topics = "rtu_refresh_topic")
    public void refreshIotInfo(ConsumerRecord<String, byte[]> record) throws Exception {
        String msg = new String(record.value());
        if (GlobalInfo.Global_Iot_Redis_Key.equals(msg)) {
            LOGGER.info("start refresh GlobalInfo, rtu_refresh key is : {}", msg);
            boolean flag = ioTService.refreshIotMapper2Global();
            if (flag) {
                ChannelManagerHandler.refreshRTUChannelInfo();
            }
        }
    }
}
