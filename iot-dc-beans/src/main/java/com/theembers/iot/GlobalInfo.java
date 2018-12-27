package com.theembers.iot;

import io.netty.channel.ChannelId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-08 14:19
 */
public class GlobalInfo {

    public static String Global_Iot_Redis_Key;

    /**
     * 全局 物联网设备映射
     */
    public static Map<String, IotInfo> iotMapper;
    /**
     * 全局 netty channel 管理器
     * map{channelId, channelinfo}
     */
    public static final Map<ChannelId, RTUChannelInfo> CHANNEL_INFO_MAP = new ConcurrentHashMap<>();

    /**
     * 全局 netty channel 管理器
     * map{sn, channelinfo}
     */
    public static final Map<String, RTUChannelInfo> SN_CHANNEL_INFO_MAP = new ConcurrentHashMap<>();
}
