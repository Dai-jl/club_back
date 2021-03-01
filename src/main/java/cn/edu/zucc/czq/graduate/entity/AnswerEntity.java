package cn.edu.zucc.czq.graduate.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "answer", schema = "deal", catalog = "")
public class AnswerEntity {
    private int iid;
    private String qid;
    private Integer uid;
    private String content;
    private String like;
    private String dislike;
    private String picture;
    private Timestamp time;

    @Id
    @Column(name = "iid", nullable = false)
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    @Basic
    @Column(name = "qid", nullable = true, length = 255)
    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    @Basic
    @Column(name = "uid", nullable = true)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "like", nullable = true, length = 255)
    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    @Basic
    @Column(name = "dislike", nullable = true, length = 255)
    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    @Basic
    @Column(name = "picture", nullable = true, length = 255)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerEntity that = (AnswerEntity) o;
        return iid == that.iid &&
                Objects.equals(qid, that.qid) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(content, that.content) &&
                Objects.equals(like, that.like) &&
                Objects.equals(dislike, that.dislike) &&
                Objects.equals(picture, that.picture) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iid, qid, uid, content, like, dislike, picture, time);
    }
}
