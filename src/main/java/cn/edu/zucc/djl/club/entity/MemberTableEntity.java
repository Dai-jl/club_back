package cn.edu.zucc.djl.club.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "member_table", schema = "club", catalog = "")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class MemberTableEntity {
    private int mId;
    private Integer cId;
    private String uId;
    private String type;
    private int state;
    private Date joinDate;
    private Date leaveDate;

    @Id
    @Column(name = "m_id")
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    @Basic
    @Column(name = "c_id")
    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "u_id")
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name="state")
    public int getState(){return state;}

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "join_date")
    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date createDate) {
        this.joinDate = createDate;
    }

    @Basic
    @Column(name = "leave_date")
    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date updateDate) {
        this.leaveDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberTableEntity that = (MemberTableEntity) o;
        return mId == that.mId &&
                Objects.equals(cId, that.cId) &&
                Objects.equals(uId, that.uId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(state,that.state) &&
                Objects.equals(joinDate,that.joinDate) &&
                Objects.equals(leaveDate,that.leaveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, cId, uId, type);
    }
}
