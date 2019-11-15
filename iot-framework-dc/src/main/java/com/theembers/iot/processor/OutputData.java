package com.theembers.iot.processor;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-07 17:01
 */
public abstract class OutputData<T> implements Output<T> {
    private T data;
    private Long time;

    public Long getTime() {
        return time;
    }

    public OutputData<T> setTime(Long time) {
        this.time = time;
        return this;
    }

    public T getData() {
        return data;
    }

    public OutputData<T> setData(T data) {
        this.data = data;
        return this;
    }
}
