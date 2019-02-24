package com.xmu.makerspace.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by status200 on 2017/8/30.
 */
public class BackendIndexVO {

    // CAUTION:转为json时日期格式化，似乎把对象放在另一个对象中时,@JsonFormat会失效,需要加上@JsonSerialize
    // CAUTION:参见 https://stackoverflow.com/questions/38838037/jsonformat-not-working-in-nested-object
    @JsonSerialize(as = Date.class)
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date beginDate;

    @JsonSerialize(as = Date.class)
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date endDate;

    private int termId;

    private short teamNumber;


    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }


    public short getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(short teamNumber) {
        this.teamNumber = teamNumber;
    }
}
