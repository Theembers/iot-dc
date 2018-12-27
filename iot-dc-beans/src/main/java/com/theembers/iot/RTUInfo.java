package com.theembers.iot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.theembers.iot.enums.EMqExchange;

import java.util.Arrays;

/**
 * netty 对象
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-22 10:53
 */
public class RTUInfo<T> {
    /**
     * 物联网id
     */
    private String id;

    @JsonIgnore
    private String sn;
    /**
     * 指标信息
     */
    private T data;

    /**
     * 是否发布
     */
    @JsonIgnore
    private boolean publish;
    /**
     * 消息队列标示
     */
    @JsonIgnore
    private EMqExchange[] mqExchange;


    public RTUInfo(String id) {
        this.id = id;
        this.publish = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public EMqExchange[] getMqExchange() {
        return mqExchange;
    }

    public void setMqExchange(EMqExchange[] mqExchange) {
        this.mqExchange = mqExchange;
    }

    @Override
    public String toString() {
        return "RTUInfo{" +
                "id='" + id + '\'' +
                ", sn='" + sn + '\'' +
                ", data=" + data +
                ", publish=" + publish +
                ", mqExchange=" + Arrays.toString(mqExchange) +
                '}';
    }
}
