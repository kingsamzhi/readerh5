package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.SilkbagMapper;
import com.show.sign.mapper.StumedalMapper;
import com.show.sign.mapper.StumedaldetailMapper;
import com.show.sign.mapper.StuopenMapper;
import com.show.sign.pojo.*;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SilkbagService {
    @Resource
    private RedisService redisService;

    @Resource
    private SilkbagMapper silkbagMapper;

    @Resource
    private StuopenMapper stuopenMapper;

    @Resource
    private StumedalMapper stumedalMapper;

    @Resource
    private StumedaldetailMapper stumedaldetailMapper;


    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdfdate=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdftime=new SimpleDateFormat("HH:mm:ss");

    public List<Silkbag> getDatas(String stuid,String createdate,String usedate,Integer flag){
            Example example = Example.builder(Silkbag.class).build();
            Example.Criteria c=example.createCriteria();

            if(flag!=null&&!flag.equals(0)){
                c.andEqualTo("flag",flag);
            }
            if(stuid!=null&&!stuid.equals("")){
                c.andEqualTo("stuid",stuid);
            }
            if(createdate!=null&&!createdate.equals("")){
                c.andEqualTo("createdate",createdate);
            }
            if(usedate!=null&&!usedate.equals("")){
                c.andEqualTo("usedate",usedate);
            }

            List<Silkbag> datas = silkbagMapper.selectByExample(example);
            return datas;

    }

    public Silkbag useSilkbag(String token,Integer stuid, Integer id) {

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



        Silkbag silkbag=silkbagMapper.selectByPrimaryKey(id);
        if(silkbag!=null&&silkbag.getFlag()==0) {
            silkbag.setUsedate(sdfdate.format(new Date()));
            silkbag.setUsetime(sdf.format(new Date()));
            silkbag.setFlag(1);
            int i=silkbagMapper.updateByPrimaryKey(silkbag);
            if(i==1) {
                return silkbag;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public Silkbag getSilkbagByStuid(String token,Integer stuid) {

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


        //判断是否有锦囊没有使用
        Example example = Example.builder(Silkbag.class).build();
        Example.Criteria c=example.createCriteria();
        c.andEqualTo("stuid",stuid);
        c.andEqualTo("flag",0);
        List<Silkbag> silkbags=silkbagMapper.selectByExample(example);
        if(silkbags!=null&&silkbags.size()>0){
            return silkbags.get(0);
        }else{
            return null;
        }

    }


    public Silkbag getSilkbag(String token,Integer stuid) {

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


        //判断是否有锦囊没有使用
        Example example = Example.builder(Silkbag.class).build();
        Example.Criteria c=example.createCriteria();
        c.andEqualTo("stuid",stuid);
        c.andEqualTo("flag",0);


        Example.Criteria d=example.createCriteria();
        d.andEqualTo("stuid",stuid);
        d.orEqualTo("createdate",sdfdate.format(new Date()));
        example.or(d);

        List<Silkbag> silkbags=silkbagMapper.selectByExample(example);
        if(silkbags!=null&&silkbags.size()>0){
            return null;//已经有锦囊,或今天已领取过锦囊。
        }else{
            Silkbag silkbag=new Silkbag();
            silkbag.setFlag(0);
            silkbag.setStuid(stuid);
            silkbag.setCreatedate(sdfdate.format(new Date()));
            silkbag.setCreatetime(sdf.format(new Date()));
            silkbagMapper.insert(silkbag);
            return silkbag;
        }

    }



    public int editSilkbag(Silkbag silkbag) {
        return silkbagMapper.updateByPrimaryKey(silkbag);
    }

    public int addSilkbag(Silkbag silkbag) {
        return silkbagMapper.insert(silkbag);
    }

    public Silkbag getSilkbagById(Integer id) {
        return silkbagMapper.selectByPrimaryKey(id);
    }

    public int deleteSilkbag(Integer id) {
        return silkbagMapper.deleteByPrimaryKey(id);
    }



    public PageInfo<Stumedaldetail> getMetals(String token,Integer stuid, Integer page, Integer limit) {

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



        //判断是否有medal
        Example example = Example.builder(Stumedaldetail.class).build();
        Example.Criteria c=example.createCriteria();
        c.andEqualTo("stuid",stuid);
        Page<Stumedaldetail> p = PageHelper.startPage(page, limit);
        List<Stumedaldetail> datas = stumedaldetailMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo<>(datas);
        return pageInfo;

    }

    public Integer addMetalsShipment(String token,Integer stuid,Integer id,String name,String phone,String address){
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

        Stumedal stumedal= stumedalMapper.selectByPrimaryKey(id);
        stumedal.setName(name);
        stumedal.setPhone(phone);
        stumedal.setAddress(address);
        stumedal.setFlag(1);
        return stumedalMapper.updateByPrimaryKey(stumedal);

    }

    public PageInfo<Stumedaldetail> getMetalsShipment(String token,Integer stuid,Integer flag,Integer page,Integer limit){
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

        Example example=Example.builder(Stumedaldetail.class).build();
        Example.Criteria c=example.createCriteria();
        if(flag!=null){
            c.andEqualTo("flag",flag);
        }
        c.andEqualTo("stuid",stuid);

        Page<Stumedaldetail> p = PageHelper.startPage(page, limit);
        List<Stumedaldetail> datas= stumedaldetailMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo<>(datas);
        return pageInfo;

    }



    public Integer addMetalsShipment(Integer id,String name,String phone,String address,String shipmentcompany,String shipmentnum){


        Stumedal stumedal= stumedalMapper.selectByPrimaryKey(id);
        stumedal.setName(name);
        stumedal.setPhone(phone);
        stumedal.setAddress(address);
        stumedal.setShipmentcompany(shipmentcompany);
        stumedal.setShipmentnum(shipmentnum);
        stumedal.setFlag(1);
        return stumedalMapper.updateByPrimaryKey(stumedal);

    }

    public PageInfo<Stumedaldetail> getMetalsShipment(Integer stuid,Integer flag,Integer page,Integer limit){


        Example example=Example.builder(Stumedaldetail.class).build();
        Example.Criteria c=example.createCriteria();
        if(flag!=null){
            c.andEqualTo("flag",flag);
        }
        c.andEqualTo("stuid",stuid);

        Page<Stumedaldetail> p = PageHelper.startPage(page, limit);
        List<Stumedaldetail> datas= stumedaldetailMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo<>(datas);
        return pageInfo;

    }

    public Stumedal getStuMetalByid(Integer id){


        return stumedalMapper.selectByPrimaryKey(id);

    }

}
