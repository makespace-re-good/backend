package com.xmu.makerspace.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "email_template", schema = "makerspace", catalog = "")
public class EmailTemplate {
    private int emailid;
    private String content;
    private String topic;
    private Integer couldDelete;

    @Id
    @Column(name = "EMAILID")
    public int getEmailid() {
        return emailid;
    }

    public void setEmailid(int emailid) {
        this.emailid = emailid;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "COULD_DELETE")
    public Integer getCouldDelete() {
        return couldDelete;
    }

    public void setCouldDelete(Integer couldDelete) {
        this.couldDelete = couldDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailTemplate that = (EmailTemplate) o;
        return emailid == that.emailid &&
                Objects.equals(content, that.content) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(couldDelete, that.couldDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailid, content, topic, couldDelete);
    }
}
