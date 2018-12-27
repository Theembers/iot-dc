package com.theembers.iot.enums;

/**
 * RTU 通道标记
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-22 13:14
 */
public enum ERTUChannelFlag {
    SYS("0#".getBytes()),
    DISPLAY("1#".getBytes()),
    R485("2#".getBytes()),
    R232("3#".getBytes()),
    SIM("4#".getBytes());


    private byte[] flag;

    ERTUChannelFlag(byte[] flag) {
        this.flag = flag;
    }

    public byte[] getFlag() {
        return flag;
    }

    public void setFlag(byte[] flag) {
        this.flag = flag;
    }
}
