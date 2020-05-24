package cn.edu.zucc.djl.club.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "passgae", schema = "club", catalog = "")
public class PassgaeEntity {
    private int pId;
    private Integer cId;
    private String name;
    private String content;
    private byte[] image;

    @Id
    @Column(name = "p_id")
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassgaeEntity that = (PassgaeEntity) o;
        return pId == that.pId &&
                Objects.equals(cId, that.cId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(content, that.content) &&
                Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(pId, cId, name, content);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
