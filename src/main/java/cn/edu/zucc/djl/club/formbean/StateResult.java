package cn.edu.zucc.djl.club.formbean;

public class StateResult {
    private int code;
    private int cId;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public StateResult(int code){
        this.code = code;
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
