package com.theembers.iot.router.route;

import com.theembers.iot.processor.ThingData;
import com.theembers.iot.router.Router;
import com.theembers.iot.router.rule.Rule;
import com.theembers.iot.shadow.Shadow;
import com.yunding.iot.demo.IotData;
import com.yunding.iot.exception.processor.IotDataProcessorErrorException;
import com.yunding.iot.framework.router.Router;
import com.yunding.iot.framework.router.rule.Rule;
import com.yunding.iot.framework.shadow.Shadow;

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
     * @param data
     */
    void run(Shadow shadow, ThingData data);
}
