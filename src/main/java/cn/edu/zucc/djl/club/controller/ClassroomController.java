package cn.edu.zucc.djl.club.controller;

import cn.edu.zucc.djl.club.config.UserLoginToken;
import cn.edu.zucc.djl.club.entity.ClassroomEntity;
import cn.edu.zucc.djl.club.form.RoomForm;
import cn.edu.zucc.djl.club.formbean.RoomResult;
import cn.edu.zucc.djl.club.formbean.TtResult;
import cn.edu.zucc.djl.club.repositpories.ClassroomRepository;
import cn.edu.zucc.djl.club.repositpories.TimetableRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "活动场地相关接口")
@RequestMapping("/api/classroom")
public class ClassroomController {
    final ClassroomRepository classroomRepository;
    final TimetableRepository timetableRepository;

    public ClassroomController(ClassroomRepository classroomRepository,TimetableRepository timetableRepository){
        this.classroomRepository=classroomRepository;
        this.timetableRepository=timetableRepository;
    }

    @UserLoginToken(type ="leader")
    @GetMapping("/list")
    @ApiOperation("获得所有活动场地列表，sx")
    @ResponseBody
    public List<RoomResult> getClaList(){
        List<ClassroomEntity> cE=classroomRepository.findAll();

        List<RoomResult> roomResults=new ArrayList<RoomResult>();
        for (ClassroomEntity ce:cE){
            RoomResult res=new RoomResult();
            res.setId(ce.getrId());
            res.setName(ce.getName());

            roomResults.add(res);
        }
        return roomResults;
    }

    @UserLoginToken(type ="leader")
    @GetMapping("/isPassList")
    @ApiOperation("获得审批成功的场地及使用时间的列表，sx")
    @ResponseBody
    public List<TtResult> getisPass() throws Exception {
        List<Object[]> obj=timetableRepository.getPass();

        List<TtResult> res=new ArrayList<TtResult>();
        res=TtResult.objectToBean(obj,TtResult.class);

        return res;
    }

    @UserLoginToken(type ="leader")
    @PostMapping("/check")
    @ApiOperation("选择并提交地点审核，sx")
    public int checkForRoom(@RequestBody RoomForm room){
        int succeed;

        succeed=timetableRepository.checkForRoom(room.getAid(),room.getRid(),room.getStart(),room.getEnd());
        return succeed;
    }

    @UserLoginToken(type ="leader")
    @GetMapping("/notPass/{aid}")
    @ApiOperation("获得指定活动场地申请未通过的列表，方便在场地申请处有记录的展示，sx")
    @ApiImplicitParam(name = "aid",value = "活动id",dataType = "int")
    @ResponseBody
    public List<TtResult> getNotPass(@PathVariable int aid) throws Exception {
        List<Object[]> res=timetableRepository.getnotPass(aid);

        List<TtResult> ttResults=new ArrayList<TtResult>();
        ttResults=TtResult.objectToBean(res,TtResult.class);

        return ttResults;
    }

    //获得当前正在审核的活动场地
    @UserLoginToken(type ="leader")
    @GetMapping("/waitPass/{aid}")
    @ApiOperation("获得指定活动的正在进行场地审核的列表，sx")
    @ApiImplicitParam(name = "aid",value = "活动id",dataType = "int")
    @ResponseBody
    public List<TtResult> getRoomName(@PathVariable int aid) throws Exception {
        List<Object[]> res=timetableRepository.getWait(aid);

        List<TtResult> ttResult=new ArrayList<TtResult>();
        ttResult=TtResult.objectToBean(res,TtResult.class);

        return ttResult;
    }

}
