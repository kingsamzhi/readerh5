package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.SchoolMapper;
import com.show.sign.pojo.School;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolService {
    @Resource
    private RedisService redisService;

    @Resource
    private SchoolMapper schoolMapper;

    public PageInfo<School> getDatas(String name,String city,String area,String town, String type,Integer flag,Integer page, Integer limit){

            Example example = Example.builder(School.class).build();
            Example.Criteria c=example.createCriteria();

            if(flag!=null&&!flag.equals("")) {
                c.andEqualTo("flag", flag);
            }
            if(city!=null&&!city.equals("")){
                c.andEqualTo("city",city);
            }
            if(area!=null&&!area.equals("")){
                c.andEqualTo("area",area);
            }
            if(town!=null&&!town.equals("")){
                c.andEqualTo("town",town);
            }
            if(name!=null&&!name.equals("")){
                c.andLike("name","%"+name+"%");
            }
            if(type!=null&&!type.equals("")){
                c.andEqualTo("type",type);
            }
            Page<School> p = PageHelper.startPage(page, limit);
            List<School> datas = schoolMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;

    }


    public int editSchool(School school) {
        return schoolMapper.updateByPrimaryKey(school);
    }

    public int addSchool(School school) {
        return schoolMapper.insert(school);
    }

    public School getSchoolById(Integer id) {
        return schoolMapper.selectByPrimaryKey(id);
    }

    public int deleteSchool(Integer id) {
        School school=schoolMapper.selectByPrimaryKey(id);
        school.setFlag(1);
        return schoolMapper.updateByPrimaryKey(school);
    }
}
