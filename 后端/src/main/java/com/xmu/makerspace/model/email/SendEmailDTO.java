package com.xmu.makerspace.model.email;

import java.util.ArrayList;

public class SendEmailDTO {
    String content;
    String topic;
    ArrayList<Integer> recipients;

    public ArrayList<Integer> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<Integer> recipients) {
        this.recipients = recipients;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
