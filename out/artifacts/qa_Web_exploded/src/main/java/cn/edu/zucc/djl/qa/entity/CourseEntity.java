package cn.edu.zucc.djl.qa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "system", catalog = "")
public class CourseEntity {
    private Integer sid;
    private String name;
    private Integer teacher;

    @Id
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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
    @Column(name = "teacher")
    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, name, teacher);
    }
}
