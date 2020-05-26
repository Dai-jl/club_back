package cn.edu.zucc.djl.club.form;

public class PassageForm {
    private int pid;
    private int cid;
    private String name;
    private String content;
    private String url;
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
