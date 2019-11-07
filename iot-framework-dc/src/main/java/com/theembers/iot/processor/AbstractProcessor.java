package com.theembers.iot.processor;

import java.util.List;

/**
 * 抽象处理器
 * 定义了处理流程以及默认实现
 *
 * @author TheEmbers Guo
 * createTime 2019-11-07 17:04
 */
public abstract class AbstractProcessor<I extends Input, O extends Output> implements DataProcessor<I, O> {
    protected abstract Class<I> getInputEntityClass();

    protected abstract Class<O> getOutputEntityClass();

    @Override
    public List<I> input(ThingData tData) {
        return tData.buildDataEntity(getInputEntityClass());
    }

    @Override
    public AppData output(List<O> out) {
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
            // 输入数据
            List<I> in = input(tData);
            // 数据转换
            List<O> out = transform(tData, in);
            // 输出数据
            return output(out);
        } catch (Exception e) {
            except(e);
        }
        return null;
    }
}
