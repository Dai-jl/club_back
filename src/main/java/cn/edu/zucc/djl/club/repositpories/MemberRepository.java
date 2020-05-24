package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberTableEntity,Integer> {
    List<MemberTableEntity> findBycIdOrderByStateDesc(int cid);

    MemberTableEntity findByUId(String uid);
}
