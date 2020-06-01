package cn.edu.zucc.djl.club.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "timetable", schema = "club", catalog = "")
public class TimetableEntity {
    private int tId;
    private int aId;
    private int rId;
    private Timestamp startTime;
    private Timestamp endTime;
    private int state;
    private String reason;

    @Id
    @Column(name = "t_id")
    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    @Basic
    @Column(name = "r_id")
    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    @Basic
    @Column(name = "a_id")
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "state")
    public int getState(){return state;}

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "reason")
    public String getReason(){return reason;}

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimetableEntity that = (TimetableEntity) o;
        return tId == that.tId &&
                aId == that.aId &&
                rId == that.rId &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                state == that.state &&
                reason ==that.reason;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tId, aId, rId, startTime, endTime, state, reason);
    }
}
