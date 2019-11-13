package com.theembers.iot.router;

import com.theembers.iot.processor.Input;
import com.theembers.iot.processor.Processor;
import com.theembers.iot.shadow.Shadow;
import com.yunding.iot.exception.processor.IotDataProcessorErrorException;
import com.yunding.iot.framework.processor.Processor;
import com.yunding.iot.framework.shadow.Shadow;
import com.yunding.iot.processor.DataProcessor;
import com.yunding.iot.protocol.common.kafka.IotDataMessage;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:47
 */

public class SimpleRoute<P> extends AbstractRoute<P, SimpleRule> {

    public SimpleRoute(SimpleRule rule) {
        super(rule);
    }

    @Override
    public Route buildRoute(Router router) {
        this.processors = (P) router.getMap().get(this.rule.key());
        return this;
    }

    @Override
    public void run(Shadow shadow, Input data) {
        Processor processor = (Processor) this.processors;
        processor.execute(shadow, data);

    }
}
