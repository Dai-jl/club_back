package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.CollegeEntity;
import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import cn.edu.zucc.djl.club.entity.StudentEntity;
import cn.edu.zucc.djl.club.formbean.College;
import cn.edu.zucc.djl.club.formbean.StuResult;
import cn.edu.zucc.djl.club.formbean.Student;
import cn.edu.zucc.djl.club.repositpories.CollegeRepository;
import cn.edu.zucc.djl.club.repositpories.MemberRepository;
import cn.edu.zucc.djl.club.repositpories.StudentRepository;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

@CrossOrigin
@RestController
public class LeaderController {
    final MemberRepository memberRepository;
    final StudentRepository studentRepository;
    final CollegeRepository  collegeRepository;

    LeaderController(MemberRepository memberRepository,StudentRepository studentRepository,CollegeRepository  collegeRepository)
    {
        this.memberRepository = memberRepository;
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
    }

    //获得成员列表，sx
    @GetMapping("/api/leader/member/{id}")
    @ResponseBody
    public List<StuResult> listAllMember(@PathVariable int id){
         List<MemberTableEntity> memberTableEntities = memberRepository.findBycIdOrderByStateDesc(id);
         List<StuResult> stuResults = new ArrayList<>();
         for(MemberTableEntity memberTableEntity:memberTableEntities){
             StuResult result=new StuResult();
             result.setJoinDate(memberTableEntity.getJoinDate());result.setLeaveDate(memberTableEntity.getLeaveDate());result.setState(memberTableEntity.getState());

             String uId = memberTableEntity.getuId();
             result.setId(uId);
             StudentEntity studentEntity = studentRepository.getOne(uId);
             if(studentEntity.getuId().equals(StudentEntity.currentStudent.getuId()))
                 continue;
             else{
                 result.setName(studentEntity.getName());
                 result.setPhone(studentEntity.getPhone());

                 int cId=studentEntity.getCollegeId();
                 CollegeEntity collegeEntity=collegeRepository.getOne(cId);
                 result.setCollege(collegeEntity.getName());
             }

             stuResults.add(result);
         }
         return stuResults;
    }

    //转让社长，sx
    //传参：社团cid + 被转让成员uid
    //返回1说明社长转让成功，则该学生失去社长权限（页面可以跳转到“亲爱的XX同学，感谢这段时间的陪伴，再见！）”
    @GetMapping("/api/leader/member/{cid}/{uidL}/{uidM}")
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


}
