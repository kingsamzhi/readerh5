package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.Require;
import com.show.sign.service.open.RequireService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/require")
public class RequireController {

    @Resource
    private RequireService requireService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getRequires")
    public @ResponseBody Map<String, Object> getRequires(String request, Integer auto, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Require> users = requireService.getRequires(request,auto, page, limit);
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
     * @param require
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editRequire")
    public @ResponseBody
    Map<String, Object> editRequires(Require require) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = requireService.editRequire(require);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", require);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", require);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param require
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addRequire")
    public @ResponseBody
    Map<String, Object> addRequires(Require require) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = requireService.addRequire(require);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "添加信息成功！");
            map.put("count", n);
            map.put("data", require);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "添加信息失败！");
            map.put("count", n);
            map.put("data", require);
            return map;
        }
    }

    /**
     * 根据id查询
     *
     * @param request
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getRequireById")
    public @ResponseBody
    Map<String, Object> getRequireById(String request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Require Require = requireService.getRequireByRequest(request);
        if (Require != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", Require);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到数据，请重新查询！");
            map.put("count", "0");
            map.put("data", Require);
            return map;
        }
    }



    /**
     * 删除
     *
     * @param require
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/deleteRequire")
    public @ResponseBody
    Map<String, Object> deleteRequire(Require require) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = requireService.deleteRequire(require);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "删除成功！");
            map.put("count", n);
            map.put("data", require);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "删除失败！");
            map.put("count", n);
            map.put("data", require);
            return map;
        }
    }

}