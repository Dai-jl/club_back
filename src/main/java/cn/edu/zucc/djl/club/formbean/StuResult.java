package cn.edu.zucc.djl.club.formbean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StuResult implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    String phone;
    String college;
    Date joinDate;
    Date leaveDate;
    int state;
    String id;

    public StuResult() {}

    /**
     * 构造器
     * 注意: 实体类中必须有无参数构造器，有参数构造器，且构造器参数的顺序和数据的顺序必须一致。如果数据格式有变化，需要重新编写一个实体类的构造器
     */
    public StuResult(String name, String phone, String college, Date joinDate,Date leaveDate,int state,String id) {
        super();
        this.name=name;
        this.phone=phone;
        this.college=college;
        this.joinDate=joinDate;
        this.leaveDate=leaveDate;
        this.state=state;
        this.id=id;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
