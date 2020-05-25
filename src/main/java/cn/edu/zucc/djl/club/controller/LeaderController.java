package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.CollegeEntity;
import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import cn.edu.zucc.djl.club.entity.StudentEntity;
import cn.edu.zucc.djl.club.form.LoginForm;
import cn.edu.zucc.djl.club.formbean.College;
import cn.edu.zucc.djl.club.formbean.StateResult;
import cn.edu.zucc.djl.club.formbean.StuResult;
import cn.edu.zucc.djl.club.formbean.Student;
import cn.edu.zucc.djl.club.repositpories.CollegeRepository;
import cn.edu.zucc.djl.club.repositpories.MemberRepository;
import cn.edu.zucc.djl.club.repositpories.StudentRepository;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

@CrossOrigin
@RestController
public class LeaderController {
    final MemberRepository memberRepository;
    final StudentRepository studentRepository;
    final CollegeRepository collegeRepository;
    LeaderController(MemberRepository memberRepository,StudentRepository studentRepository,CollegeRepository collegeRepository)
    {
        this.memberRepository = memberRepository;
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
    }

    //获得成员列表，sx
    @GetMapping("/api/leader/member/{id}/{pageIndex}")
    @ResponseBody
    public List<StuResult> listAllMember(@PathVariable int id, @PathVariable int pageIndex) throws Exception {
        //优化使用多表查询，使性能提高
        //使用limit实现分页
        int startIndex=10*(pageIndex-1);
        List<Object[]> memberMsg = memberRepository.getMemList(id,startIndex);

        List<StuResult> results=StuResult.objectToBean(memberMsg,StuResult.class);
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


    //转让社长，sx
    //传参：社团cid + 被转让成员uid
    //返回1说明社长转让成功，则该学生失去社长权限（页面可以跳转到“亲爱的XX同学，感谢这段时间的陪伴，再见！）”
    @GetMapping("/api/leader/change/{cid}/{uidL}/{uidM}")
    @ResponseBody
    public int transferLeader(@PathVariable int cid,@PathVariable String uidL,@PathVariable String  uidM){
        int succeed=-1;
        succeed=memberRepository.transferMsgLeader(uidL,cid);
        if(succeed>0){
            succeed=memberRepository.transferMsgMem(uidM,cid);
            if(succeed>0)
                return 1;
            else
                return 0;
        }else
            return 0;
    }



    @GetMapping("/api/leader/student/{uid}")
    //添加成员 czq
    @PostMapping("/api/leader/addmember")
    public String addMember(@RequestBody Map<String, String> res){
        StudentEntity studentEntity=new StudentEntity();
        String uid=res.get("number");
        studentEntity=studentRepository.getOne(uid);
        System.out.print(uid);
        if (studentEntity!=null&&uid!=""){
            int cid = Integer.parseInt(res.get("cid"));
            if (!memberRepository.existsByCIdAndUId(cid,uid)){
                MemberTableEntity memberTableEntity=new MemberTableEntity();
                memberTableEntity.setcId(cid);
                memberTableEntity.setType("member");
                memberTableEntity.setuId(res.get("number"));
                memberTableEntity.setState(1);
                Date now = new Date();
                memberTableEntity.setJoinDate(now);
                try{
                    memberRepository.save(memberTableEntity);
                }
                catch (Exception e){
                    return "添加失败";
                }
            }

            else{
                return "该学生已经加入社团";
            }
        }
        else{
            return "该学生不存在";
        }
        return "添加成功";
    }
    //删除成员 czq
    @PostMapping("/api/leader/deletemember")
    @ResponseBody
    public String deleteMember(@RequestBody Map<String, String> res){
        try {
            int cid=Integer.parseInt(res.get("cid"));
            memberRepository.updatestate(cid,res.get("uid"));
        }
        catch (Exception e){
            return "删除失败";
        }
        return "删除成功";
    }



    //社长登陆，djl
    @PostMapping("/api/leader/login")
    @CrossOrigin
    public StateResult login(@RequestBody LoginForm loginForm){
        String id = loginForm.getId();
        MemberTableEntity memberTableEntity = memberRepository.findByUId(id);
        if(memberTableEntity == null) return new StateResult(300);
        StudentEntity studentEntity = studentRepository.getOne(id);
        if(loginForm.getPassword().equals(studentEntity.getPassword())){
            StudentEntity.setCurrentStudent(studentEntity);
            return new StateResult(200);
        }
        return new StateResult(400);

    }


}
