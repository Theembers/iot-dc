package com.theembers.iot.netty.processor;

import com.theembers.iot.ItemInfo;
import com.theembers.iot.RTUInfo;
import com.theembers.iot.enums.EMqExchange;
import com.theembers.iot.enums.ERTUChannelFlag;
import com.theembers.iot.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模拟信号 处理器
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-19 16:04
 */
public class SimDataProcessor extends ProcessorAbstract implements IDataProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimDataProcessor.class);

    public SimDataProcessor() {
        super(ERTUChannelFlag.SIM);
    }

    @Override
    public void translate(ChannelHandlerContext ctx, ByteBuf source, RTUInfo rtuInfo) throws Exception {
        if (checkAndGetAvailable(source)) {
            byte[] dataBytes = new byte[source.readableBytes()];
            source.readBytes(dataBytes);
            String dataJsonStr = new String(dataBytes);
            Map<String, List<Map<String, String>>> dataMap = JsonUtils.jsonStr2Obj(dataJsonStr, Map.class);
            if (!CollectionUtils.isEmpty(dataMap) && dataMap.containsKey("data")) {
                List<Map<String, String>> dataItems = dataMap.get("data");
                if (CollectionUtils.isEmpty(dataItems)) {
                    return;
                }
                List<ItemInfo> itemInfos = new ArrayList<>(dataItems.size());
                dataItems.forEach(data -> {
                    if (CollectionUtils.isEmpty(data)) {
                        return;
                    }
                    data.forEach((key, val) -> {
                        String itemKey = buildDataKey(key);
                        if ("ch1".equals(key)) {
                            val = String.valueOf((Float.valueOf(val) - 4) / 16);
                        }
                        itemInfos.add(new ItemInfo(itemKey, val));
                    });
                    EMqExchange[] eMqExchanges = {EMqExchange.RTU_DATA, EMqExchange.RTU_HEART};
                    rtuInfo.setMqExchange(eMqExchanges);
                    rtuInfo.setData(itemInfos);
                });
            }
        } else {
            if (super.getNextProcessor() != null)
                super.getNextProcessor().translate(ctx, source, rtuInfo);
        }
    }
}
