package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberTableEntity,Integer> {
    List<MemberTableEntity> findBycIdOrderByStateDesc(int cid);
    boolean existsByCIdAndUId(int cid,String uid);


    @Transactional
    @Modifying
    @Query(value = "UPDATE  member_table SET state=0 ,leave_date = NOW() WHERE c_id = ? AND u_id = ?", nativeQuery = true)
    void updatestate(int cid,String uid);
}
