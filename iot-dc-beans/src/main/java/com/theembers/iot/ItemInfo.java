package com.theembers.iot;

/**
 * 指标信息
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-22 10:54
 */
public class ItemInfo {
    /**
     * 指标 id
     */
    private String id;
    /**
     * 值
     */
    private String val;
    /**
     * 时间戳
     */
    private Long time;



    public ItemInfo(String id, String val) {
        this(id, val, System.currentTimeMillis());
    }

    public ItemInfo(String id, String val, Long time) {
        this.id = id;
        this.val = val;
        this.time = time;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "id='" + id + '\'' +
                ", val='" + val + '\'' +
                ", time=" + time +
                '}';
    }
}
