package cn.edu.zucc.djl.qa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course_list", schema = "system", catalog = "")
public class CourseListEntity {
    private Integer sid;
    private Integer courseSid;
    private Integer userSid;
    private String state;

    @Id
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "course_sid")
    public Integer getCourseSid() {
        return courseSid;
    }

    public void setCourseSid(Integer courseSid) {
        this.courseSid = courseSid;
    }

    @Basic
    @Column(name = "user_sid")
    public Integer getUserSid() {
        return userSid;
    }

    public void setUserSid(Integer userSid) {
        this.userSid = userSid;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseListEntity that = (CourseListEntity) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(courseSid, that.courseSid) &&
                Objects.equals(userSid, that.userSid) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, courseSid, userSid, state);
    }
}
