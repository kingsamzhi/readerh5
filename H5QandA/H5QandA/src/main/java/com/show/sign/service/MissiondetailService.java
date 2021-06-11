package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.MissiondetailMapper;
import com.show.sign.mapper.StuerrorbookMapper;
import com.show.sign.mapper.StuopenMapper;
import com.show.sign.pojo.Missiondetail;
import com.show.sign.pojo.Stuerrorbook;
import com.show.sign.pojo.Stuopen;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MissiondetailService {
    @Resource
    private RedisService redisService;

    @Resource
    private MissiondetailMapper missiondetailMapper;
    @Resource
    private StuopenMapper stuopenMapper;

    @Resource
    private StuerrorbookMapper stuerrorbookMapper;

    public PageInfo<Stuerrorbook> getErrorBook(String token, Integer stuid, Integer page, Integer limit){
          /*
            1.判断是否token和stuid是合法的
            * */
        if(stuid==null||token==null){
            return null;
        }

        String openid=redisService.getValue(token);
        //获取openid并获取学生信息，验证是否是绑定的openid
        Example stuexample = Example.builder(Stuopen.class).build();
        Example.Criteria stuc=stuexample.createCriteria();
        stuc.andEqualTo("stuid",stuid);//当前这个学生
        stuc.andEqualTo("openid",openid);
        List<Stuopen> stus=stuopenMapper.selectByExample(stuexample);
        if(openid==null||stus==null||stus.size()<=0){
           return null;
        }

        Example example = Example.builder(Stuerrorbook.class).build();
        Example.Criteria c=example.createCriteria();
        c.andEqualTo("stuid",stuid);
        Page<Stuerrorbook> p = PageHelper.startPage(page, limit);
        List<Stuerrorbook> datas = stuerrorbookMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo<>(datas);

        return pageInfo;

    }


    public PageInfo<Missiondetail> getDatas(Integer missionid,Integer page, Integer limit){

            Example example = Example.builder(Missiondetail.class).build();
            Example.Criteria c=example.createCriteria();

            if(missionid!=null&&!missionid.equals(0)){
                c.andEqualTo("missionid",missionid);
            }
            Page<Missiondetail> p = PageHelper.startPage(page, limit);
            List<Missiondetail> datas = missiondetailMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;

    }


    public int editMissiondetail(Missiondetail missiondetail) {
        return missiondetailMapper.updateByPrimaryKey(missiondetail);
    }

    public int addMissiondetail(Missiondetail missiondetail) {
        return missiondetailMapper.insert(missiondetail);
    }

    public Missiondetail getMissiondetailById(Integer id) {
        return missiondetailMapper.selectByPrimaryKey(id);
    }

    public int deleteMissiondetail(Integer id) {
        return missiondetailMapper.deleteByPrimaryKey(id);
    }
}
