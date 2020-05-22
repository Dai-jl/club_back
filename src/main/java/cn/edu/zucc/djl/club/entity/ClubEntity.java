package cn.edu.zucc.djl.club.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "club", schema = "club", catalog = "")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ClubEntity {
    private int cId;
    private String name;
    private byte[] logo;
    private String description;
    private Integer bookNum;
    private Integer level;
    private Integer mainActivity;
    private Integer collegeId;

    @Id
    @Column(name = "c_id")
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "logo")
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "book_num")
    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "main_activity")
    public Integer getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(Integer mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Basic
    @Column(name = "college_id")
    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubEntity that = (ClubEntity) o;
        return cId == that.cId &&
                Objects.equals(name, that.name) &&
                Arrays.equals(logo, that.logo) &&
                Objects.equals(description, that.description) &&
                Objects.equals(bookNum, that.bookNum) &&
                Objects.equals(level, that.level) &&
                Objects.equals(mainActivity, that.mainActivity) &&
                Objects.equals(collegeId, that.collegeId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(cId, name, description, bookNum, level, mainActivity, collegeId);
        result = 31 * result + Arrays.hashCode(logo);
        return result;
    }
}
