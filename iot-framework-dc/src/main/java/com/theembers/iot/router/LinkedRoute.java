package com.theembers.iot.router;


import com.theembers.iot.processor.Input;
import com.theembers.iot.processor.Processor;
import com.theembers.iot.processor.ThingData;
import com.theembers.iot.shadow.Shadow;

import java.util.Arrays;
import java.util.Map;


/**
 * @author TheEmbers Guo
 * createTime 2019-11-12 10:14
 */
public class LinkedRoute extends AbstractRoute<Processor, LinkedRule> {
    protected LinkedRoute(LinkedRule rule) {
        super(rule);
    }

    @Override
    public LinkedRoute buildRoute(Router router) {
        Map<String, Processor> processorMap = router.getMap();
        String[] keyArr = this.rule.getKey();
        if (keyArr.length > 0) {
            Arrays.asList(keyArr).forEach(key -> {
                Processor theProcessor = processorMap.get(key);
                if (this.processors == null) {
                    this.processors = theProcessor;
                    return;
                }
                this.processors.append(theProcessor);
                this.processors = theProcessor;
            });
        }
        return this;
    }

    @Override
    public void run(Shadow shadow, Input data) {
        this.processors.execute(shadow, data);
    }
}
