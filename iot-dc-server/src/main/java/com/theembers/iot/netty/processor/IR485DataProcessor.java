package com.theembers.iot.netty.processor;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-15 17:10
 */
public interface IR485DataProcessor {
    /**
     * 获取子数据段
     *
     * @param data
     * @return
     */
    static List<byte[]> subData(byte[] data) {
        int count = data.length / 4;
        List<byte[]> dataItemList = new ArrayList<>(count);
        ByteBuf byteBuf = Unpooled.wrappedBuffer(data);
        for (int i = 1; i <= count; i++) {
            byte[] bytes = new byte[4];
            byteBuf.readBytes(bytes);
            dataItemList.add(bytes);
        }
        return dataItemList;
    }

    /**
     * 高低位转换
     *
     * @param src
     * @return
     */
    static Float exchangeHL(byte[] src) {
        int value = ((src[0] & 0xFF) << 8)
                | (src[1] & 0xFF)
                | ((src[2] & 0xFF) << 24)
                | ((src[3] & 0xFF) << 16);
        return Float.intBitsToFloat(value);
    }

    static Float toFloat(byte[] b) {
        return Float.intBitsToFloat(getInt(b, 0));
    }

    static int getInt(byte[] memory, int index) {
        return (memory[index] & 0xff) << 24 |
                (memory[index + 1] & 0xff) << 16 |
                (memory[index + 2] & 0xff) << 8 |
                memory[index + 3] & 0xff;
    }
}
