package com.theembers.iot.processor;


import com.theembers.iot.collector.SourceData;
import com.theembers.iot.shadow.Shadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-13 16:29
 */
public interface Slot<S extends Shadow, I extends Input, O extends Output> {

    /**
     * 接收
     * 插槽数据接收 & 执行
     */
    Output<O> receive(S shadow, SlotData slotData);

    /**
     * 接力
     * 插槽数据转换
     */
    Input<I> relay(S shadow, SlotData slotData);

    /**
     * 传递
     * 将自己的数据转为插槽
     */
    SlotData passOn(S shadow, Output<O> output);

    Output<O> headIn(S shadow, SourceData input);

    Output<O> tailOut(S shadow, O output);
}
