package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.Markconfig;
import com.show.sign.service.MarkconfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/markconfig")
public class MarkconfigController {

    @Resource
    private MarkconfigService markconfigService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getMarkconfigs")
    public @ResponseBody Map<String, Object> getMarkconfigs( @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Markconfig> users = markconfigService.getDatas(page, limit);
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

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getMarkconfigById")
    public @ResponseBody Map<String, Object> getMarkconfigById(Integer id){
        Markconfig markconfig = markconfigService.getMarkconfigById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        if (markconfig != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", 1);
            map.put("data", markconfig);
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
     * @param markconfig
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editMarkconfig")
    public @ResponseBody
    Map<String, Object> editMarkconfigs(Markconfig markconfig) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = markconfigService.editMarkconfig(markconfig);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", markconfig);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", markconfig);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param markconfig
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addMarkconfig")
    public @ResponseBody
    Map<String, Object> addMarkconfigs(Markconfig markconfig) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = markconfigService.addMarkconfig(markconfig);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "添加信息成功！");
            map.put("count", n);
            map.put("data", markconfig);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "添加信息失败！");
            map.put("count", n);
            map.put("data", markconfig);
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
    @RequestMapping("/deleteMarkconfig")
    public @ResponseBody
    Map<String, Object> deleteMarkconfig(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = markconfigService.deleteMarkconfig(id);
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