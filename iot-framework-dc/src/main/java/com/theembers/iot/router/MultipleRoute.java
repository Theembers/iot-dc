package com.theembers.iot.router;

import com.theembers.iot.processor.Input;
import com.theembers.iot.processor.Processor;
import com.theembers.iot.shadow.Shadow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-13 15:53
 */
public class MultipleRoute extends AbstractRoute<Map<String[], Processor>, MultipleRule> {
    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(5, 10, 60, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    protected MultipleRoute(MultipleRule rule) {
        super(rule);
    }

    @Override
    public Route buildRoute(Router router) {
        Map<String, Processor> processorMap = router.getMap();
        Map<String, String[]> ruleKey = this.rule.key();
        if (ruleKey == null || ruleKey.size() == 0) {
            return this;
        }
        this.processors = new HashMap<>();
        ruleKey.forEach((head, keys) -> {
            if (keys == null || keys.length == 0) {
                return;
            }
            Processor p = null;

            for (String key : keys) {
                Processor theProcessor = processorMap.get(key);
                if (p == null) {
                    p = theProcessor;
                    this.processors.put(keys, theProcessor);
                    return;
                }
                p.append(theProcessor);
                p = theProcessor;
            }
        });
        return null;
    }

    @Override
    public void run(Shadow shadow, Input data) {
        this.processors.forEach((keys, link) -> {

        });
        THREAD_POOL.execute(() -> {

        });
    }
}
