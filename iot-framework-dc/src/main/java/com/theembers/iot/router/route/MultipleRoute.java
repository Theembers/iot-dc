package com.theembers.iot.router.route;

import com.theembers.iot.processor.Processor;
import com.theembers.iot.processor.ThingData;
import com.theembers.iot.router.Router;
import com.theembers.iot.router.rule.MultipleRule;
import com.theembers.iot.shadow.Shadow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-13 15:53
 */
public class MultipleRoute extends AbstractRoute<Map<String[], Dispatcher>, MultipleRule> {
    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(5, 10, 60, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());


    @Override
    public void buildDispatcher(Router router, MultipleRule rule, Map<String[], Dispatcher> dispatchers) {
        if (dispatchers == null) {
            dispatchers = new HashMap<>();
        }
        Map<String, Processor> processorMap = router.getMap();
        Map<String, String[]> ruleKey = this.rule.key();
        if (ruleKey == null || ruleKey.size() == 0) {
            return;
        }

        for (String head : ruleKey.keySet()) {
            String[] keys = ruleKey.get(head);
            if (keys == null || keys.length == 0) {
                return;
            }
            Dispatcher d = new Dispatcher();
            for (String key : keys) {
                d.append(processorMap.get(key));
            }
            dispatchers.put(keys, d);
        }
    }


    @Override
    public void run(Shadow shadow, ThingData data) {
        Map<String[], Dispatcher> dispatcherMap = this.dispatchers;
        dispatcherMap.forEach((keys, dispatcher) -> {
            dispatcher.run(shadow, data);
        });
    }
}
