package cn.edu.zucc.djl.club.formbean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class AdminActivity  implements Serializable {

//    private static final long serialVersionUID = 1L;
    private int tId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String className;
    private Integer state;
    private String reason;
    private Integer aId;
    private String activityName;
    private String clubName;
    private Integer number;
    private Double budget;
    private String detial;
    private byte[] image;
    private Integer limitt;
    private String collegeName;
    private Integer aPass;

    public Integer getaPass() {
        return aPass;
    }

    public void setaPass(Integer aPass) {
        this.aPass = aPass;
    }

    public AdminActivity(){};
    public AdminActivity(Timestamp startTime, Timestamp endTime,Integer aId, String activityName, String clubName, Integer number, Double budget, String detial, byte[] image, Integer limitt, String collegeName,Integer aPass){
        this.startTime = startTime;
        this.endTime = endTime;
        this.aId = aId;
      this.activityName = activityName;
      this.clubName = clubName;
      this.number = number;
      this.budget = budget;
      this.detial = detial;
      this.image = image;
      this.limitt = limitt;
      this.collegeName = collegeName;
      this.aPass = aPass;
    };
    public AdminActivity(int tId, Timestamp startTime, Timestamp endTime, String className, Integer state, String reason, Integer aId, String activityName, String clubName, Integer number, Double budget, String detial, byte[] image, Integer limitt, String collegeName,Integer aPass){
        this.tId = tId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.className = className;
        this.state = state;
        this.reason = reason;
        this.aId = aId;
        this.activityName = activityName;
        this.clubName = clubName;
        this.number = number;
        this.budget = budget;
        this.detial = detial;
        this.image = image;
        this.limitt = limitt;
        this.collegeName = collegeName;
        this.aPass = aPass;
    };

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getLimitt() {
        return limitt;
    }

    public void setLimitt(Integer limitt) {
        this.limitt = limitt;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


}
