package com.theembers.iot;

import java.util.Map;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-29 10:29
 */
public class IotInfo {
    private String id;
    private Map<String, String> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IotInfo{" +
                "id='" + id + '\'' +
                ", data=" + data +
                '}';
    }
}
