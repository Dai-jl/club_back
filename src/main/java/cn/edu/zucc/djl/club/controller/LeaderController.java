package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.config.PassToken;
import cn.edu.zucc.djl.club.config.PasswordMD5;
import cn.edu.zucc.djl.club.config.UserLoginToken;
import cn.edu.zucc.djl.club.entity.CollegeEntity;
import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import cn.edu.zucc.djl.club.entity.StudentEntity;
import cn.edu.zucc.djl.club.form.LoginForm;
import cn.edu.zucc.djl.club.form.MemeditForm;
import cn.edu.zucc.djl.club.formbean.College;
import cn.edu.zucc.djl.club.formbean.StateResult;
import cn.edu.zucc.djl.club.formbean.StuResult;
import cn.edu.zucc.djl.club.formbean.Student;
import cn.edu.zucc.djl.club.repositpories.*;
import cn.edu.zucc.djl.club.service.Memberservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.BeanUtils;

@CrossOrigin
@RestController
@Api(tags = "社长职能相关接口")
@RequestMapping("/api/leader")
public class LeaderController {
    final MemberRepository memberRepository;
    final StudentRepository studentRepository;
    final CollegeRepository collegeRepository;
    final ActivityRespository activityRespository;
    final TimetableRepository timetableRepository;
    private Memberservice memberservice;
    LeaderController(MemberRepository memberRepository,StudentRepository studentRepository,CollegeRepository collegeRepository,ActivityRespository activityRespository,TimetableRepository timetableRepository,Memberservice memberservice)
    {
        this.memberRepository = memberRepository;
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
        this.activityRespository = activityRespository;
        this.timetableRepository = timetableRepository;
        this.memberservice=memberservice;
    }


//    @UserLoginToken(type ="leader")
    @GetMapping("/member/{id}/{state}")
    @ApiOperation("获得社团成员列表，sx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "社团id",dataType = "int"),
            @ApiImplicitParam(name = "state",value = "指定社团成员状态，在社1/离社0",dataType = "int")
    })
    @ResponseBody
    public List<StuResult> listAllMember(@PathVariable int id,@PathVariable int state) throws Exception {
        //优化使用多表查询，使性能提高
        //分页在前端进行模拟
    //    List<Object[]> memberMsg = memberRepository.getMemList(id, state);
        System.out.println(id+"id");
        System.out.println(state+"state");
        long start = System.currentTimeMillis();
        List<Object[]> re=memberRepository.getMemList(id, state);
        System.out.println(re.get(0));
        List<StuResult> results = memberservice.getMemberList(id, state);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return results;

        //简单循环
//         List<StuResult> stuResults = new ArrayList<>();
//         for(MemberTableEntity memberTableEntity:memberTableEntities){
//             StuResult result=new StuResult();
//             result.setJoinDate(memberTableEntity.getJoinDate());result.setLeaveDate(memberTableEntity.getLeaveDate());result.setState(memberTableEntity.getState());
//
//             String uId = memberTableEntity.getuId();
//             result.setId(uId);
//             StudentEntity studentEntity = studentRepository.getOne(uId);
//             if(studentEntity.getuId().equals(StudentEntity.currentStudent.getuId()))
//                 continue;
//             else{
//                 result.setName(studentEntity.getName());
//                 result.setPhone(studentEntity.getPhone());
//
//                 int cId=studentEntity.getCollegeId();
//                 CollegeEntity collegeEntity=collegeRepository.getOne(cId);
//                 result.setCollege(collegeEntity.getName());
//             }
//
//             stuResults.add(result);
//         }
//
//        return stuResults;

    }

    @UserLoginToken(type ="leader")
    @GetMapping("/cancelActivity/{aid}")
    @ApiOperation("取消活动的申请,sx")
    @ApiImplicitParam(name = "aid",value = "社团id",dataType = "int")
    @ResponseBody
    public int cancel(@PathVariable int aid){
        int succeed;

        succeed=activityRespository.cancelActivity1(aid);

        List<Object[]> wait=timetableRepository.getWait(aid);
        if(wait.size()>0 && succeed==1){
            succeed=timetableRepository.cancelActivity2(aid);
        }

        return succeed;
    }

    @UserLoginToken(type ="leader")
    @PostMapping("/editMember")
    @ApiOperation("编辑社团成员信息，sx")
    public int editMember(@RequestBody MemeditForm form){
        int succeed;

        succeed=studentRepository.editMember(form.getId(),form.getPhone());
        return succeed;
    }

    @UserLoginToken(type ="leader")
    @PostMapping("/addmember")
    @ApiOperation("添加社团成员,czq")
    public String addMember(@RequestBody Map<String, String> res) {
        new StudentEntity();
        String uid = (String)res.get("number");
                int cid = Integer.parseInt((String)res.get("cid"));
                String[] numbers = uid.split("、");
                System.out.print(numbers.length);

                for(int u = 0; u < numbers.length; ++u) {
                    if (numbers[u] != "" && numbers[u] != " " && numbers[u] != null) {
                        Optional<StudentEntity> studentEntity = studentRepository.findById(numbers[u]);
                        if (studentEntity == null || numbers[u] == "") {
                            return "学生" + numbers[u] + "不存在";
                        }

                        System.out.println(numbers[u]);
                        if (this.memberRepository.existsMember(cid, numbers[u]) != null) {
                            return "学生" + numbers[u] + "已经加入社团";
                        }
                if (u == 0) {
                    this.memberservice.addMember(cid);
                }
                try {
                    this.memberRepository.insertMember(numbers[u], cid);
                }
             catch (Exception e) {
                return "添加学生" + numbers[u] + "失败";
            }
        }
    }

        return "添加成功";
}

    @UserLoginToken(type ="leader")
    @PostMapping("/deletemember")
    @ApiOperation("删除社团成员,czq")
    @ResponseBody
    public String deleteMember(@RequestBody Map<String, String> res){
        try {
            int cid=Integer.parseInt(res.get("cid"));
            memberservice.deleteMember(cid, res.get("uid"));
        }
        catch (Exception e){
            return "删除失败";
        }
        return "删除成功";
    }

    @UserLoginToken(type ="leader")
    @PostMapping("/searchmember")
    @ApiOperation("通过关键字搜索成员列表，czq")
    @ResponseBody
    public List<StuResult> searchMember(@RequestBody Map<String,String> res) throws Exception {
        int cid=Integer.parseInt(res.get("cid"));
        String username="%"+res.get("username")+"%";
        String collegename="%"+res.get("collegename")+"%";
        if (res.get("collegename").equals(""))
            collegename="%%";
        String joindate=res.get("joindate");
        System.out.print(joindate + "fdsfsdf");
        if (joindate==null||joindate.equals("")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            joindate = format.format(new Date());
        }

        System.out.print(joindate);
        List<Object[]> memberMsg = memberRepository.searchMember(cid,username,collegename,joindate);
        List<StuResult> results=StuResult.objectToBean(memberMsg,StuResult.class);
        return results;
    }

    @PassToken
    @PostMapping("/login")
    @ApiOperation("社长登陆，djl")
    public Object login(@RequestBody LoginForm loginForm){
        String id = loginForm.getId();
        MemberTableEntity memberTableEntity = memberRepository.findByUId(id);
        if(memberTableEntity == null || memberTableEntity.getType().equals("member"))
            return new StateResult(300);
        int cid = memberTableEntity.getcId();
        StudentEntity studentEntity = studentRepository.getOne(id);
        String pwd = PasswordMD5.inputPassToFormPass(loginForm.getPassword());
        if(pwd.equals(studentEntity.getPassword())){
            StudentEntity.setCurrentStudent(studentEntity);
            String token = studentEntity.getToken(studentEntity);
            return new StateResult(200,cid,token);
        }

        return new StateResult(400);

    }


}
