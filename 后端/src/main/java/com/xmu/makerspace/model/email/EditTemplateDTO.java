package com.xmu.makerspace.model.email;

public class EditTemplateDTO {
    int id;
    String topic;
    String content;

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
