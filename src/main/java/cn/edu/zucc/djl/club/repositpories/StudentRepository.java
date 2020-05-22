package cn.edu.zucc.djl.club.repositpories;

import cn.edu.zucc.djl.club.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository  extends JpaRepository<StudentEntity,String> {
}
