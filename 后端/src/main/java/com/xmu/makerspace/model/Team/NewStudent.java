package com.xmu.makerspace.model.Team;

/**
 * 团队申请时存放成员信息的类
 */
public class NewStudent {
    String name;
    String student_id;
    String college;
    String education_background;
    String phone;
    String email;

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public String getEducation_background() {
        return education_background;
    }

    public String getPhone() {
        return phone;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setEducation_background(String education_background) {
        this.education_background = education_background;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
