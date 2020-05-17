package cn.edu.zucc.djl.club.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "club", catalog = "")
public class StudentEntity {
    private String uId;
    private String password;
    private String name;
    private String phone;
    private byte[] image;
    private Integer collegeId;

    @Id
    @Column(name = "u_id")
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(uId, that.uId) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Arrays.equals(image, that.image) &&
                Objects.equals(collegeId, that.collegeId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(uId, password, name, phone, collegeId);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}