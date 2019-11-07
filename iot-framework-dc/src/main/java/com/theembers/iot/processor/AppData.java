package com.theembers.iot.processor;

import java.util.List;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-07 17:01
 */
public class AppData<T> implements Output<T> {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public AppData<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public static AppData builder() {
        return new AppData<>();
    }
}
