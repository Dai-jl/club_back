package cn.edu.zucc.djl.club.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "passgae", schema = "club", catalog = "")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PassgaeEntity {
    private int pId;
    private int cId;
    private String name;
    private String content;
    private byte[] image;
    private Date time;
    private String url;

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

    @Basic
    @Column(name = "time")
    public Date getTime(){return time;}

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "url")
    public String getUrl(){return url;}

    public void setUrl(String url) {
        this.url = url;
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
                Arrays.equals(image, that.image) &&
                Objects.equals(time,that.time) &&
                Objects.equals(url,that.url);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(pId, cId, name, content,time,url);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
