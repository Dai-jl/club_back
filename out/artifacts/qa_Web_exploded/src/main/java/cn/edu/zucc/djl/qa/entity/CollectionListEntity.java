package cn.edu.zucc.djl.qa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "collection_list", schema = "system", catalog = "")
public class CollectionListEntity {
    private Integer sid;
    private Integer answerSid;
    private Integer userSid;

    @Id
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "answer_sid")
    public Integer getAnswerSid() {
        return answerSid;
    }

    public void setAnswerSid(Integer answerSid) {
        this.answerSid = answerSid;
    }

    @Basic
    @Column(name = "user_sid")
    public Integer getUserSid() {
        return userSid;
    }

    public void setUserSid(Integer userSid) {
        this.userSid = userSid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionListEntity that = (CollectionListEntity) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(answerSid, that.answerSid) &&
                Objects.equals(userSid, that.userSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, answerSid, userSid);
    }
}
