package cn.edu.zucc.djl.qa.repositpories;

import cn.edu.zucc.djl.qa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository <UserEntity,Integer>{

}
