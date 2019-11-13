package com.theembers.iot.processor;


import com.theembers.iot.shadow.Shadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-13 16:29
 */
public interface Slot {
    /**
     * 追加
     *
     * @param processor
     */
    void append(Processor processor);

    /**
     * 传递
     *
     * @param sortData
     */
    void passOn(Shadow shadow, Input input, SortData sortData);

    /**
     * 接收
     *
     * @param sortData
     */
    void receive(Shadow shadow, Input input, SortData sortData);
}
