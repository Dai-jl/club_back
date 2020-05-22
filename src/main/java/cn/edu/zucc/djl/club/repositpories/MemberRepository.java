package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MemberRepository extends JpaRepository<MemberTableEntity,Integer> {

    //通过社团id查找社团成员
    List<MemberTableEntity> findBycIdOrderByStateDesc(int cid);

    //转让社长
    //修改社长身份
    @Transactional
    @Query(value = "update member_table set type='member',leave_date=now() where u_id=?1 and c_id=?2",nativeQuery = true)
    @Modifying
    int transferMsgLeader(String uid,int cid);

    //修改成员身份
    @Transactional
    @Query(value = "update  member_table set type='leader' where u_id=?1 and c_id=?2",nativeQuery = true)
    @Modifying
    int transferMsgMem(String uid,int cid);

}
