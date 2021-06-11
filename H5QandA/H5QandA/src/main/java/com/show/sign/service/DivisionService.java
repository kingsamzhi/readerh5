package com.show.sign.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;

import com.show.sign.mapper.DivisionMapper;
import com.show.sign.pojo.Division;
import tk.mybatis.mapper.entity.Example;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * AdminService
 *
 * @author Albert
 * @Version 1.0
 * @date 2019/10/14 16:59
 **/
@Service
public class DivisionService {

    @Resource
    private DivisionMapper divisionMapper;

    
    public List<Division> getDivisionByPcode(String pcode) {
    	Example example = Example.builder(Division.class).build();
        Example.Criteria c=example.createCriteria();
        if(pcode!=null&&!pcode.equals("")) {
        	c.andEqualTo("pcode", pcode);
        	List<Division> datas = this.divisionMapper.selectByExample(example);
             return datas;
        }
        return null; 
       
    }
    
    
    public PageInfo<Division> getDivision(String name, Integer page, Integer limit) {
    	Example example = Example.builder(Division.class).build();
        Example.Criteria c=example.createCriteria();
        if(name!=null&&!name.equals("")) {
            c.andLike("name", "%" + name + "%");
            
        }
         Page<Division> p = PageHelper.startPage(page, limit);
            List<Division> datas = this.divisionMapper.selectByExample(example);
            return p.toPageInfo();
       
    }
    public int getDivisionCount(String name) {
    	Example example = Example.builder(Division.class).build();
        Example.Criteria c=example.createCriteria();
        if(name!=null&&!name.equals("")) {
            c.andLike("name", "%" + name + "%");
        }
        return divisionMapper.selectCountByExample(example);
    }

    public int editDivision(Division division) {
        return divisionMapper.updateByPrimaryKey(division);
    }

    public int addDivision(Division division) {
        return divisionMapper.insert(division);
    }

    public Division getDivisionById(Integer id) {
        return divisionMapper.selectByPrimaryKey(id);
    }

   

    public int deleteDivision(Division division) {
        return divisionMapper.deleteByPrimaryKey(division.getId());
    }

}
