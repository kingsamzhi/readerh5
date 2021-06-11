package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.MedalMapper;
import com.show.sign.pojo.Medal;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedalService {
    @Resource
    private RedisService redisService;

    @Resource
    private MedalMapper medalMapper;


    public PageInfo<Medal> getDatas( Integer page, Integer limit){

            Example example = Example.builder(Medal.class).build();
            Example.Criteria c=example.createCriteria();

            Page<Medal> p = PageHelper.startPage(page, limit);
            List<Medal> datas = medalMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;

    }


    public int editMedal(Medal medal) {
        return medalMapper.updateByPrimaryKey(medal);
    }

    public int addMedal(Medal medal) {
        return medalMapper.insert(medal);
    }

    public Medal getMedalById(Integer id) {
        return medalMapper.selectByPrimaryKey(id);
    }

    public int deleteMedal(Integer id) {
        return medalMapper.deleteByPrimaryKey(id);
    }
}
