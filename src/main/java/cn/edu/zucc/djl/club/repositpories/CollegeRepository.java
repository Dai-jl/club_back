package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<CollegeEntity,Integer> {

    //通过id找学校信息
    CollegeEntity getOne(int cid);

}
