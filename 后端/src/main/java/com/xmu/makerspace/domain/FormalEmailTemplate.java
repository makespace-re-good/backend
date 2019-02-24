package com.xmu.makerspace.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "formal_email_template", schema = "makerspace", catalog = "")
public class FormalEmailTemplate {
    private int id;
    private String topic;
    private String content;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TOPIC")
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormalEmailTemplate that = (FormalEmailTemplate) o;
        return id == that.id &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, content);
    }
}
