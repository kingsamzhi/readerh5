package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.entity.AnswerResult;
import com.show.sign.entity.MissionResult;
import com.show.sign.pojo.Mission;
import com.show.sign.service.MissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mission")
public class MissionController {

    @Resource
    private MissionService missionService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getMission")
    public @ResponseBody Map<String, Object> getMissions(String token,Integer stuid,String replacedate){
        AnswerResult res = missionService.getMissions(token,stuid,replacedate);
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", res.getCode());
            map.put("msg", res.getMsg());
            map.put("count", res.getCount());
            map.put("data", res);
            return map;

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/submitMission")
    public @ResponseBody Map<String, Object> submitMission(String token,Integer id,String answer){
        AnswerResult res = missionService.submitMission(token,id,answer);
        Map<String, Object> map = new HashMap<String, Object>();
        if(res!=null){
            map.put("code", "0");
            map.put("msg", res.getMsg());
            map.put("count", 1);
            map.put("data", res);
        }else{
            map.put("code", "1");
            map.put("msg", res.getMsg());
            map.put("count", 0);
            map.put("data", res);
        }

        return map;

    }

    /**
     * 修改管理员用户
     *
     * @param mission
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editMission")
    public @ResponseBody
    Map<String, Object> editMissions(Mission mission) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = missionService.editMission(mission);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", mission);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", mission);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param mission
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addMission")
    public @ResponseBody
    Map<String, Object> addMissions(Mission mission) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = missionService.addMission(mission);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "添加信息成功！");
            map.put("count", n);
            map.put("data", mission);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "添加信息失败！");
            map.put("count", n);
            map.put("data", mission);
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
    @RequestMapping(value = "/getMissionById")
    public @ResponseBody
    Map<String, Object> getMissionById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Mission Mission = missionService.getMissionById(id);
        if (Mission != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", Mission);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到数据，请重新查询！");
            map.put("count", "0");
            map.put("data", Mission);
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
    @RequestMapping("/deleteMission")
    public @ResponseBody
    Map<String, Object> deleteMission(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = missionService.deleteMission(id);
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