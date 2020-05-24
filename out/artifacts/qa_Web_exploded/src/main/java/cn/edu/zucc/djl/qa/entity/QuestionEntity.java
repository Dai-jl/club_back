package cn.edu.zucc.djl.qa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "question", schema = "system", catalog = "")
public class QuestionEntity {
    private Integer sid;
    private String title;
    private Integer creator;
    private Date createDate;
    private Date updateDate;
    private Integer bestAnswerSid;
    private Integer courseSid;

    @Id
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "creator")
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "best_answer_sid")
    public Integer getBestAnswerSid() {
        return bestAnswerSid;
    }

    public void setBestAnswerSid(Integer bestAnswerSid) {
        this.bestAnswerSid = bestAnswerSid;
    }

    @Basic
    @Column(name = "course_sid")
    public Integer getCourseSid() {
        return courseSid;
    }

    public void setCourseSid(Integer courseSid) {
        this.courseSid = courseSid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionEntity that = (QuestionEntity) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(title, that.title) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(bestAnswerSid, that.bestAnswerSid) &&
                Objects.equals(courseSid, that.courseSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, title, creator, createDate, updateDate, bestAnswerSid, courseSid);
    }
}
