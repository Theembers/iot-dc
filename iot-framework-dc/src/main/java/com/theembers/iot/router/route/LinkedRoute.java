package com.theembers.iot.router.route;

import com.theembers.iot.collector.SourceData;
import com.theembers.iot.processor.Processor;
import com.theembers.iot.router.Router;
import com.theembers.iot.router.rule.LinkedRule;
import com.theembers.iot.shadow.Shadow;

import java.util.Map;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-12 10:14
 */
public class LinkedRoute extends AbstractRoute<Dispatcher, LinkedRule> {
    @Override
    public Dispatcher buildDispatcher(Router router, LinkedRule rule, Dispatcher dispatcher) {
        if (dispatcher == null) {
            dispatcher = new Dispatcher();
        }
        Map<String, Processor> processorMap = router.getMap();
        String[] keyArr = this.rule.getKey();

        if (keyArr.length > 0) {
            for (String key : keyArr) {
                Processor theProcessor = processorMap.get(key);
                dispatcher.append(theProcessor);
            }
        }
        return dispatcher;
    }


    @Override
    public void run(Shadow shadow, SourceData data) {
        run(shadow, data);
    }
}
