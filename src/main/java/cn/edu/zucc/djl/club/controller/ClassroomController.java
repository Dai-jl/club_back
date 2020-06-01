package cn.edu.zucc.djl.club.controller;


import ch.qos.logback.core.db.dialect.DBUtil;
import cn.edu.zucc.djl.club.entity.ClassroomEntity;
import cn.edu.zucc.djl.club.form.RoomForm;
import cn.edu.zucc.djl.club.formbean.RoomResult;
import cn.edu.zucc.djl.club.formbean.TtResult;
import cn.edu.zucc.djl.club.repositpories.ClassroomRepository;
import cn.edu.zucc.djl.club.repositpories.TimetableRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ClassroomController {
    final ClassroomRepository classroomRepository;
    final TimetableRepository timetableRepository;

    public ClassroomController(ClassroomRepository classroomRepository,TimetableRepository timetableRepository){
        this.classroomRepository=classroomRepository;
        this.timetableRepository=timetableRepository;
    }

    //获得所有活动地点列表
    @GetMapping("/api/classroom/list")
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

    //获得场地审批成功的活动地点列表
    @GetMapping("/api/classroom/isPassList")
    @ResponseBody
    public List<TtResult> getisPass() throws Exception {
        List<Object[]> obj=timetableRepository.getPass();

        List<TtResult> res=new ArrayList<TtResult>();
        res=TtResult.objectToBean(obj,TtResult.class);

        return res;
    }

    //提交地点审核
    @PostMapping("/api/classroom/check")
    public int checkForRoom(@RequestBody RoomForm room){
        int succeed;

        succeed=timetableRepository.checkForRoom(room.getAid(),room.getRid(),room.getStart(),room.getEnd());
        return succeed;
    }

    //获得某社团场地申请未通过的列表
    @GetMapping("/api/classroom/notPass/{aid}")
    @ResponseBody
    public List<TtResult> getNotPass(@PathVariable int aid) throws Exception {
        List<Object[]> res=timetableRepository.getnotPass(aid);

        List<TtResult> ttResults=new ArrayList<TtResult>();
        ttResults=TtResult.objectToBean(res,TtResult.class);

        return ttResults;
    }

    //获得当前正在审核的活动场地
    @GetMapping("/api/classroom/waitPass/{aid}")
    @ResponseBody
    public List<TtResult> getRoomName(@PathVariable int aid) throws Exception {
        List<Object[]> res=timetableRepository.getWait(aid);

        List<TtResult> ttResult=new ArrayList<TtResult>();
        ttResult=TtResult.objectToBean(res,TtResult.class);

        return ttResult;
    }

}
