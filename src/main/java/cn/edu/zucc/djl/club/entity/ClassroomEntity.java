package cn.edu.zucc.djl.club.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "classroom", schema = "club", catalog = "")
public class ClassroomEntity {
    private int rId;
    private String name;
    private int capacity;
    private int ohp;
    private Integer adminId;

    @Id
    @Column(name = "r_id")
    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
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
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "OHP")
    public int getOhp() {
        return ohp;
    }

    public void setOhp(int ohp) {
        this.ohp = ohp;
    }

    @Basic
    @Column(name = "admin_id")
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomEntity that = (ClassroomEntity) o;
        return rId == that.rId &&
                capacity == that.capacity &&
                ohp == that.ohp &&
                Objects.equals(name, that.name) &&
                Objects.equals(adminId, that.adminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rId, name, capacity, ohp, adminId);
    }
}
