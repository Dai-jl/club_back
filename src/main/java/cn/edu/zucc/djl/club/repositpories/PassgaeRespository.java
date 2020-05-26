package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.ActivityEntity;
import cn.edu.zucc.djl.club.entity.PassgaeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface PassgaeRespository extends JpaRepository<PassgaeEntity,Integer> {

    //方式一
    //根据社团id获取活动推送内容，并按时间排序
    @Transactional
    @Query(value = "select p.p_id,p.name,p.content,p.time,p.url from passgae p where c_id=?1 order by time desc",nativeQuery = true)
    List<Object[]> getAllPas(int cid,int startIndex);

    //方式二：
    //根据社长给输入内容进行模糊查询
    @Transactional
    @Query(value = "select p.p_id,p.name,p.content,p.time,p.url from passgae p where c_id=?1 and (content like ?2 or name like ?2)",nativeQuery = true)
    List<Object[]> getLessPas(int cid,String keyStr);

    //发布社团推送
    @Transactional
    @Query(value = "insert into passgae(c_id,name,content,image,time,url) values(?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    @Modifying
    int addPsg(int cid, String name, String content, byte[] img,Date time,String url);

    //删除推送
    @Transactional
    @Query(value = "delete from passgae where p_id=?1",nativeQuery = true)
    @Modifying
    int deletePsg(int pid);

    //更改推送内容
    @Transactional
    @Query(value = "update passgae set name=?1,content=?2 where p_id=?3",nativeQuery = true)
    @Modifying
    int modifyPsg(String name,String content,int pid);
}
