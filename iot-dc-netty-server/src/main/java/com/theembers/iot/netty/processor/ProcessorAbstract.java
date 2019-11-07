package com.theembers.iot.netty.processor;

import com.theembers.iot.enums.ERTUChannelFlag;
import io.netty.buffer.ByteBuf;

/**
 * 数据解码器 抽象类
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-19 15:52
 */
public abstract class ProcessorAbstract implements IDataProcessor {
    private IDataProcessor nextProcessor;
    private ERTUChannelFlag flag;

    @Override
    public IDataProcessor getNextProcessor() {
        return nextProcessor;
    }

    @Override
    public void setNextProcessor(IDataProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    ProcessorAbstract(ERTUChannelFlag flag) {
        this.flag = flag;
    }

    /**
     * 通道检测：前两个字节比较
     *
     * @param source
     * @return
     */
    boolean checkAndGetAvailable(ByteBuf source) {
        int length = source.readableBytes();
        if (length <= 2 && flag == null) {
            return false;
        }
        byte[] sourceFlag = new byte[2];
        source.readBytes(sourceFlag);
        int i = 0;
        int n = sourceFlag.length;
        byte[] checkFlag = flag.getFlag();
        while (n-- != 0) {
            if (sourceFlag[i] != checkFlag[i]) {
                // 未监测到 重置读标记位
                source.resetReaderIndex();
                return false;
            }
            i++;
        }
        // mark 读取标记位
        source.markReaderIndex();
        return true;
    }

    String buildDataKey(String littleKey) {
        return this.flag + "-" + littleKey;
    }
}
