package cn.edu.zucc.djl.club.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MemeditForm {
    @ApiModelProperty(value = "成员id")
    String id;
    @ApiModelProperty(value = "成员联系方式")
    String phone;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
