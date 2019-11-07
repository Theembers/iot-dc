package com.theembers.iot.netty.processor;

import com.theembers.iot.ItemInfo;
import com.theembers.iot.RTUInfo;
import com.theembers.iot.enums.EMqExchange;
import com.theembers.iot.enums.ERTUChannelFlag;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示器
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-19 16:18
 */
public class DisplayDataProcessor extends ProcessorAbstract implements IDataProcessor {
    public DisplayDataProcessor() {
        super(ERTUChannelFlag.DISPLAY);
    }

    @Override
    public void translate(ChannelHandlerContext ctx, ByteBuf source, RTUInfo rtuInfo) throws Exception {
        if (checkAndGetAvailable(source)) {
//            byte[] dataBytes = new byte[source.readableBytes()];
//            source.readBytes(dataBytes);
//            String dataStr = new String(dataBytes);
            List<ItemInfo> itemInfoList = new ArrayList<>(1);
            itemInfoList.add(new ItemInfo("DISPLAY-WORK", "ok"));
            EMqExchange[] eMqExchanges = {EMqExchange.RTU_DATA, EMqExchange.RTU_HEART};
            rtuInfo.setMqExchange(eMqExchanges);
            rtuInfo.setData(itemInfoList);
        } else {
            if (super.getNextProcessor() != null)
                super.getNextProcessor().translate(ctx, source, rtuInfo);
        }

    }
}
