package com.theembers.iot.processor;

import com.theembers.iot.shadow.Shadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 17:54
 */
public interface Processor<I, O> extends Slot<I, O> {
    /**
     * 前置转换
     */
    Input<I> beforeTransform(Shadow shadow, I tData);

    /**
     * 数据转换: 基于数据的业务内容进行加工，构建为具体的业务数据
     */
    Output<O> transform(Shadow shadow, Input<I> input);

    /**
     * 后置转换
     *
     * @param output
     * @return
     */
    Output<O> afterTransform(Shadow shadow, O output);

    /**
     * 异常
     */
    void except(Exception e, String... msg);
}
