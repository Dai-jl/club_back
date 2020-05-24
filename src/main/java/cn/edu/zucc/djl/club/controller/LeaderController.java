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

    @GetMapping("/api/leader/student/{uid}")
    @ResponseBody
    public Student findOne(@PathVariable String uid){
        StudentEntity studentEntity=studentRepository.getOne(uid);
        Student student=new Student();

        BeanUtils.copyProperties(studentEntity,student);
        return student;
    }

    //社长登陆，djl
    @PostMapping("/api/leader/login")
    @CrossOrigin
    public StateResult login(@RequestBody LoginForm loginForm){
        String id = loginForm.getId();
        MemberTableEntity memberTableEntity = memberRepository.findByUId(id);
        if(memberTableEntity.getType().equals("member")) return new StateResult(300);
        StudentEntity studentEntity = studentRepository.getOne(id);
        if(loginForm.getPassword().equals(studentEntity.getPassword())){
            StudentEntity.setCurrentStudent(studentEntity);
            return new StateResult(200);
        }
        return new StateResult(400);

    }


}
