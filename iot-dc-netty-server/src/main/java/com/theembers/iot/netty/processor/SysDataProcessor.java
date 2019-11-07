package com.theembers.iot.netty.processor;

import com.theembers.iot.ItemInfo;
import com.theembers.iot.RTUInfo;
import com.theembers.iot.enums.EMqExchange;
import com.theembers.iot.enums.ERTUChannelFlag;
import com.theembers.iot.netty.channelhandler.ChannelManagerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统信息 处理器
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-19 15:50
 */
public class SysDataProcessor extends ProcessorAbstract implements IDataProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysDataProcessor.class);

    private static final String SIGNAL = "Signal";
    private static final String VERSION = "version";
    private static final String PING = "ping";
    private static final String SN = "sn";

    public SysDataProcessor() {
        super(ERTUChannelFlag.SYS);
    }

    @Override
    public void translate(ChannelHandlerContext ctx, ByteBuf source, RTUInfo rtuInfo) throws Exception {
        if (checkAndGetAvailable(source)) {
            byte[] dataBytes = new byte[source.readableBytes()];
            source.readBytes(dataBytes);
            String sourceStr = new String(dataBytes);
            if (sourceStr.contains(SIGNAL)) {
                // 信号强度
                String signal = sourceStr.split(":")[1].trim();
                rtuInfo.setData(new ItemInfo(SIGNAL, signal));
                EMqExchange[] eMqExchanges = {EMqExchange.RTU_SIGNAL, EMqExchange.RTU_HEART};
                rtuInfo.setMqExchange(eMqExchanges);
            } else if (sourceStr.contains(VERSION)) {
                // 版本信息
                String version = sourceStr.split(":")[1].trim();
                rtuInfo.setData(new ItemInfo(VERSION, version));
            } else if (sourceStr.contains(PING)) {
                // ping
                rtuInfo.setData(new ItemInfo(PING, "ok"));
                EMqExchange[] eMqExchanges = {EMqExchange.RTU_HEART};
                rtuInfo.setMqExchange(eMqExchanges);
            } else if (sourceStr.contains(SN)) {
                // sn
                String sn = sourceStr.split(":")[1].trim();
                // 补全 channelInfo信息
                ChannelManagerHandler.setRTUChannelInfo(ctx, sn);
            } else {
                LOGGER.warn("unchecked system source: \"{}\"", sourceStr);
            }
        } else {
            if (super.getNextProcessor() != null)
                super.getNextProcessor().translate(ctx, source, rtuInfo);
        }
    }
}
