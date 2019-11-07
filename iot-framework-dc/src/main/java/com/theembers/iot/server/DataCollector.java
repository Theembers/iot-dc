package com.theembers.iot.server;

import com.theembers.iot.config.DataCollectorConfig;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-06 14:18
 */
public interface DataCollector<C extends DataCollectorConfig> {
    void executor(C c);
}
