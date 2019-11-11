package com.theembers.iot.router;


import com.theembers.iot.processor.DataProcessor;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:47
 */

public class SimpleRoute implements Route {
    private String key;
    private DataProcessor processor;

    public SimpleRoute(String key, DataProcessor processor) {
        this.key = key;
        this.processor = processor;
    }

    public String getKey() {
        return key;
    }

    public SimpleRoute setKey(String key) {
        this.key = key;
        return this;
    }

    public DataProcessor getProcessor() {
        return processor;
    }

    public SimpleRoute setProcessor(DataProcessor processor) {
        this.processor = processor;
        return this;
    }
}
