package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.MarkconfigMapper;
import com.show.sign.mapper.MarkconfigMapper;
import com.show.sign.pojo.Markconfig;
import com.show.sign.pojo.Markconfig;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MarkconfigService {
    @Resource
    private RedisService redisService;

    @Resource
    private MarkconfigMapper markconfigMapper;


    public PageInfo<Markconfig> getDatas( Integer page, Integer limit){

            Example example = Example.builder(Markconfig.class).build();
            Example.Criteria c=example.createCriteria();

            Page<Markconfig> p = PageHelper.startPage(page, limit);
            List<Markconfig> datas = markconfigMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;

    }


    public int editMarkconfig(Markconfig markconfig) {
        return markconfigMapper.updateByPrimaryKey(markconfig);
    }

    public int addMarkconfig(Markconfig markconfig) {
        return markconfigMapper.insert(markconfig);
    }

    public Markconfig getMarkconfigById(Integer id) {
        return markconfigMapper.selectByPrimaryKey(id);
    }

    public int deleteMarkconfig(Integer id) {
        return markconfigMapper.deleteByPrimaryKey(id);
    }
}
