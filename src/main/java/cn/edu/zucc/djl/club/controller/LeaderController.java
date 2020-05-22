package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.MemberTableEntity;
import cn.edu.zucc.djl.club.entity.StudentEntity;
import cn.edu.zucc.djl.club.repositpories.MemberRepository;
import cn.edu.zucc.djl.club.repositpories.StudentRepository;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LeaderController {
    final MemberRepository memberRepository;
    final StudentRepository studentRepository;
    LeaderController(MemberRepository memberRepository,StudentRepository studentRepository)
    {
        this.memberRepository = memberRepository;
        this.studentRepository = studentRepository;
    }

    @CrossOrigin
    @GetMapping("/api/leader/member/{id}")
    @ResponseBody
    public List<StudentEntity> listAllMember(@PathVariable int id){
         List<MemberTableEntity> memberTableEntities = memberRepository.findBycId(id);
         List<StudentEntity> studentEntityList = new ArrayList<>();
         for(int i = 0;i<memberTableEntities.size();i++){
             String uId = memberTableEntities.get(i).getuId();
             StudentEntity studentEntity = studentRepository.getOne(uId);
             if(studentEntity.getuId().equals(StudentEntity.currentStudent.getuId()))
                 continue;
             studentEntityList.add(studentEntity);
         }
         return studentEntityList;
    }

}
