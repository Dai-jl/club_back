package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.ClassroomEntity;
import cn.edu.zucc.djl.club.entity.TimetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface TimetableRepository extends JpaRepository<TimetableEntity,Integer> {

    //获得活动场地审核未通过列表
    //为在进度中展示申请历史
    @Transactional
    @Query(value ="select c.r_id,c.name,t.start_time,t.end_time,t.reason from classroom c,timetable t where t.a_id=?1 and t.state=0 and t.r_id=c.r_id",nativeQuery = true)
    List<Object[]> getnotPass(int aid);

    //获得活动场地审核通过列表及已提交的场地
    //为前端选件时间判断场地冲突做准备
    @Transactional
    @Query(value = "select c.r_id,c.name,t.start_time,t.end_time,t.reason from classroom c,timetable t where (t.state=1 or t.state is null) and t.r_id=c.r_id",nativeQuery = true)
    List<Object[]> getPass();

    //提交活动场地申请
    @Transactional
    @Query(value = "insert into timetable(a_id,r_id,start_time,end_time) values(?1,?2,?3,?4)",nativeQuery = true)
    @Modifying
    int checkForRoom(int aid, int rid, Timestamp s,Timestamp e);

    //获得当前活动正在审核的活动场地的信息
    @Transactional
    @Query(value = "select c.r_id,c.name,t.start_time,t.end_time,t.reason from classroom c,timetable t where t.a_id=?1 and t.state is null and t.r_id=c.r_id",nativeQuery = true)
    List<Object[]> getWait(int aid);

    //取消活动申请,修改timetable的state状态
    @Transactional
    @Query(value = "update timetable set state = 0 ,reason='社自动取消申请' where a_id=?1",nativeQuery = true)
    @Modifying
    int cancelActivity2(int aid);

}
