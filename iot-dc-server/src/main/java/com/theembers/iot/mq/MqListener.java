package com.theembers.iot.mq;

import com.theembers.iot.GlobalInfo;
import com.theembers.iot.RTUCommandInfo;
import com.theembers.iot.netty.channelhandler.ChannelManagerHandler;
import com.theembers.iot.netty.channelhandler.CommandHandler;
import com.theembers.iot.redis.IoTService;
import com.theembers.iot.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-09 11:09
 */
@Component
public class MqListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqListener.class);

    @Autowired
    private IoTService ioTService;

    /**
     * 指令队列
     *
     * @param message
     */
    @RabbitListener(queues = "rtu_inst_queue")
    @RabbitHandler
    public void command(Message message) throws IOException {
        String msg = new String(message.getBody());
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
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "rtu_refresh_queue")
    @RabbitHandler
    public void refreshIotInfo(Message message) throws Exception {
        String msg = new String(message.getBody());
        if (GlobalInfo.Global_Iot_Redis_Key.equals(msg)) {
            LOGGER.info("start refresh GlobalInfo, rtu_refresh key is : {}", msg);
            boolean flag = ioTService.refreshIotMapper2Global();
            if (flag) {
                ChannelManagerHandler.refreshRTUChannelInfo();
            }
        }
    }

}
