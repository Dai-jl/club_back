package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.ClassroomEntity;
import cn.edu.zucc.djl.club.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity,Integer> {

    //展示没有冲突的场地（根据社长选择的时间进行判断）



}
