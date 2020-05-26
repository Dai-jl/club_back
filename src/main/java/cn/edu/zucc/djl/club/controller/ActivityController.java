package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.repositpories.CollegeRepository;
import cn.edu.zucc.djl.club.repositpories.MemberRepository;
import cn.edu.zucc.djl.club.repositpories.StudentRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ActivityController {
    final MemberRepository memberRepository;
    final StudentRepository studentRepository;
    final CollegeRepository collegeRepository;

    ActivityController(MemberRepository memberRepository,StudentRepository studentRepository,CollegeRepository  collegeRepository)
    {
        this.memberRepository = memberRepository;
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
    }

    //获得活动列表,sx
    //@GetMapping('/api/activity/{cid}')



}
