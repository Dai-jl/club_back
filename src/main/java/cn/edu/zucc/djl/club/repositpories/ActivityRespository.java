package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ActivityRespository extends JpaRepository<ActivityEntity,Integer> {

    //活动通过
    @Transactional
    @Query(value = "update  activity set a_pass=1 where a_id=?1 ",nativeQuery = true)
    @Modifying
    boolean toPass(int aid);

    //取消活动
    @Transactional
    @Query(value = "update  activity set a_pass=0 where a_id=?1 ",nativeQuery = true)
    @Modifying
    boolean toCancel(int aid);

    //通过活动地点
    @Transactional
    @Query(value = "update  activity set b_pass=1 where a_id=?1 ",nativeQuery = true)
    @Modifying
    boolean toPassAddress(int aid);

    //取消活动地点
    @Transactional
    @Query(value = "update  activity set b_pass=0 where a_id=?1 ",nativeQuery = true)
    @Modifying
    boolean toCancelAddress(int aid);
}
