package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.show.sign.entity.*;
import com.show.sign.mapper.*;
import com.show.sign.pojo.*;
import com.show.sign.utils.PIC;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Service
public class SigninService {
    @Resource
    private RedisService redisService;

    @Resource
    private SigninMapper signinMapper;

    @Resource
    private StuopenMapper stuopenMapper;

    @Resource
    private SignrecordMapper signrecordMapper;

    @Resource
    private StudentMapper studentMapper;

    static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat sdfdate=new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdftime=new SimpleDateFormat("HH:mm:ss");


   public List<Signrecord> getSignin(String token,int stuid,String month){

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
        Example example = Example.builder(Signrecord.class).build();
        Example.Criteria c=example.createCriteria();
        c.andEqualTo("stuid",stuid);
        c.andLike("signdate",month+"%");
        List<Signrecord> signins=signrecordMapper.selectByExample(example);
        return signins;
   }

    public SiginRecordShow getSignDates(String token,int stuid,String rootPath){
        SiginRecordShow srs=new SiginRecordShow();

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

       String date=getXDateBefore(7);

        Example e= Example.builder(Signrecord.class).build();
        Example.Criteria ce=e.createCriteria();
        ce.andEqualTo("stuid",stuid);
        ce.andGreaterThan("signdate",date);
        ce.andEqualTo("flag",0);
        List<Signrecord> total=signrecordMapper.selectByExample(e);
        String nowdate=sdfdate.format(new Date());//获取今天
        int totalsign=0;


        for(int i=0;i<7;i++){
            nowdate=getXDateBefore(i);
            for(Signrecord signrecord:total){
                if(signrecord.getSigndate().equals(nowdate)){
                    totalsign++;
                }
            }
        }


        srs.setSignrecords(total);
        srs.setTotal(totalsign);
        //生成图片
        PIC pic=new PIC();

        Student student=studentMapper.selectByPrimaryKey(stuid);


        String path=rootPath+ File.separator+"qrcreate"+File.separator+"background.png";
        String qrpath=rootPath+ File.separator+"qrcreate"+File.separator+"qrcode.png";

        System.out.println("**path:"+path);


        BufferedImage b = pic.loadImageLocal(path);
        BufferedImage qrcode = pic.loadImageLocal(qrpath);

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("d/MMM , ", Locale.ENGLISH);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy ", Locale.ENGLISH);

        Font font1 = new Font("宋体", Font.BOLD, 55); // 添加字体的属性设置
        BufferedImage res1=pic.modifyImage(b,dateFormat1.format(new Date()),380,780,font1,Color.black);

        Font font2 = new Font("宋体", Font.BOLD, 40); // 添加字体的属性设置
        BufferedImage res2=pic.modifyImage(res1,dateFormat2.format(new Date()),600,780,font2,Color.black);


        Font font = new Font("宋体", Font.BOLD, 200); // 添加字体的属性设置
        BufferedImage res=pic.modifyImage(res2,totalsign+"",480,1100,font,Color.orange);

        BufferedImage resx=pic.modifyImagetogeter(qrcode,res);

        String stroagepath=rootPath+"qrcreate"+File.separator+"images"+File.separator+student.getSchoolid()+File.separator;
        System.out.println(stroagepath);
        System.out.println(stroagepath+stuid+".jpg");
        File file=new File(stroagepath);
        if(!file.exists()||!file.isDirectory()){
            file.mkdirs();
        }

        String pic1=(stroagepath+stuid+".jpg").replaceAll("//", "/").replace("\\\\", "\\");

        pic.writeImageLocal(pic1, resx);

        srs.setImages("/qrcreate/images/"+student.getSchoolid()+"/"+stuid+".jpg");

        return srs;
    }

    public static String getXDateBefore(int x){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -x);         //利用Calendar实现Date日期+1天
        Date sDate = c.getTime();
        return sdfdate.format(sDate);
    }

}
