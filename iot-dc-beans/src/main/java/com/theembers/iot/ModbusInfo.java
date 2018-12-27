package com.theembers.iot;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;

/**
 * modbus 数据模型
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-23 14:16
 */
public class ModbusInfo {
    private byte[] address;
    private byte[] command;
    private byte[] length;
    private byte[] data;
    private byte[] crc;
    private byte[] fullData;

    private ByteBuf source;

    {
        address = new byte[ModBusModel.ADDRESS_LEN.len];
        command = new byte[ModBusModel.COMMAND_LEN.len];
        length = new byte[ModBusModel.LENGTH_LEN.len];
        crc = new byte[ModBusModel.CRC_LEN.len];
    }

    public ModbusInfo(ByteBuf source) {
        this.source = source;
        this.data = new byte[source.readableBytes() - address.length - command.length - length.length - crc.length];
        this.fullData = new byte[source.readableBytes() - crc.length];

        this.source.readBytes(address)
                // command
                .readBytes(command)
                // length
                .readBytes(length)
                // data
                .readBytes(data)
                // crc
                .readBytes(crc);
        // fullData
        this.source.resetReaderIndex();
        this.source.readBytes(fullData);

    }

    private enum ModBusModel {
        ADDRESS_LEN(1),
        COMMAND_LEN(1),
        LENGTH_LEN(1),
        CRC_LEN(2);

        int len;

        ModBusModel(int len) {
            this.len = len;
        }
    }

    public byte[] getAddress() {
        return address;
    }

    public byte[] getCommand() {
        return command;
    }

    public byte[] getLength() {
        return length;
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getCrc() {
        return crc;
    }

    public byte[] getFullData() {
        return fullData;
    }

    @Override
    public String toString() {
        return "ModbusInfo{" +
                "address=" + Arrays.toString(address) +
                ", command=" + Arrays.toString(command) +
                ", length=" + Arrays.toString(length) +
                ", data=" + Arrays.toString(data) +
                ", crc=" + Arrays.toString(crc) +
                '}';
    }
}
