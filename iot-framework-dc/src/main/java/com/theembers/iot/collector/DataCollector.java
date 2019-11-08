package com.theembers.iot.collector;

/**
 * 数据采集器
 *
 * @author TheEmbers Guo
 * createTime 2019-11-06 14:18
 */
public interface DataCollector<C extends DataCollectorConfig> {
    /**
     * 初始化
     */
    default void init() {
    }

    /**
     * 执行
     *
     * @param c
     */
    void executor(C c);

    /**
     * 卸载
     */
    default void dispatch() {
    }
}
