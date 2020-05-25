package cn.edu.zucc.djl.club.controller;

import ch.qos.logback.core.db.dialect.DBUtil;
import cn.edu.zucc.djl.club.entity.PassgaeEntity;
import cn.edu.zucc.djl.club.form.PassageForm;
import cn.edu.zucc.djl.club.formbean.PsgResult;
import cn.edu.zucc.djl.club.formbean.StuResult;
import cn.edu.zucc.djl.club.repositpories.MemberRepository;
import cn.edu.zucc.djl.club.repositpories.PassgaeRespository;
import cn.edu.zucc.djl.club.repositpories.StudentRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.edu.zucc.djl.club.entity.StudentEntity.currentStudent;

@CrossOrigin
@RestController
public class PassgaeController {
    final PassgaeRespository passgaeRespository;

    PassgaeController( PassgaeRespository passgaeRespository)
    {
        this.passgaeRespository = passgaeRespository;
    }

    //获得列表
    //方式一：（初始化方法）
    //获得活动推送的全部列表,sx
    @GetMapping("/api/passage/show1/{cid}")
    @ResponseBody
    public List<PsgResult> getAllPsList(@PathVariable int cid) throws Exception {
        List<Object[]> listObject=passgaeRespository.getAllPas(cid);
        List<PsgResult>  psgResults= StuResult.objectToBean(listObject,PsgResult.class);

        return psgResults;
    }

    //方式二：
    //按输入搜索内容(keyStr)的范围进行推送的查找
    //模糊查询
    //未完成
    @GetMapping("/api/passage/show2/{cid}/{keyStr}")
    @ResponseBody
    public List<PsgResult> getLessPsList(@PathVariable int cid,@PathVariable String keyStr) throws Exception {
        keyStr=URLDecoder.decode(keyStr,"utf-8");

        System.out.println(keyStr);
        keyStr="%"+keyStr+"%";
        List<Object[]> listObject=passgaeRespository.getLessPas(cid,keyStr);
        List<PsgResult>  psgResults= StuResult.objectToBean(listObject,PsgResult.class);

        return psgResults;
    }


    //发布活动推送,sx
    //暂时规定当前社团id为1
    @PostMapping("/api/passage/putup")
    public int putPsg(@RequestBody PassageForm psg){
        int succeed;
        succeed=passgaeRespository.addPsg(1,psg.getName(),psg.getContent(),psg.getImg(),new Date(),psg.getUrl());

        return succeed;
    }




}
