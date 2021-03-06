package cn.edu.zucc.djl.club.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "admin", schema = "club", catalog = "")
public class AdminEntity {
    private String id;
    private String password;
    private String type;
    public static AdminEntity currentAdmin;
    public static void setCurrentAdmin(AdminEntity adminEntity){
        AdminEntity.currentAdmin = adminEntity;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminEntity that = (AdminEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(password, that.password) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, type);
    }


    public String getToken(AdminEntity admin){
        String token = "";
        token = JWT.create().withAudience(admin.getId())
                .sign(Algorithm.HMAC256(admin.getPassword()));
        return token;
    }
}
