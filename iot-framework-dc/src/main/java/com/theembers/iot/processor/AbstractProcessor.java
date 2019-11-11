package com.theembers.iot.processor;

import java.util.List;

/**
 * 抽象处理器
 * 定义了处理流程以及默认实现
 *
 * @author TheEmbers Guo
 * createTime 2019-11-07 17:04
 */
public abstract class AbstractProcessor<I extends Input, O extends Output> implements Processor<I, O> {
    protected abstract Class<I> setInputEntityClass();

    protected abstract Class<O> setOutputEntityClass();

    @Override
    public List<I> inputDeserialization(ThingData tData) {
        return tData.buildDataEntity(setInputEntityClass());
    }

    @Override
    public AppData outputSerialization(List<O> out) {
        AppData<O> appData = AppData.builder();
        appData.setData(out);
        return appData;
    }

    @Override
    public void except(Exception e, String... msg) {
        throw new RuntimeException(e);
    }

    @Override
    public final AppData execute(ThingData tData) {
        try {
            // 输入数据反序列化
            List<I> in = inputDeserialization(tData);
            // 数据转换
            List<O> out = transform(tData, in);
            // 输出数据序列化
            return outputSerialization(out);
        } catch (Exception e) {
            except(e);
        }
        return null;
    }
}
