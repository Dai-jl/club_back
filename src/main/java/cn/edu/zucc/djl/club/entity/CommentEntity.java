package cn.edu.zucc.djl.club.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "club", catalog = "")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class CommentEntity {
    private int comId;
    private Integer pId;
    private String uId;
    private String content;
    private String u2Id;

    @Id
    @Column(name = "com_id")
    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    @Basic
    @Column(name = "p_id")
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "u2_id")
    public String getU2Id() {
        return u2Id;
    }

    public void setU2Id(String u2Id) {
        this.u2Id = u2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return comId == that.comId &&
                Objects.equals(pId, that.pId) &&
                Objects.equals(uId, that.uId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(u2Id, that.u2Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comId, pId, uId, content, u2Id);
    }
}
