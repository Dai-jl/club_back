package cn.edu.zucc.djl.qa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "system")
public class UserEntity {
    private Integer sid;
    private String sno;
    private String password;
    private String name;
    private String userType;
    private String email;
    private String phone;
    private String column8;

    @Id
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "sno")
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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
    @Column(name = "user_type")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "Column_8")
    public String getColumn8() {
        return column8;
    }

    public void setColumn8(String column8) {
        this.column8 = column8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(sno, that.sno) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(column8, that.column8);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, sno, password, name, userType, email, phone, column8);
    }
}
