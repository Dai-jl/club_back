package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.AdminEntity;
import cn.edu.zucc.djl.club.form.LoginForm;
import cn.edu.zucc.djl.club.formbean.ActivityResult;
import cn.edu.zucc.djl.club.formbean.AdminActivity;
import cn.edu.zucc.djl.club.formbean.StateResult;
import cn.edu.zucc.djl.club.repositpories.ActivityRespository;
import cn.edu.zucc.djl.club.repositpories.AdminRespository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;


@CrossOrigin
@RestController
@Api(tags = "管理员审批相关接口")
@RequestMapping("/api/admin")
public class AdminController {
    final AdminRespository adminRespository;
    final ActivityRespository activityRespository;

    AdminController(AdminRespository adminRespository,ActivityRespository activityRespository){
        this.activityRespository = activityRespository;
        this.adminRespository = adminRespository;
    }


    @PostMapping("/login")
    @ApiOperation("管理员登陆,djl")
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


    @PostMapping("/waittopassa/{aid}")
    @ApiOperation("获得待审核活动列表,djl")
    @ApiImplicitParam(name = "aid",value = "活动id",dataType = "String")
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


    @PostMapping("/passa/{aid}")
    @ApiOperation("获得已审核活动列表,djl")
    @ApiImplicitParam(name = "aid",value = "活动id",dataType = "String")
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


    @PostMapping("/passactivity")
    @ApiOperation("通过活动,djl")
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


    @PostMapping("/api/admin/cancelactivity")
    @ApiOperation("取消活动,djl")
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
