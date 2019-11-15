package com.theembers.iot.processor;

import com.theembers.iot.collector.SourceData;
import com.theembers.iot.shadow.Shadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 17:54
 */
public interface Processor<S extends Shadow, I extends Input, O extends Output> extends Slot<S,I, O> {
    /**
     * 前置处理流程
     * 流数据转换、对象转换等...
     *
     * @param shadow
     * @param srcData
     * @return
     */
    Input<I> beforeTransform(S shadow, SourceData srcData) throws Exception;

    /**
     * 业务处理
     * 具体业务赋能
     *
     * @param shadow
     * @param input
     * @return
     */
    Output<O> transform(S shadow, Input<I> input) throws Exception;

    /**
     * 后置处理流程
     * 只有在最后才会被调用的方法
     * 数据封装、业务数据封装等
     *
     * @param shadow
     * @param output
     * @return
     */
    Output<O> afterTransform(S shadow, O output) throws Exception;

    /**
     * 异常
     */
    void except(Exception e, String... msg);
}
