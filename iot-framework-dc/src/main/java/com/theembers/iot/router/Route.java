package com.theembers.iot.router;

import com.theembers.iot.processor.Input;
import com.theembers.iot.processor.ThingData;
import com.theembers.iot.shadow.Shadow;

/**
 * 路线
 *
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:16
 */
public interface Route<R extends Rule> {
    /**
     * 获取导航
     *
     * @return
     */
    Route buildRoute(Router router);

    /**
     * 执行
     *
     * @param shadow
     * @param data
     * @throws
     */
    void run(Shadow shadow, Input data);
}
