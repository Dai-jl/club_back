package cn.edu.zucc.djl.qa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "answer", schema = "system", catalog = "")
public class AnswerEntity {
    private Integer sid;
    private String content;
    private Integer creator;
    private Date creatorDate;
    private Date updateDate;
    private Integer questionSid;

    @Id
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "creator_date")
    public Date getCreatorDate() {
        return creatorDate;
    }

    public void setCreatorDate(Date creatorDate) {
        this.creatorDate = creatorDate;
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
    @Column(name = "question_sid")
    public Integer getQuestionSid() {
        return questionSid;
    }

    public void setQuestionSid(Integer questionSid) {
        this.questionSid = questionSid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerEntity that = (AnswerEntity) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(content, that.content) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(creatorDate, that.creatorDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(questionSid, that.questionSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, content, creator, creatorDate, updateDate, questionSid);
    }
}
