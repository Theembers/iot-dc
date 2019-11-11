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

    public Rule buildRule(String topic) {
        return new TopicRule(topic);
    }


    @Override
    public String key() {
        return topic;
    }
}
