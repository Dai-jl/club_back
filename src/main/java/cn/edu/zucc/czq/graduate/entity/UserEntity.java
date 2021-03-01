package cn.edu.zucc.czq.graduate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "deal", catalog = "")
public class UserEntity {
    private int iid;
    private String name;
    private String cclass;
    private String major;
    private String pic;
    private String phone;
    private String address;

    @Id
    @Column(name = "iid", nullable = false)
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "cclass", nullable = true, length = 255)
    public String getCclass() {
        return cclass;
    }

    public void setCclass(String cclass) {
        this.cclass = cclass;
    }

    @Basic
    @Column(name = "major", nullable = true, length = 255)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "pic", nullable = true, length = 255)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return iid == that.iid &&
                Objects.equals(name, that.name) &&
                Objects.equals(cclass, that.cclass) &&
                Objects.equals(major, that.major) &&
                Objects.equals(pic, that.pic) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iid, name, cclass, major, pic, phone, address);
    }
}
