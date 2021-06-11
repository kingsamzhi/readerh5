package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.*;
import com.show.sign.pojo.*;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {
    @Resource
    private RedisService redisService;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private StuopenMapper stuopenMapper;

    @Resource
    private StudentopenMapper studentopenMapper;

    @Resource
    private StudentdetailMapper studentdetailMapper;

    @Resource
    private DefaultchildMapper defaultchildMapper;


    public Studentdetail addDefaultchild(String token,Integer stuid){
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
        Defaultchild defaultchild=defaultchildMapper.selectByPrimaryKey(openid);
        if(defaultchild!=null){
            defaultchild.setStuid(stuid);
            defaultchildMapper.updateByPrimaryKey(defaultchild);
        }else {
            defaultchild = new Defaultchild();
            defaultchild.setStuid(stuid);
            defaultchild.setOpenid(openid);
            defaultchildMapper.insert(defaultchild);
        }

        return studentdetailMapper.selectByPrimaryKey(defaultchild.getStuid());
    }


    public Studentdetail getDefaultchild(String token){
        String openid=redisService.getValue(token);
        if(StringUtil.isNullOrEmpty(openid)){
            return null;
        }
        Defaultchild defaultchild=defaultchildMapper.selectByPrimaryKey(openid);
        if(defaultchild==null){
            return null;
        }
        return studentdetailMapper.selectByPrimaryKey(defaultchild.getStuid());
    }

    public List<Studentdetail> getChildrens(String token, Integer flag){
        String openid=redisService.getValue(token);
        if(StringUtil.isNullOrEmpty(openid)){
            return null;
        }
        Example example = Example.builder(Studentdetail.class).build();
        Example.Criteria c=example.createCriteria();

        if (openid!=null&&!openid.equals("")){
            c.andEqualTo("openid",openid);
        }
        if (flag!=null){
            c.andEqualTo("flag",flag);
        }

        List<Studentdetail> datas = studentdetailMapper.selectByExample(example);
        return datas;

    }


    public PageInfo<Student> getDatas(Integer schoolid,Integer teacherid,Integer name,String bgrade,String bclass,Integer page, Integer limit){

            Example example = Example.builder(Student.class).build();
            Example.Criteria c=example.createCriteria();

            if(schoolid!=null&&!schoolid.equals(0)){
                c.andEqualTo("schoolid",schoolid);
            }
            if (teacherid!=null&&!teacherid.equals(0)){
                c.andEqualTo("teacherid",teacherid);
            }
            if (bgrade!=null&&!bgrade.equals(0)){
                c.andEqualTo("bgrade",bgrade);
            }
            if (bclass!=null&&!bclass.equals(0)){
                c.andEqualTo("bclass",bclass);
            }
            if (name!=null&&!name.equals(0)){
                c.andLike("name","%"+name+"%");
            }


            Page<Student> p = PageHelper.startPage(page, limit);
            List<Student> datas = studentMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;

    }


    public int editStudent(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    public int addStudent(Student student) {
        String grade=student.getBgrade();
        switch(grade){
            case "四年级": student.setLevels(1); break;
            case "五年级": student.setLevels(2); break;
            case "六年级": student.setLevels(3); break;
            case "初一": student.setLevels(4); break;
            case "初二": student.setLevels(5); break;
            case "初三": student.setLevels(6); break;
        }

        return studentMapper.insert(student);
    }

    //创建关系即可
    public int addStudent(Student student,String token) {
        int res=0;

        String openid=redisService.getValue(token);
        student.setFlag(0);
        String grade=student.getBgrade();
        switch(grade){
            case "四年级": student.setLevels(1); break;
            case "五年级": student.setLevels(2); break;
            case "六年级": student.setLevels(3); break;
            case "初一": student.setLevels(4); break;
            case "初二": student.setLevels(5); break;
            case "初三": student.setLevels(6); break;
        }

        Example example = Example.builder(Student.class).build();
        Example.Criteria c=example.createCriteria();
        c.andEqualTo("schoolid",student.getSchoolid());
        c.andEqualTo("teacherid",student.getTeacherid());
        c.andEqualTo("bgrade",student.getBgrade());
        c.andEqualTo("bclass",student.getBclass());
        c.andEqualTo("name",student.getName());
        List<Student> datas = studentMapper.selectByExample(example);
        //如果已经有这个学生，同学校，同年级，同班级，同名。
        if(datas!=null&&datas.size()>0){
            Student stu=datas.get(0);

            Example e2 = Example.builder(Stuopen.class).build();
            Example.Criteria c2=e2.createCriteria();
            c2.andEqualTo("openid",openid);
            c2.andEqualTo("stuid",stu.getId());

            List<Stuopen> opendatas = stuopenMapper.selectByExample(e2);
            if(opendatas!=null&&opendatas.size()>0){//检查是否有个关系，如果已经存在，则不操作。
                return 0;
            }else{
                Stuopen open=new Stuopen();
                open.setStuid(stu.getId());
                open.setOpenid(openid);
                res=stuopenMapper.insert(open);
            }
        }else{
            student.setWeeknum(0);
            student.setMonthnum(0);
            studentMapper.insert(student);
            Stuopen open=new Stuopen();
            open.setStuid(student.getId());
            open.setOpenid(openid);
            res=stuopenMapper.insert(open);
        }
        return res;
    }

    public Studentdetail getStudentByIdwithToken(String token, Integer stuid){
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

        return studentdetailMapper.selectByPrimaryKey(stuid);
    }


    public Student getStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    public int deleteStudent(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    public int deleteStudent(String token,Integer stuid) {
        String openid=redisService.getValue(token);
        //获取openid并获取学生信息，验证是否是绑定的openid
        Example stuexample = Example.builder(Stuopen.class).build();
        Example.Criteria stuc=stuexample.createCriteria();
        stuc.andEqualTo("stuid",stuid);//当前这个学生
        stuc.andEqualTo("openid",openid);
        List<Stuopen> stus=stuopenMapper.selectByExample(stuexample);

        if(openid==null||stus==null||stus.size()<=0){
            return 0;
        }else{
            return stuopenMapper.delete(stus.get(0));
        }
    }
}
