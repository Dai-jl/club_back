package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.AdminEntity;
import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import cn.edu.zucc.djl.club.entity.StudentEntity;
import cn.edu.zucc.djl.club.form.LoginForm;
import cn.edu.zucc.djl.club.formbean.StateResult;
import cn.edu.zucc.djl.club.repositpories.AdminRespository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AdminController {
    final AdminRespository adminRespository;

    AdminController(AdminRespository adminRespository){
        this.adminRespository = adminRespository;
    }


    //管理员登陆，djl
    @PostMapping("/api/leader/login")
    @CrossOrigin
    public StateResult login(@RequestBody LoginForm loginForm){
        String id = loginForm.getId();
        AdminEntity adminEntity = adminRespository.getOne(id);
        if(loginForm.getPassword().equals(adminEntity.getPassword())){
            AdminEntity.setCurrentAdmin(adminEntity);
            return new StateResult(200);
        }
        return new StateResult(400);

    }
}
