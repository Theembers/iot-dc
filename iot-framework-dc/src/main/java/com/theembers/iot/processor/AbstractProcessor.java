package com.theembers.iot.processor;

import com.theembers.iot.collector.SourceData;
import com.theembers.iot.shadow.Shadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 17:57
 */
public abstract class AbstractProcessor<S extends Shadow, I extends Input, O extends Output> implements Processor<S, I, O> {

    @Override
    public final Output<O> receive(S shadow, SlotData slotData) {
        try {
            Input<I> iData = relay(shadow, slotData);
            return transform(shadow, iData);
        } catch (Exception e) {
            except(e);
        }
        return null;
    }

    @Override
    public final Output<O> headIn(S shadow, SourceData srcData) {
        try {
            Input<I> iData = beforeTransform(shadow, srcData);
            return transform(shadow, iData);
        } catch (Exception e) {
            except(e);
        }
        return null;
    }

    @Override
    public final Output<O> tailOut(S shadow, O output) {
        try {
            return afterTransform(shadow, output);
        } catch (Exception e) {
            except(e);
        }
        return null;
    }
}
