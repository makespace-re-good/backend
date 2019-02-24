package com.xmu.makerspace.model.email;

public class NewTemplateDTO {
    int couldDelete;
    String topic;
    String content;

    public int getCouldDelete() {
        return couldDelete;
    }

    public void setCouldDelete(int couldDelete) {
        this.couldDelete = couldDelete;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
