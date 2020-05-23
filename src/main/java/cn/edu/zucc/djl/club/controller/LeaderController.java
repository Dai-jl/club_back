package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.CollegeEntity;
import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import cn.edu.zucc.djl.club.entity.StudentEntity;
import cn.edu.zucc.djl.club.formbean.StuResult;
import cn.edu.zucc.djl.club.formbean.Student;
import cn.edu.zucc.djl.club.repositpories.CollegeRepository;
import cn.edu.zucc.djl.club.repositpories.MemberRepository;
import cn.edu.zucc.djl.club.repositpories.StudentRepository;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class LeaderController {
    final MemberRepository memberRepository;
    final StudentRepository studentRepository;
    final CollegeRepository collegeRepository;
    LeaderController(MemberRepository memberRepository,StudentRepository studentRepository,CollegeRepository collegeRepository)
    {
        this.memberRepository = memberRepository;
        this.studentRepository = studentRepository;
        this.collegeRepository=collegeRepository;
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

}
