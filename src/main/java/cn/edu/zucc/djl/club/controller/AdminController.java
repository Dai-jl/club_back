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
    @GetMapping("/api/admin/waittopassa/{aid}")
    @CrossOrigin
    public List<ActivityResult> waitToPass(@PathVariable String aid){
        AdminEntity adminEntity = adminRespository.getOne(aid);
        List<Object[]> res;
        if(adminEntity.getType().equals("class")){
            res = activityRespository.findwaitToPass(aid);
        }
        else {
            res = activityRespository.findwaitToaPass(aid);
        }
        try{
            List<ActivityResult> list = ActivityResult.objectToBean(res,ActivityResult.class);
            return list;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


    //待审核活动 djl
    @GetMapping("/api/admin/passa/{aid}")
    @CrossOrigin
    public List<ActivityResult> alredyPass(@PathVariable String aid){
        AdminEntity adminEntity = adminRespository.getOne(aid);
        List<Object[]> res;
        if(adminEntity.getType().equals("class")){
            res = activityRespository.findAlreadyPass(aid);
        }
        else{
            res = activityRespository.findAlreadyaPass(aid);
        }
        try{
            List<ActivityResult> list = ActivityResult.objectToBean(res,ActivityResult.class);
            return list;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    //通过活动 djl
    @PostMapping("/api/admin/passactivity/{adminid}/{aid}")
    @CrossOrigin
    public StateResult pass(@PathVariable int aid,@PathVariable String adminid){
        AdminEntity adminEntity = adminRespository.getOne(adminid);
        if(adminEntity.getType().equals("class")){
            if(activityRespository.toPassAddress(aid) != 0){
                return new StateResult(200);
            }
            return new StateResult(400);
        }
        if(activityRespository.toPass(aid)!=0){
            return new StateResult(200);
        }
        return new StateResult(400);
    }

    //取消活动 djl
    @PostMapping("/api/admin/cancelactivity/{adminid}/{aid}")
    @CrossOrigin
    public StateResult cancel(@PathVariable int aid,@PathVariable String adminid){
        AdminEntity adminEntity = adminRespository.getOne(adminid);
        if(adminEntity.getType().equals("class")){
            if(activityRespository.toCancelAddress(aid) != 0){
                return new StateResult(200);
            }
            return new StateResult(400);
        }
        if(activityRespository.toCancel(aid)!=0){
            return new StateResult(200);
        }
        return new StateResult(400);
    }



}
