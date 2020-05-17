package cn.edu.zucc.djl.club.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "join_activity", schema = "club", catalog = "")
public class JoinActivityEntity {
    private int jId;
    private String uId;
    private Integer aId;

    @Id
    @Column(name = "j_id")
    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
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
    @Column(name = "a_id")
    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinActivityEntity that = (JoinActivityEntity) o;
        return jId == that.jId &&
                Objects.equals(uId, that.uId) &&
                Objects.equals(aId, that.aId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jId, uId, aId);
    }
}
