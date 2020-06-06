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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.Request;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.edu.zucc.djl.club.entity.StudentEntity.currentStudent;

@CrossOrigin
@RestController
@Api(tags = "推送发布相关接口")
@RequestMapping("/api/passage")
public class PassgaeController {
    final PassgaeRespository passgaeRespository;

    PassgaeController( PassgaeRespository passgaeRespository)
    {
        this.passgaeRespository = passgaeRespository;
    }


    @GetMapping("/show1/{cid}")
    @ApiOperation("获得活动推送的全部列表,sx")
    @ApiImplicitParam(name = "cid",value = "社团id",dataType = "int")
    @ResponseBody
    public List<PsgResult> getAllPsList(@PathVariable int cid) throws Exception {
        List<PsgResult>  psgResults=new ArrayList<PsgResult>();

        List<Object[]> listObject=passgaeRespository.getAllPas(cid);
        psgResults= PsgResult.objectToBean(listObject,PsgResult.class);

        return psgResults;
    }


    @GetMapping("/show2/{cid}/{keyStr}")
    @ApiOperation("按输入搜索内容(keyStr)的范围进行推送的查找，模糊查询,sx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid",value = "社团id",dataType = "int"),
            @ApiImplicitParam(name = "keyStr",value = "搜索关键字",dataType = "String")
    })
    @ResponseBody
    public List<PsgResult> getLessPsList(@PathVariable int cid, @PathVariable String keyStr) throws Exception {
        List<PsgResult>  psgResults=new ArrayList<PsgResult>();
//        String keyword=request.getParameter(keyStr);
//        keyword=URLDecoder.decode(keyword,"utf-8");
//        System.out.println(keyStr);

        keyStr="%"+keyStr+"%";
        List<Object[]> listObject=passgaeRespository.getLessPas(cid,keyStr);
        System.out.println(listObject.size());
        psgResults= PsgResult.objectToBean(listObject,PsgResult.class);

        return psgResults;
    }


    @PostMapping("/putup")
    @ApiOperation("发布活动推送,sx")
    public int putPsg(@RequestBody PassageForm psg){
        int succeed;
        succeed=passgaeRespository.addPsg(psg.getCid(),psg.getName(),psg.getContent(),psg.getImg(),new Date(),psg.getUrl());

        return succeed;
    }


    @GetMapping("/delete/{pid}")
    @ApiOperation("删除活动推送，sx")
    @ApiImplicitParam(name = "pid",value = "推送id",dataType = "int")
    @ResponseBody
    public int deletePsg(@PathVariable int pid){
        int succeed;
        succeed=passgaeRespository.deletePsg(pid);

        return succeed;
    }

    //修改活动推送，sx
//    @PostMapping("/modify")
//    public int modifyPsg(@RequestBody PassageForm psg){
//        int succeed;
//        succeed=passgaeRespository.modifyPsg(psg.getName(),psg.getContent(),psg.getPid());
//
//        return succeed;
//    }


}
