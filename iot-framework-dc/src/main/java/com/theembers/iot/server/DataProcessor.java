package com.theembers.iot.server;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-06 14:18
 */
public interface DataProcessor<C> {
    void executor(C c);
}
