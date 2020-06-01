package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.ActivityEntity;
import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface ActivityRespository extends JpaRepository<ActivityEntity,Integer> {
    //sx
    //活动未通过列表（活动未审核或地点未审核）
    @Transactional
    @Query(value = "select c.a_id,c.activity_name,club.`name` club_name,c.`name` class_name,c.apply_date,c.start_time,c.end_time,c.number,c.budget,c.detial,c.image,c.limitt,c.a_pass,c.b_pass,club.college_name from \n" +
            "            (select a.a_id,a.`name` activity_name,a.c_id,b.`name`,a.start_time,a.end_time,a.number,a.budget,a.detial,a.image,a.limitt,a.a_pass,a.b_pass,a.apply_date from activity a left join classroom  b on a.r_id = b.r_id) as c left join\n" +
            "            (select club.c_id,club.`name`,college.c_name college_name,club.admin_id from club left join college on club.college_id = college.c_id) as club\n" +
            "            on c.c_id = club.c_id where  club.c_id=?1 and (c.a_pass is null or c.b_pass is null)",nativeQuery = true)
    List<Object[]> findwaitToabPass(int cid);

    //活动审核全部通过列表
    @Transactional
    @Query(value = "select c.a_id,c.activity_name,club.`name` club_name,c.`name` class_name,c.apply_date,c.start_time,c.end_time,c.number,c.budget,c.detial,c.image,c.limitt,c.a_pass,c.b_pass,club.college_name from \n" +
            "            (select a.a_id,a.`name` activity_name,a.c_id,b.`name`,a.start_time,a.end_time,a.number,a.budget,a.detial,a.image,a.limitt,a.a_pass,a.b_pass,a.apply_date from activity a left join classroom  b on a.r_id = b.r_id) as c left join\n" +
            "            (select club.c_id,club.`name`,college.c_name college_name,club.admin_id from club left join college on club.college_id = college.c_id) as club\n" +
            "            on c.c_id = club.c_id where  club.c_id=?1 and (c.a_pass = 1 and c.b_pass = 1) ",nativeQuery = true)
    List<Object[]> findisabPass(int cid);

    //活动申请
    @Transactional
    @Query(value = "insert into activity(name,c_id,r_id,apply_date,start_time,end_time,number,budget,detial,limitt) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)",nativeQuery = true)
    @Modifying
    int addActivity(String name,int cid, int rid, Date app, Timestamp start,Timestamp end,int num,double bud,String de,int li);


    //djl
    //活动未审核列表
    @Transactional
    @Query(value = "select c.a_id,c.activity_name,club.`name` club_name,c.`name` class_name,c.apply_date,c.start_time,c.end_time,c.number,c.budget,c.detial,c.image,c.limitt,c.a_pass,c.b_pass,club.college_name from \n" +
            "            (select a.a_id,a.`name` activity_name,a.c_id,b.`name`,a.start_time,a.end_time,a.number,a.budget,a.detial,a.image,a.limitt,a.a_pass,a.b_pass,a.apply_date from activity a left join classroom  b on a.r_id = b.r_id) as c left join\n" +
            "            (select club.c_id,club.`name`,college.c_name college_name,club.admin_id from club left join college on club.college_id = college.c_id) as club\n" +
            "            on c.c_id = club.c_id where  club.admin_id = ?1 and c.a_pass is null",nativeQuery = true)
    List<Object[]>  findwaitToaPass(String aid);

    //活动已审核列表
    @Transactional
    @Query(value = "select c.a_id,c.activity_name,club.`name` club_name,c.`name` class_name,c.apply_date,c.start_time,c.end_time,c.number,c.budget,c.detial,c.image,c.limitt,c.a_pass,c.b_pass,club.college_name from \n" +
            "            (select a.a_id,a.`name` activity_name,a.c_id,b.`name`,a.start_time,a.end_time,a.number,a.budget,a.detial,a.image,a.limitt,a.a_pass,a.b_pass,a.apply_date from activity a left join classroom  b on a.r_id = b.r_id) as c left join\n" +
            "            (select club.c_id,club.`name`,college.c_name college_name,club.admin_id from club left join college on club.college_id = college.c_id) as club\n" +
            "            on c.c_id = club.c_id where  club.admin_id = ?1 and c.a_pass is null",nativeQuery = true)
    List<Object[]>  findAlreadyaPass(String aid);

    //地点未审核列表
    @Transactional
    @Query(value = "select c.a_id,c.activity_name,club.`name` club_name,c.`name` class_name,c.apply_date,c.start_time,c.end_time,c.number,c.budget,c.detial,c.image,c.limitt,c.a_pass,c.b_pass,club.college_name from \n" +
            "(select a.a_id,a.`name` activity_name,a.c_id,b.`name`,a.start_time,a.end_time,a.number,a.budget,a.detial,a.image,a.limitt,a.a_pass,a.b_pass,a.apply_date from activity a left join (select * from classroom where admin_id = ?1) b on a.r_id = b.r_id) as c left join \n" +
            "(select club.c_id,club.`name`,college.c_name college_name from club left join college on club.college_id = college.c_id) as club\n" +
            "on c.c_id = club.c_id where c.b_pass is null and c.a_pass = 1",nativeQuery = true)
    List<Object[]>  findwaitToPass(String aid);

    //地点已审核列表
    @Transactional
    @Query(value = "select c.a_id,c.activity_name,club.`name` club_name,c.`name` class_name,c.apply_date,c.start_time,c.end_time,c.number,c.budget,c.detial,c.image,c.limitt,c.a_pass,c.b_pass,club.college_name from \n" +
            "(select a.a_id,a.`name` activity_name,a.c_id,b.`name`,a.start_time,a.end_time,a.number,a.budget,a.detial,a.image,a.limitt,a.a_pass,a.b_pass,a.apply_date from activity a left join (select * from classroom where admin_id = ?1) b on a.r_id = b.r_id) as c left join \n" +
            "(select club.c_id,club.`name`,college.c_name college_name from club left join college on club.college_id = college.c_id) as club\n" +
            "on c.c_id = club.c_id where c.b_pass is not null",nativeQuery = true)
    List<Object[]>  findAlreadyPass(String aid);

    //活动通过
    @Transactional
    @Query(value = "update  activity set a_pass=1 where a_id=?1 ",nativeQuery = true)
    @Modifying
    int toPass(int aid);

    //取消活动
    @Transactional
    @Query(value = "update  activity set a_pass=0 where a_id=?1 ",nativeQuery = true)
    @Modifying
    int toCancel(int aid);

    //通过活动地点
    @Transactional
    @Query(value = "update  activity set b_pass=1 where a_id=?1 ",nativeQuery = true)
    @Modifying
    int toPassAddress(int aid);

    //取消活动地点
    @Transactional
    @Query(value = "update  activity set b_pass=0 where a_id=?1 ",nativeQuery = true)
    @Modifying
    int toCancelAddress(int aid);
}
