package cn.edu.zucc.djl.club.service.impl;

import cn.edu.zucc.djl.club.formbean.StuResult;
import cn.edu.zucc.djl.club.repositpories.MemberRepository;
import cn.edu.zucc.djl.club.service.Memberservice;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MemberserviceImpl implements Memberservice {
    @Autowired
    MemberRepository memberRepository;

//    @Value("${guava.cache.maximumSize}")
//    private Long cacheSize;
//    @Value("${guava.cache.expire}")
//    private Long expireDays;
    //
    private LoadingCache<Integer, List<StuResult>> memberinCache = CacheBuilder.newBuilder()
            .recordStats()
            .maximumSize(1000)
            .expireAfterAccess(10, TimeUnit.DAYS)
            .build(
                    new CacheLoader<Integer, List<StuResult>>() {
                        @Override
                        public List<StuResult> load(Integer cid) throws Exception {
                            List<StuResult> lists=findMemberbyCid(cid,1);
                            if (lists!=null){
                                return lists;
                            }

                            else
                                return null;
                        }


                    }
            );
//    private LoadingCache<Integer, List<StuResult>> memberoutCache = CacheBuilder.newBuilder()
//            .recordStats()
//            .maximumSize(1000)
//            .expireAfterAccess(10, TimeUnit.DAYS)
//            .build(
//                    new CacheLoader<Integer, List<StuResult>>() {
//                        @Override
//                        public List<StuResult> load(Integer integer) throws Exception {
//                            findMemberbyCid(integer,0);
//                            return null;
//                        }
//
//
//                    }
//            );

    //从数据库读取数据
    @Override
    public List<StuResult> findMemberbyCid(int cid, int state) throws Exception {
        System.out.println(cid+state+"fdsfsdf");
        List<Object[]> res=memberRepository.getMemList(cid,state);
        List<StuResult> results=new ArrayList<>();
        if (res!=null)
        {
            System.out.print("not null 1");
            results= StuResult.objectToBean(res, StuResult.class);
            System.out.print(results);
        }
        else {
           System.out.print("null 1");
        }

        return results;
    }
    //在社成员
    public List<StuResult> getMemberList(int cid, int state) throws Exception {
        List<StuResult> res=new ArrayList<>();
        try {
            List<StuResult> results=memberinCache.get(cid);
            if (state==1&&results!=null){
                res=results;
            }
            else if (state==0){
                res=findMemberbyCid(cid,state);
            }
            else {
                System.out.print("null");
            }

        }catch (Exception e){

        }

        return res;

    }
    public void addMember (int cid) {
        memberinCache.invalidate(cid);

    }

    public void deleteMember(int cid,String sid){
        memberinCache.invalidate(cid);
        try {
            memberRepository.updatestate(cid, sid);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        }

}
