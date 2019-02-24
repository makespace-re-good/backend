package com.xmu.makerspace.model;

import java.sql.Date;

/**
 * Created by status200 on 2017/8/30.
 */
public class NewApplicationRoundDTO {
    private Date beginDate;

    private Date endDate;

    private int termId;

    public NewApplicationRoundDTO(Date beginDate,Date endDate,int termId)
    {
              this.beginDate=beginDate;
              this.endDate=endDate;
              this.termId=termId;
    }
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
}
