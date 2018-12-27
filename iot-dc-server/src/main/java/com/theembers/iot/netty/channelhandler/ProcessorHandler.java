package com.theembers.iot.netty.channelhandler;

import com.theembers.iot.*;
import com.theembers.iot.enums.EMqExchange;
import com.theembers.iot.netty.processor.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RTU 设备 解码
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-19 15:59
 */
@Component
public class ProcessorHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorHandler.class);
    private final IDataProcessor sysDataProcessor = new SysDataProcessor();
    private final IDataProcessor simDataProcessor = new SimDataProcessor();
    private final IDataProcessor displayDataProcessor = new DisplayDataProcessor();
    private final IDataProcessor r485DataProcessor = new R485DataProcessor();


    public ProcessorHandler() {
        sysDataProcessor.setNextProcessor(simDataProcessor);
        simDataProcessor.setNextProcessor(r485DataProcessor);
        r485DataProcessor.setNextProcessor(displayDataProcessor);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;
            RTUInfo rtuInfo = new RTUInfo(null);
            sysDataProcessor.translate(ctx, byteBuf, rtuInfo);
            buildRtuInfo(ctx, rtuInfo);
            ctx.fireChannelRead(rtuInfo);
        } else {
            ctx.fireChannelRead(msg);
        }

    }

    private void buildRtuInfo(ChannelHandlerContext ctx, RTUInfo rtuInfo) {
        RTUChannelInfo rtuChannelInfo = GlobalInfo.CHANNEL_INFO_MAP.get(ctx.channel().id());
        IotInfo iotInfo = rtuChannelInfo.getIotInfo();
        // 如果不存在物联网信息则使用sn号并添加未注册队列标记，否则使用物联网id
        rtuInfo.setSn(rtuChannelInfo.getSn());
        Map<String, String> iotInfoDataMap;
        if (iotInfo == null) {
            rtuInfo.setId(rtuChannelInfo.getSn());
            EMqExchange[] eMqExchanges = {EMqExchange.RTU_UNREGISTERED};
            rtuInfo.setMqExchange(eMqExchanges);
            iotInfoDataMap = new HashMap<>();
        } else {
            rtuInfo.setId(iotInfo.getId());
            iotInfoDataMap = iotInfo.getData();
        }

        // 替换 rtuInfo 的 item id
        if (rtuInfo.getData() instanceof Collection) {
            List<ItemInfo> itemInfoList = (List) rtuInfo.getData();
            if (CollectionUtils.isEmpty(itemInfoList)) {
                return;
            }
            itemInfoList.forEach(item -> {
                String itemId = rtuInfo.getSn() + "-" + item.getId();
                item.setId(iotInfoDataMap.getOrDefault(itemId, itemId));
            });
        } else if (rtuInfo.getData() instanceof ItemInfo) {
            ItemInfo itemInfo = ((ItemInfo) rtuInfo.getData());
            String itemId = rtuInfo.getSn() + "-" + itemInfo.getId();
            itemInfo.setId(iotInfoDataMap.getOrDefault(itemId, itemId));
        }
    }
}
