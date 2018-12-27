package com.theembers.iot.netty.processor;

import com.theembers.iot.ItemInfo;
import com.theembers.iot.ModbusInfo;
import com.theembers.iot.RTUInfo;
import com.theembers.iot.enums.EMqExchange;
import com.theembers.iot.enums.ERTUChannelFlag;
import com.theembers.iot.utils.CRCUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 485串口 处理器
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-22 15:51
 */
public class R485DataProcessor extends ProcessorAbstract implements IDataProcessor, IR485DataProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(R485DataProcessor.class);

    public R485DataProcessor() {
        super(ERTUChannelFlag.R485);
    }

    @Override
    public void translate(ChannelHandlerContext ctx, ByteBuf source, RTUInfo rtuInfo) throws Exception {
        if (checkAndGetAvailable(source)) {
            // 截取数据
            ModbusInfo modbusInfo = new ModbusInfo(source);
            // 校验数据
            if (!CRCUtils.checkCRC(modbusInfo.getFullData(), modbusInfo.getCrc())) {
                LOGGER.warn("R485 bad data: {}", String.valueOf(source));
            }
            // 数据转换
            List<byte[]> dataItemList = IR485DataProcessor.subData(modbusInfo.getData());

            // 构建 rtuInfo 信息
            List<ItemInfo> itemInfoList = new ArrayList<>(2);
            byte[] SSLL = dataItemList.get(0);
            String itemKeySSLL = buildDataKey("SSLL");
            itemInfoList.add(new ItemInfo(itemKeySSLL, String.valueOf(IR485DataProcessor.exchangeHL(SSLL))));
            byte[] LJLL = dataItemList.get(1);
            String itemKeyLJLL = buildDataKey("LJLL");
            itemInfoList.add(new ItemInfo(itemKeyLJLL, String.valueOf(IR485DataProcessor.exchangeHL(LJLL))));
            if (dataItemList.size() == 3) {
                byte[] YL = dataItemList.get(2);
                String itemKeyYL = buildDataKey("YL");
                itemInfoList.add(new ItemInfo(itemKeyYL, String.valueOf(IR485DataProcessor.toFloat(YL))));
            }
            rtuInfo.setData(itemInfoList);

            // 设置 mq 交换器
            EMqExchange[] eMqExchanges = {EMqExchange.RTU_DATA, EMqExchange.RTU_HEART};
            rtuInfo.setMqExchange(eMqExchanges);
        } else {
            if (super.getNextProcessor() != null)
                super.getNextProcessor().translate(ctx, source, rtuInfo);
        }
    }
}
