package com.theembers.iot.processor;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-14 14:59
 */
public abstract class InputData<T> implements Input<T> {
    private T data;
    private Long time;


    public T getData() {
        return data;
    }

    public InputData<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public InputData<T> setTime(Long time) {
        this.time = time;
        return this;
    }
}
