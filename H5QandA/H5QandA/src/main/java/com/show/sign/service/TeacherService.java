package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.SchoolteacherMapper;
import com.show.sign.mapper.TeacherMapper;
import com.show.sign.pojo.Schoolteacher;
import com.show.sign.pojo.Teacher;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherService {
    @Resource
    private RedisService redisService;

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private SchoolteacherMapper schoolteacherMapper;


    public PageInfo<Schoolteacher> getDatas(Integer schoolid, String name, String code, Integer flag,Integer page, Integer limit){

            Example example = Example.builder(Schoolteacher.class).build();
            Example.Criteria c=example.createCriteria();

            if(schoolid!=null&&!schoolid.equals(0)){
                c.andEqualTo("schoolid",schoolid);
            }
            if(name!=null&&!name.equals("")){
                c.andLike("name","%"+name+"%");
            }
            if(code!=null&&!code.equals("")){
                c.andEqualTo("code",code);
            }
            if(flag!=null&&!flag.equals("")) {
                c.andEqualTo("tflag", flag);
            }
            Page<Schoolteacher> p = PageHelper.startPage(page, limit);
            List<Schoolteacher> datas = schoolteacherMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;

    }


    public int editTeacher(Teacher teacher) {
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    public int addTeacher(Teacher teacher) {
        int i=teacherMapper.insert(teacher);
        String code = String.format("%0" + 5 + "d", teacher.getId());
        teacher.setCode(code);
        teacherMapper.updateByPrimaryKey(teacher);
        return i;
    }

    public Teacher getTeacherById(Integer id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    public Schoolteacher getTeacherDetailById(Integer id) {

        return schoolteacherMapper.selectByPrimaryKey(id);
    }

    public int deleteTeacher(Integer id) {
        Teacher teacher=teacherMapper.selectByPrimaryKey(id);
        teacher.setFlag(1);
        return teacherMapper.updateByPrimaryKey(teacher);

    }
}
