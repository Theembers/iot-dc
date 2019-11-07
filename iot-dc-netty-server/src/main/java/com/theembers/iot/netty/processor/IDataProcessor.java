package com.theembers.iot.netty.processor;

import com.theembers.iot.RTUInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * 数据处理器
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-19 15:45
 */
public interface IDataProcessor {
    IDataProcessor getNextProcessor();

    void setNextProcessor(IDataProcessor nextProcessor);

    void translate(ChannelHandlerContext ctx, ByteBuf source, RTUInfo rtuInfo) throws Exception;
}
