package com.theembers.iot.router;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:41
 */
public class TopicRule implements Rule {
    private String topic;

    public TopicRule(String topic) {
        this.topic = topic;
    }

    @Override
    public String key() {
        return topic;
    }
}
