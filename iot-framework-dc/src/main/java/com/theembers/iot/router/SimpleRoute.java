package com.theembers.iot.router;


import com.theembers.iot.processor.Processor;

/**
 * simpleRoute 是 简单路径
 * 只返回一个 processor
 *
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:47
 */

public class SimpleRoute implements Route {
    private String key;
    private Processor processor;

    public SimpleRoute(String key, Processor processor) {
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

    public Processor getProcessor() {
        return processor;
    }

    public SimpleRoute setProcessor(Processor processor) {
        this.processor = processor;
        return this;
    }
}
