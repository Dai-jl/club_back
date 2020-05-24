package cn.edu.zucc.djl.club.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "college", schema = "club", catalog = "")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class CollegeEntity {
    private int cId;
    private String cName;
    private Integer clubAdmin;

    @Id
    @Column(name = "c_id")
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "c_name")
    public String getcName(){return  cName;}

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "club_admin")
    public Integer getClubAdmin() {
        return clubAdmin;
    }

    public void setClubAdmin(Integer clubAdmin) {
        this.clubAdmin = clubAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollegeEntity that = (CollegeEntity) o;
        return cId == that.cId &&
                Objects.equals(cName, that.cName) &&
                Objects.equals(clubAdmin, that.clubAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, cName, clubAdmin);
    }
}
