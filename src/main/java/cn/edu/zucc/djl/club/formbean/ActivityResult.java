package cn.edu.zucc.djl.club.formbean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ActivityResult  implements Serializable {

    private static final long serialVersionUID = 1L;
    private int aId;
    private String activityName;
    private String clubName;
    private String className;
    private Date applyDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer number;
    private Double budget;
    private String detial;
    private byte[] image;
    private Integer limit;
    private Integer aPass;
    private Integer bPass;
    private String collegeName;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public ActivityResult(){};
    public ActivityResult(int aid,String activityName, String clubName, String className, Date applyDate, Timestamp startTime,Timestamp endTime,Integer number,Double budget,
                          String detial,byte[] image,Integer limit,Integer aPass,Integer bPass,String collegeName){
        this.aId = aid;
        this.className = className;
        this.activityName = activityName;
        this.clubName = clubName;
        this.applyDate = applyDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.number = number;
        this.budget = budget;
        this.detial = detial;
        this.image = image;
        this.limit = limit;
        this.aPass = aPass;
        this.bPass = bPass;
        this.collegeName = collegeName;

    }


    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getaPass() {
        return aPass;
    }

    public void setaPass(Integer aPass) {
        this.aPass = aPass;
    }

    public Integer getbPass() {
        return bPass;
    }

    public void setbPass(Integer bPass) {
        this.bPass = bPass;
    }



    //Object转换为实体类的数组
    public static <T> List<T> objectToBean(List<Object[]> objList, Class<T> clz) throws Exception{
        if (objList==null || objList.size()==0) {
            return null;
        }

        Class<?>[] cz = null;
        Constructor<?>[] cons = clz.getConstructors();
        for (Constructor<?> ct : cons) {
            Class<?>[] clazz = ct.getParameterTypes();
            if (objList.get(0).length == clazz.length) {
                cz = clazz;
                break;
            }
        }

        List<T> list = new ArrayList<T>();
        for (Object[] obj : objList) {
            Constructor<T> cr = clz.getConstructor(cz);
            list.add(cr.newInstance(obj));
        }
        return list;
    }
}
