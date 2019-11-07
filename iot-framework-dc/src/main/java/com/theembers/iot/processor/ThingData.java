package com.theembers.iot.processor;

import java.util.Collections;
import java.util.List;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-07 16:57
 */
public class ThingData<T> implements Input<T> {
    private T data;


    public T getData() {
        return data;
    }

    public ThingData<T> setData(T data) {
        this.data = data;
        return this;
    }

    List<T> buildDataEntity(Class<T> clazz) {
        return Collections.singletonList(data);
    }
}
