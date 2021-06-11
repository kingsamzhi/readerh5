package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.School;
import com.show.sign.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Resource
    private SchoolService schoolService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getSchools")
    public @ResponseBody Map<String, Object> getSchools(String name,String city,String area,String town,String type, Integer flag, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<School> users = schoolService.getDatas(name,city,area,town,type, flag, page, limit);
        Map<String, Object> map = new HashMap<String, Object>();
        if (users != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", users.getTotal());
            map.put("data", users.getList());
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到记录！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }

    /**
     * 修改管理员用户
     *
     * @param school
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editSchool")
    public @ResponseBody
    Map<String, Object> editSchools(School school) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = schoolService.editSchool(school);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", school);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", school);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param school
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addSchool")
    public @ResponseBody
    Map<String, Object> addSchools(School school) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = schoolService.addSchool(school);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "添加信息成功！");
            map.put("count", n);
            map.put("data", school);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "添加信息失败！");
            map.put("count", n);
            map.put("data", school);
            return map;
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getSchoolById")
    public @ResponseBody
    Map<String, Object> getSchoolById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        School School = schoolService.getSchoolById(id);
        if (School != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", School);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到数据，请重新查询！");
            map.put("count", "0");
            map.put("data", School);
            return map;
        }
    }



    /**
     * 删除
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/deleteSchool")
    public @ResponseBody
    Map<String, Object> deleteSchool(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = schoolService.deleteSchool(id);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "删除成功！");
            map.put("count", n);
            map.put("data", id);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "删除失败！");
            map.put("count", n);
            map.put("data", id);
            return map;
        }
    }

}