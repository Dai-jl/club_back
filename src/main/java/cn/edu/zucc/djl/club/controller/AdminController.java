package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.config.PassToken;
import cn.edu.zucc.djl.club.config.PasswordMD5;
import cn.edu.zucc.djl.club.config.UserLoginToken;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


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

    @PassToken
    @PostMapping("/login")
    @ApiOperation("管理员登陆,djl")
    public StateResult login(@RequestBody LoginForm loginForm){
        String id = loginForm.getId();
        AdminEntity adminEntity = adminRespository.getOne(id);
        if(adminEntity == null){
            return new StateResult(300);
        }
        String pwd = PasswordMD5.inputPassToFormPass(loginForm.getPassword());
        if(pwd.equals(adminEntity.getPassword())){
            AdminEntity.setCurrentAdmin(adminEntity);
            String type = adminEntity.getType();
            String token = adminEntity.getToken(adminEntity);
            return new StateResult(200,type,token);
        }
        return new StateResult(400);
    }

    //测试token
//    @UserLoginToken
//    @GetMapping("/test")
//    public String test(){
//        return "ok";
//    }

    @UserLoginToken(type ="admin")
    @PostMapping("/waittopass")
    @ApiOperation("获得待审核活动列表,djl")
    @ApiImplicitParam(name = "aid",value = "活动id",dataType = "String")
    public List<AdminActivity> waitToPass(@RequestParam("id") String aid,@RequestParam("type") String type){
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

    @UserLoginToken(type ="admin")
    @PostMapping("/pass")
    @ApiOperation("获得已审核活动列表,djl")
    @ApiImplicitParam(name = "aid",value = "活动id",dataType = "String")
    public List<AdminActivity> alredyPass(@RequestParam("id") String aid,@RequestParam("type") String type){
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

    @UserLoginToken(type ="admin")
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

    @UserLoginToken(type ="admin")
    @PostMapping("/cancelactivity")
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

//    @UserLoginToken(type ="admin")
    @ApiOperation("搜索未审核活动,czq")
    @PostMapping("/searchwaittopassa")
    @CrossOrigin
    public List<AdminActivity> searchWaitToPass(@RequestBody Map<String,String> res){
        List<Object[]> result = null;
        String a="%"+res.get("activityname")+"%";
        String c="%"+res.get("clubname")+"%";
        String s=res.get("startdate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (res.get("startdate").equals("")){
            Calendar calendar = Calendar.getInstance();
            // System.out.println(format.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 100);
            //    System.out.println(format.format(calendar.getTime()));
            s=format.format(calendar.getTime());
        }

        System.out.println(a);
        System.out.println(c);
        System.out.println(s);
        if(res.get("type").equals("activity"))
            result = activityRespository.searchActivitiesWait(res.get("aid"),c,a,s);

        else {
            result = activityRespository.searchClassroomsWait(res.get("aid"),c,a,s);
        }
        try{
            List<AdminActivity> list = ActivityResult.objectToBean(result, AdminActivity.class);
            return list;
        }catch (Exception e){
            return null;
        }

    }

//    @UserLoginToken(type ="admin")
    @ApiOperation("搜索已审核活动,czq")
    @PostMapping("/searchpassa")
    @CrossOrigin
    public List<AdminActivity> searchPass(@RequestBody Map<String,String> res){

        List<Object[]> result = null;
        String a="%"+res.get("activityname")+"%";
        String c="%"+res.get("clubname")+"%";
        String s=res.get("startdate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (res.get("startdate").equals("")){

            Calendar calendar = Calendar.getInstance();
            // System.out.println(format.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 100);
            //    System.out.println(format.format(calendar.getTime()));
            s=format.format(calendar.getTime());
        }

        System.out.println(a);
        System.out.println(c);
        System.out.println(s);

        if(res.get("type").equals("activity"))
            result = activityRespository.searchActivities(res.get("aid"),c,a,s);

        else {
            result = activityRespository.searchClassrooms(res.get("aid"),c,a,s);
        }
        try{
            List<AdminActivity> list = ActivityResult.objectToBean(result, AdminActivity.class);
            return list;
        }catch (Exception e){
            return null;
        }

    }

}
