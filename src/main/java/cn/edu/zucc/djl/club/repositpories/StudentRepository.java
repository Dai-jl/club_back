package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository  extends JpaRepository<StudentEntity,String> {

    //通过学号找学生
    StudentEntity getOne(String uid);

}
