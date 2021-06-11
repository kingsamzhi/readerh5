package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.entity.Result;
import com.show.sign.pojo.Adminuser;
import com.show.sign.pojo.Division;
import com.show.sign.service.AdminuserService;
import com.show.sign.service.DivisionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DivisionController
 *
 * @author kingsam
 * @Version 1.0
 * @date 2019/10/14 16:39
 **/
@RestController
@RequestMapping("/division")
public class DivisionController {

    @Resource
    private DivisionService divisionService;

    /**
     * 通过pcode找到子级行政区划
     *
     * @param pcode
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getDivisionByPcode")
    public @ResponseBody   Map<String, Object> getDivisionByPcode(String pcode) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        List<Division> divisions = divisionService.getDivisionByPcode(pcode);
        if (divisions == null) {
            map.put("code", "1");
            map.put("msg", "登录失败！");
            map.put("count", "0");
            map.put("data", divisions);
            return map;
        }else {
            map.put("code", "0");
            map.put("msg", "查找成功！");
            map.put("count", divisions.size());
            map.put("data", divisions);
            return map;
        }
    }

    /**
     * 行政区划列表
     *
     * @param name  null
     * @param page
     * @param limit
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getDivisions")
    public @ResponseBody
    Result getDivisions(String name, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        PageInfo<Division> divisions = divisionService.getDivision(name, page, limit);
        return divisions==null
    			? Result.error("没有符合的数据！")
    					: Result.success("查询成功！", divisions.getTotal(), divisions.getList());
    }

    /**
     * 修改行政区域
     *
     * @param division
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editDivision")
    public @ResponseBody
    Map<String, Object> editDivision(Division division) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = divisionService.editDivision(division);
        if (n > 0) {
            map.put("code", 0);
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", division);
            return map;
        } else {
            map.put("code", 1);
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", division);
            return map;
        }
    }

    /**
     * 添加行政区划
     *
     * @param division
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addDivision")
    public @ResponseBody
    Map<String, Object> addDivision(Division division) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = divisionService.addDivision(division);
        if (n > 0) {
            map.put("code", 0);
            map.put("msg", "管理员添加成功！");
            map.put("count", n);
            map.put("data", division);
            return map;
        } else {
            map.put("code", 1);
            map.put("msg", "管理员添加失败！");
            map.put("count", n);
            map.put("data", division);
            return map;
        }
    }

    /**
     * 根据id查询行政区划
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getDivisionById")
    public @ResponseBody
    Map<String, Object> getDivisionById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Division division = divisionService.getDivisionById(id);
        if (division != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", division);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到该用户，请重新查询！");
            map.put("count", "0");
            map.put("data", division);
            return map;
        }
    }

   


    /**
     * 删除行政区划
     *
     * @param division
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/deleteDivision")
    public @ResponseBody
    Map<String, Object> deleteDivision(Division division) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = divisionService.deleteDivision(division);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "删除成功！");
            map.put("count", n);
            map.put("data", division);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "删除失败！");
            map.put("count", n);
            map.put("data", division);
            return map;
        }
    }
}
