package cn.edu.zucc.djl.club.formbean;

public class StateResult {
    private int code;
    private int cId;
    private String adminType;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType,String token) {
        this.adminType = adminType;
        this.token = token;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public StateResult(int code){
        this.code = code;
    }
    public StateResult(int code,String adminType,String token){
        this.code = code;
        this.adminType = adminType;
        this.token = token;
    }
    public StateResult(int code,int cId){
        this.code = code;
        this.cId = cId;
    }
    public int getCode(){
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }
}
