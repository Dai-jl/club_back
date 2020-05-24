package cn.edu.zucc.djl.qa.controller;

import cn.edu.zucc.djl.qa.entity.UserEntity;
import cn.edu.zucc.djl.qa.form.LoginForm;
import cn.edu.zucc.djl.qa.repositpories.UserRepository;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository repository;
    UserController(UserRepository repository){this.repository = repository;};

    @PostMapping("/user/login")
    public UserEntity Login(@RequestBody LoginForm loginForm){
        UserEntity user = repository.getOne(loginForm.getSno());
        if(user.getPassword().equals(loginForm.getPassword())) return user;
        return null;
    }
}
