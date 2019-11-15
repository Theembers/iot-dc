package com.theembers.iot.processor;


import com.theembers.iot.shadow.Shadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-13 16:29
 */
public interface Slot<I, O> {

    /**
     * 接收
     * 插槽数据接收 & 执行
     */
    Output<O> receive(Shadow shadow, SlotData slotData);

    /**
     * 接力
     * 插槽数据转换
     */
    Input<I> relay(Shadow shadow, SlotData slotData);

    /**
     * 传递
     * 数据转为插槽
     */
    SlotData passOn(Shadow shadow, Output<O> output);

    Output<O> headIn(Shadow shadow, I input);

    Output<O> tailOut(Shadow shadow, O output);
}
