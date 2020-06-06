package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.entity.AdminEntity;
import cn.edu.zucc.djl.club.form.ActivityForm;
import cn.edu.zucc.djl.club.formbean.ActivityResult;
import cn.edu.zucc.djl.club.repositpories.ActivityRespository;
import cn.edu.zucc.djl.club.repositpories.CollegeRepository;
import cn.edu.zucc.djl.club.repositpories.MemberRepository;
import cn.edu.zucc.djl.club.repositpories.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "活动操作相关接口")
@RequestMapping("/api/activity")
public class ActivityController {
    final ActivityRespository activityRespository;

    ActivityController(ActivityRespository activityRespository)
    {
        this.activityRespository = activityRespository;

    }


    @GetMapping("/notpass/{cid}")
    @ApiOperation("获得审核未通过的活动列表（包括活动未通过或地点未通过的）,sx")
    @ApiImplicitParam(name = "cid",value = "社团id",dataType = "int")
    @ResponseBody
    public List<ActivityResult> waitToabPass(@PathVariable int cid){
        List<Object[]> activitirs =  activityRespository.findwaitToabPass(cid);
        try{
            List<ActivityResult> list = ActivityResult.objectToBean(activitirs,ActivityResult.class);
            return list;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


    @GetMapping("/ispass/{cid}")
    @ApiOperation("获得审核全部已通过的列表,sx")
    @ApiImplicitParam(name = "cid",value = "社团id",dataType = "int")
    @ResponseBody
    public List<ActivityResult> isabPass(@PathVariable int cid){
        List<Object[]> activitirs =  activityRespository.findisabPass(cid);
        try{
            List<ActivityResult> list = ActivityResult.objectToBean(activitirs,ActivityResult.class);
            return list;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


    @PostMapping("/add")
    @ApiOperation("活动申请,sx")
    public int addActivity(@RequestBody ActivityForm aF){
        int succeed;

        succeed=activityRespository.addActivity(aF.getName(),aF.getCid(),aF.getPlace(),new Date(),aF.getStart(),aF.getEnd(),aF.getNumber(),aF.getBudget(),aF.getDetail(),aF.getLimit());
        System.out.println(succeed);
        return succeed;
    }

}
