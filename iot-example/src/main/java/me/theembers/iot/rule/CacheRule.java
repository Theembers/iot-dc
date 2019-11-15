package me.theembers.iot.rule;

import com.theembers.iot.router.rule.MultipleRule;

import java.util.Map;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-15 15:32
 */
public class CacheRule implements MultipleRule {
    private Map<String, String[]> keys;

    public Map<String, String[]> getKeys() {
        return keys;
    }

    public CacheRule setKeys(Map<String, String[]> keys) {
        this.keys = keys;
        return this;
    }

    @Override
    public Map<String, String[]> key() {
        return keys;
    }
}
