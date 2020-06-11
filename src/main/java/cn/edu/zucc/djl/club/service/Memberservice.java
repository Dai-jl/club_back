package cn.edu.zucc.djl.club.service;

import cn.edu.zucc.djl.club.formbean.StuResult;

import java.util.List;

public interface Memberservice {
    //根据社团id查询社团成员
    List<StuResult> findMemberbyCid(int cid, int state) throws Exception;
    List<StuResult> getMemberList(int cid, int state)throws Exception;
    void addMember(int cid);
    void deleteMember(int cid, String sid);
}
