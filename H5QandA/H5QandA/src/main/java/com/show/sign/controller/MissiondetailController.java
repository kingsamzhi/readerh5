package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.Missiondetail;
import com.show.sign.pojo.Stuerrorbook;
import com.show.sign.service.MissiondetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/missiondetail")
public class MissiondetailController {

    @Resource
    private MissiondetailService missiondetailService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getChildrenErrorBook")
    public @ResponseBody Map<String, Object> getMissiondetails(Integer stuid,String token, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Stuerrorbook> errorBooks = missiondetailService.getErrorBook(token,stuid, page, limit);
        Map<String, Object> map = new HashMap<String, Object>();
        if (errorBooks != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", errorBooks.getTotal());
            map.put("data", errorBooks.getList());
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到记录！可能是非法访问！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getMissiondetails")
    public @ResponseBody Map<String, Object> getMissiondetails(Integer missionid, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Missiondetail> users = missiondetailService.getDatas(missionid, page, limit);
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
     * @param missiondetail
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editMissiondetail")
    public @ResponseBody
    Map<String, Object> editMissiondetails(Missiondetail missiondetail) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = missiondetailService.editMissiondetail(missiondetail);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", missiondetail);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", missiondetail);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param missiondetail
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addMissiondetail")
    public @ResponseBody
    Map<String, Object> addMissiondetails(Missiondetail missiondetail) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = missiondetailService.addMissiondetail(missiondetail);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "添加信息成功！");
            map.put("count", n);
            map.put("data", missiondetail);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "添加信息失败！");
            map.put("count", n);
            map.put("data", missiondetail);
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
    @RequestMapping(value = "/getMissiondetailById")
    public @ResponseBody
    Map<String, Object> getMissiondetailById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Missiondetail Missiondetail = missiondetailService.getMissiondetailById(id);
        if (Missiondetail != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", Missiondetail);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到数据，请重新查询！");
            map.put("count", "0");
            map.put("data", Missiondetail);
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
    @RequestMapping("/deleteMissiondetail")
    public @ResponseBody
    Map<String, Object> deleteMissiondetail(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = missiondetailService.deleteMissiondetail(id);
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