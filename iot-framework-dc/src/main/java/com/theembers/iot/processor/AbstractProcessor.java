package com.theembers.iot.processor;

import com.theembers.iot.shadow.Shadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 17:57
 */
public abstract class AbstractProcessor<I extends Input, O extends Output> implements Processor<I, O> {

    @Override
    public final Output<O> receive(Shadow shadow, SlotData slotData) {
        Input<I> iData = relay(shadow, slotData);
        return transform(shadow, iData);
    }

    @Override
    public final Output<O> headIn(Shadow shadow, I input) {
        Input<I> iData = beforeTransform(shadow, input);
        return transform(shadow, iData);
    }

    @Override
    public final Output<O> tailOut(Shadow shadow, O output) {
        return afterTransform(shadow, output);
    }


    @Override
    public void except(Exception e, String... msg) {
    }
}
