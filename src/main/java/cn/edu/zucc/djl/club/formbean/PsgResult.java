package cn.edu.zucc.djl.club.formbean;

import java.util.Date;

public class PsgResult {
     String name;
     String content;
     Byte[] img;
     String url;

    public PsgResult(){}

    public PsgResult(String name,String content,Byte[] img,String url){
        super();
        this.name=name;
        this.content=content;
        this.img=img;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Byte[] getImg() {
        return img;
    }

    public void setImg(Byte[] img) {
        this.img = img;
    }
}
