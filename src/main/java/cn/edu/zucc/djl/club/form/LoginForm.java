package cn.edu.zucc.djl.club.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginForm {
    @ApiModelProperty(value = "管理员id")
    private  String id;
    @ApiModelProperty(value = "管理员登录密码")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
