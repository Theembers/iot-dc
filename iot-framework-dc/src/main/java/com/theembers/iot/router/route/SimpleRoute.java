package com.theembers.iot.router.route;

import com.theembers.iot.collector.SourceData;
import com.theembers.iot.processor.Processor;
import com.theembers.iot.router.Router;
import com.theembers.iot.router.rule.SimpleRule;
import com.theembers.iot.shadow.Shadow;

import java.util.Map;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:47
 */

public class SimpleRoute extends AbstractRoute<Dispatcher, SimpleRule> {

    @Override
    public Dispatcher buildDispatcher(Router router, SimpleRule rule, Dispatcher dispatchers) {
        Map<String, Processor> processorMap = router.getMap();
        if (dispatchers == null) {
            dispatchers = new Dispatcher();
        }
        dispatchers.append(processorMap.get(this.rule.key()));
        return dispatchers;
    }

    @Override
    public void run(Shadow shadow, SourceData srcData) {
        run(shadow, srcData);
    }
}
