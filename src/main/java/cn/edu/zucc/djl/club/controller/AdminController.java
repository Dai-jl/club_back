package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.AdminEntity;
import cn.edu.zucc.djl.club.form.LoginForm;
import cn.edu.zucc.djl.club.formbean.ActivityResult;
import cn.edu.zucc.djl.club.formbean.AdminActivity;
import cn.edu.zucc.djl.club.formbean.StateResult;
import cn.edu.zucc.djl.club.repositpories.ActivityRespository;
import cn.edu.zucc.djl.club.repositpories.AdminRespository;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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
        if(adminEntity == null){
            return new StateResult(300);
        }
        if(loginForm.getPassword().equals(adminEntity.getPassword())){
            AdminEntity.setCurrentAdmin(adminEntity);
            String type = adminEntity.getType();
            return new StateResult(200,type);
        }
        return new StateResult(400);
    }

    //待审核活动 djl
    @PostMapping("/api/admin/waittopassa/{aid}")
    @CrossOrigin
    public List<AdminActivity> waitToPass(@PathVariable String aid,@RequestParam("type") String type){
        List<Object[]> res;
        if(type.equals("class")){
            res = activityRespository.findwaitToPass(aid);
        }
        else {
            res = activityRespository.findwaitToaPass(aid);
        }
        try{
            List<AdminActivity> list = ActivityResult.objectToBean(res, AdminActivity.class);
            return list;
        }catch (Exception e){
            return null;
        }

    }


    //已审核活动 djl
    @PostMapping("/api/admin/passa/{aid}")
    @CrossOrigin
    public List<AdminActivity> alredyPass(@PathVariable String aid,@RequestParam("type") String type){
        List<Object[]> res;
        if(type.equals("class")){
            res = activityRespository.findAlreadyPass(aid);
        }
        else{
//            res = activityRespository.findAlreadyPass(aid);
            res = activityRespository.findAlreadyaPass(aid);
        }
        try{
            List<AdminActivity> list = ActivityResult.objectToBean(res,AdminActivity.class);
            return list;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    //通过活动 djl
    @PostMapping("/api/admin/passactivity")
    @CrossOrigin
    public StateResult pass(@RequestParam("aid") int aid,@RequestParam("tid") int tid,@RequestParam("type") String type){
        if(type.equals("class")){
            if(activityRespository.toPassAddress(tid,aid) != 0){
                return new StateResult(200);
            }
        }
        else {
            if(activityRespository.toPass(aid) != 0){
                return new StateResult(200);
            }
        }
        return new StateResult(400);
    }

    //取消活动 djl
    @PostMapping("/api/admin/cancelactivity")
    @CrossOrigin
    public StateResult cancel(@RequestParam("aid") int aid,@RequestParam("tid") int tid,@RequestParam("type") String type,@RequestParam("reason") String reason){
        if(type.equals("class")){
            if(activityRespository.toCancelAddress(tid,reason) != 0){
                return new StateResult(200);
            }
        }
        if(activityRespository.toCancel(aid)!=0){
            return new StateResult(200);
        }
        return new StateResult(400);
    }



}
