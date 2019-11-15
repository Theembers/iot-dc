package me.theembers.iot.bean;


import com.theembers.iot.collector.SourceData;
import me.theembers.iot.shadow.IotShadow;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-15 14:11
 */
public class IotMsgData implements SourceData<String> {
    private IotShadow shadow;
    private String dataJson;

    public IotShadow getShadow() {
        return shadow;
    }

    public IotMsgData setShadow(IotShadow shadow) {
        this.shadow = shadow;
        return this;
    }

    public String getDataJson() {
        return dataJson;
    }

    public IotMsgData setDataJson(String dataJson) {
        this.dataJson = dataJson;
        return this;
    }

    @Override
    public String getData() {
        return dataJson;
    }
}
