package com.theembers.iot.router.route;

import com.theembers.iot.collector.SourceData;
import com.theembers.iot.router.Router;
import com.theembers.iot.router.rule.Rule;
import com.theembers.iot.shadow.Shadow;

/**
 * 路线
 *
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:16
 */
public interface Route<T, R extends Rule> {


    Route buildRoute(Router router, R rule);

    /**
     * 执行
     *
     * @param shadow
     * @param srcData
     */
    void run(Shadow shadow, SourceData srcData);
}
