package com.theembers.iot;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-25 16:10
 */
public class RTUChannelInfo {
    private ChannelId channelId;
    private String sn;
    private IotInfo iotInfo;
    private Channel channel;

    public static RTUChannelInfo build(String sn, ChannelId channelId) {
        return new RTUChannelInfo(sn, channelId);
    }

    private RTUChannelInfo(String sn, ChannelId channelId) {
        this.channelId = channelId;
        this.sn = sn;
    }

    public ChannelId getChannelId() {
        return channelId;
    }

    public RTUChannelInfo setChannelId(ChannelId channelId) {
        this.channelId = channelId;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public RTUChannelInfo setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public Channel getChannel() {
        return channel;
    }

    public RTUChannelInfo setChannel(Channel channel) {
        this.channel = channel;
        return this;
    }

    public IotInfo getIotInfo() {
        return iotInfo;
    }

    public RTUChannelInfo setIotInfo(IotInfo iotInfo) {
        this.iotInfo = iotInfo;
        return this;
    }

    @Override
    public String toString() {
        return "RTUChannelInfo{" +
                "channelId=" + channelId +
                ", sn='" + sn + '\'' +
                ", iotInfo=" + iotInfo +
                ", channel=" + channel +
                '}';
    }
}
