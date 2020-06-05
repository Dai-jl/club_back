package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import cn.edu.zucc.djl.club.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface StudentRepository extends JpaRepository<StudentEntity,String> {

    @Transactional
    @Query(value = "update student set phone=?2 where u_id=?1",nativeQuery = true)
    @Modifying
    int editMember(String uid,String phone);
}
