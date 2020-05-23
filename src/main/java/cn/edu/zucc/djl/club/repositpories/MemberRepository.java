package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import cn.edu.zucc.djl.club.formbean.StuResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

public interface MemberRepository extends JpaRepository<MemberTableEntity,Integer> {

    //通过社团id查找社团成员
//    @Transactional
//    @Query(value = "select * from member_table where c_id=?1 limit ?2,10",nativeQuery = true)
//    @Modifying
//    List<MemberTableEntity> getMemList(int cid,int startIndex);

    //通过社团id查找社团成员的全部信息
    @Transactional
    @Query(value = "select s.u_name,s.phone,c.c_name,mt.join_date,mt.leave_date,mt.state,s.u_id" +
            " from member_table mt,student s,college c where mt.c_id=?1 and mt.u_id=s.u_id and s.college_id=c.c_id LIMIT ?2,10",nativeQuery = true)
    List<Object[]> getMemList(int cid, int startIndex);

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
