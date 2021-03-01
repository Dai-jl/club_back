package cn.edu.zucc.czq.graduate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commodity", schema = "deal", catalog = "")
public class CommodityEntity {
    private int iid;
    private String address;
    private Integer number;
    private String pic;

    @Id
    @Column(name = "iid", nullable = false)
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "number", nullable = true)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "pic", nullable = true, length = 255)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityEntity that = (CommodityEntity) o;
        return iid == that.iid &&
                Objects.equals(address, that.address) &&
                Objects.equals(number, that.number) &&
                Objects.equals(pic, that.pic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iid, address, number, pic);
    }
}
