package cn.edu.zucc.djl.club.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PassageForm {
    @ApiModelProperty(value = "推送id")
    private int pid;
    @ApiModelProperty(value = "社团id")
    private int cid;
    @ApiModelProperty(value = "推送标题")
    private String name;
    @ApiModelProperty(value = "推送内容概括")
    private String content;
    @ApiModelProperty(value = "推送链接")
    private String url;
    @ApiModelProperty(value = "推送图像")
    private byte[] img;

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
