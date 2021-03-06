package cn.edu.zucc.djl.club.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "activity", schema = "club", catalog = "")
public class ActivityEntity {
    private int aId;
    private String name;
    private Integer cId;
    private Integer rId;
    private Date applyDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer number;
    private Double budget;
    private String detial;
    private byte[] image;
    private Integer limitt;
    private Integer aPass;
    private Integer bPass;

    @Id
    @Column(name = "a_id")
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    @Basic
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "c_id")
    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "r_id")
    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    @Basic
    @CreatedDate
    @Column(name = "apply_date")
    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "budget")
    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Basic
    @Column(name = "detial")
    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    @Basic
    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "limitt")
    public Integer getLimit() {
        return limitt;
    }

    public void setLimit(Integer limit) {
        this.limitt = limit;
    }

    @Basic
    @Column(name = "a_pass")
    public Integer getaPass() {
        return aPass;
    }

    public void setaPass(Integer aPass) {
        this.aPass = aPass;
    }

    @Basic
    @Column(name = "b_pass")
    public Integer getbPass() {
        return bPass;
    }

    public void setbPass(Integer bPass) {
        this.bPass = bPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return aId == that.aId &&
                Objects.equals(name,that.name)&&
                Objects.equals(cId, that.cId) &&
                Objects.equals(rId, that.rId) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(number, that.number) &&
                Objects.equals(budget, that.budget) &&
                Objects.equals(detial, that.detial) &&
                Arrays.equals(image, that.image) &&
                Objects.equals(limitt, that.limitt) &&
                Objects.equals(aPass, that.aPass) &&
                Objects.equals(bPass, that.bPass);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(aId, name,cId, rId, startTime, endTime, number, budget, detial, limitt, aPass, bPass);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
