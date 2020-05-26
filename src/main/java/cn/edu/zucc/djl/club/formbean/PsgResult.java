package cn.edu.zucc.djl.club.formbean;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PsgResult {
     int pid;
     String name;
     String content;
     Date time;
     String url;

    public PsgResult(){}

    public PsgResult(int pid,String name,String content,Date time,String url){
        super();
        this.pid=pid;
        this.name=name;
        this.content=content;
        this.time=time;
        this.url=url;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
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

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
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
