package cn.edu.zucc.djl.club.formbean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public class MemberTable {
    private int mId;
    private Integer cId;
    private String uId;
    private String type;
    private int state;
    private Date joinDate;
    private Date leaveDate;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState(){return state;}

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return joinDate;
    }

    public void setCreateDate(Date createDate) {
        this.joinDate = createDate;
    }

    public Date getUpdateDate() {
        return leaveDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.leaveDate = updateDate;
    }
}
