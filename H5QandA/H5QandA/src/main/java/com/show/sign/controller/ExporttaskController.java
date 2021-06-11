package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.Exporttask;
import com.show.sign.service.ExporttaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/exporttask")
public class ExporttaskController {

    @Resource
    private ExporttaskService exporttasksService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getExporttasks")
    public @ResponseBody Map<String, Object> getExporttasks(String groupid, Integer flag, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Exporttask> exporttasks = exporttasksService.getExporttasks(groupid,flag,page,limit);
        Map<String, Object> map = new HashMap<String, Object>();
        if (exporttasks != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", exporttasks.getTotal());
            map.put("data", exporttasks.getList());
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
     * @param exporttasks
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editExporttasks")
    public @ResponseBody
    Map<String, Object> editExporttasks(Exporttask exporttasks) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = exporttasksService.editExporttasks(exporttasks);
        if (n > 0) {
            map.put("code", 0);
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", exporttasks);
            return map;
        } else {
            map.put("code", 1);
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", exporttasks);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param exporttask
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addExporttasks")
    public @ResponseBody
    Map<String, Object> addExporttasks(Exporttask exporttask) {
        Map<String, Object> map = new HashMap<String, Object>();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now=sdf.format(new Date());
        exporttask.setCreatetime(now);
        exporttask.setGroupid(UUID.randomUUID().toString());


        int n = exporttasksService.addExporttasks(exporttask);
        if (n > 0) {
            map.put("code", 0);
            map.put("msg", "添加成功！");
            map.put("count", n);
            map.put("data", exporttask);
            return map;
        } else {
            map.put("code", 1);
            map.put("msg", "添加失败！");
            map.put("count", n);
            map.put("data", exporttask);
            return map;
        }
    }

    /**
     * 根据账号查询用户
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getExporttasksById")
    public @ResponseBody
    Map<String, Object> getExporttasksById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Exporttask exporttasks = exporttasksService.getExporttasksById(id);
        if (exporttasks != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", exporttasks);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到该用户，请重新查询！");
            map.put("count", "0");
            map.put("data", exporttasks);
            return map;
        }
    }



    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/deleteExporttasks")
    public @ResponseBody
    Map<String, Object> deleteExporttasks(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = exporttasksService.deleteExporttasks(id);
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

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/cleanExporttasks")
    public @ResponseBody
    Map<String, Object> cleanExporttasks(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = exporttasksService.cleanExporttasks(id);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "清空题库成功！");
            map.put("count", n);
            map.put("data", id);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "清空题库失败！");
            map.put("count", n);
            map.put("data", id);
            return map;
        }
    }

}