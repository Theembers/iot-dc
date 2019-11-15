package com.theembers.iot.router.route;

import com.theembers.iot.collector.SourceData;
import com.theembers.iot.processor.Processor;
import com.theembers.iot.router.Router;
import com.theembers.iot.router.rule.MultipleRule;
import com.theembers.iot.shadow.Shadow;
import com.yunding.iot.framework.collector.SourceData;
import com.yunding.iot.framework.processor.Processor;
import com.yunding.iot.framework.router.Router;
import com.yunding.iot.framework.router.rule.MultipleRule;
import com.yunding.iot.framework.shadow.Shadow;

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
    public Map<String[], Dispatcher> buildDispatcher(Router router, MultipleRule rule, Map<String[], Dispatcher> dispatchers) {
        if (dispatchers == null) {
            dispatchers = new HashMap<>();
        }
        Map<String, Processor> processorMap = router.getMap();
        Map<String, String[]> ruleKey = this.rule.key();
        if (ruleKey == null || ruleKey.size() == 0) {
            return dispatchers;
        }

        for (String head : ruleKey.keySet()) {
            String[] keys = ruleKey.get(head);
            if (keys == null || keys.length == 0) {
                return dispatchers;
            }
            Dispatcher d = new Dispatcher();
            for (String key : keys) {
                d.append(processorMap.get(key));
            }
            dispatchers.put(keys, d);
        }
        return dispatchers;
    }


    @Override
    public void run(Shadow shadow, SourceData data) {
        Map<String[], Dispatcher> dispatcherMap = this.dispatchers;
        dispatcherMap.forEach((keys, dispatcher) -> THREAD_POOL.execute(() -> dispatcher.run(shadow, data)));
    }
}
