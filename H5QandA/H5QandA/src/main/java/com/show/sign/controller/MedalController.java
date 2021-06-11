package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.Medal;
import com.show.sign.service.MedalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/medalx")
public class MedalController {

    @Resource
    private MedalService medalService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getMedals")
    public @ResponseBody Map<String, Object> getMedals( @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Medal> users = medalService.getDatas(page, limit);
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
    @RequestMapping(value = "/getMedalById")
    public @ResponseBody Map<String, Object> getMedalById(Integer id){
        Medal medal = medalService.getMedalById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        if (medal != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", 1);
            map.put("data", medal);
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
     * @param medal
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editMedal")
    public @ResponseBody Map<String, Object> editMedal(Medal medal) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = medalService.editMedal(medal);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", medal);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", medal);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param medal
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addMedal")
    public @ResponseBody  Map<String, Object> addMedal(Medal medal) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = medalService.addMedal(medal);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "添加信息成功！");
            map.put("count", n);
            map.put("data", medal);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "添加信息失败！");
            map.put("count", n);
            map.put("data", medal);
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
    @RequestMapping("/deleteMedal")
    public @ResponseBody  Map<String, Object> deleteMedal(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = medalService.deleteMedal(id);
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