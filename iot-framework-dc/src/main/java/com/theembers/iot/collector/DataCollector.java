package com.theembers.iot.collector;

import com.theembers.iot.config.DataCollectorConfig;

/**
 * 数据采集器
 *
 * @author TheEmbers Guo
 * createTime 2019-11-06 14:18
 */
public interface DataCollector<C extends DataCollectorConfig> {
    void executor(C c);
}
