package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.ActivityEntity;
import cn.edu.zucc.djl.club.entity.AdminEntity;
import cn.edu.zucc.djl.club.form.LoginForm;
import cn.edu.zucc.djl.club.formbean.ActivityResult;
import cn.edu.zucc.djl.club.formbean.StateResult;
import cn.edu.zucc.djl.club.repositpories.ActivityRespository;
import cn.edu.zucc.djl.club.repositpories.AdminRespository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
public class AdminController {
    final AdminRespository adminRespository;
    final ActivityRespository activityRespository;

    AdminController(AdminRespository adminRespository,ActivityRespository activityRespository){
        this.activityRespository = activityRespository;
        this.adminRespository = adminRespository;
    }


    //管理员登陆，djl
    @PostMapping("/api/admin/login")
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

    //待审核活动 djl
    @GetMapping("/api/admin/waittopassa")
    @CrossOrigin
    public List<ActivityResult> waitToPass(){
        List<Object[]> res = activityRespository.findwaitToPass();
        try{
            List<ActivityResult> list = ActivityResult.objectToBean(res,ActivityResult.class);
//            System.out.println(list+"ok");
            return list;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    //通过活动 djl
    @PostMapping("/api/admin/passactivity/{aid}")
    @CrossOrigin
    public StateResult pass(@PathVariable int aid){
        if(activityRespository.toPass(aid)){
            return new StateResult(200);
        }
        return new StateResult(400);
    }

    //取消活动 djl
    @PostMapping("/api/admin/cancelactivity/{aid}")
    @CrossOrigin
    public StateResult cancel(@PathVariable int aid){
        if(activityRespository.toCancel(aid)){
            return new StateResult(200);
        }
        return new StateResult(400);
    }

    //通过地点 djl
    @PostMapping("/api/admin/passaddress/{aid}")
    @CrossOrigin
    public StateResult passaddress(@PathVariable int aid){
        if(activityRespository.toPassAddress(aid)){
            return new StateResult(200);
        }
        return new StateResult(400);
    }

    //取消地点 djl
    @PostMapping("/api/admin/canceladdress/{aid}")
    @CrossOrigin
    public StateResult canceladdress(@PathVariable int aid){
        if(activityRespository.toCancelAddress(aid)){
            return new StateResult(200);
        }
        return new StateResult(400);
    }



}
