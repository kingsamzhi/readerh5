package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.entity.QuestionsReq;
import com.show.sign.pojo.Questions;
import com.show.sign.service.QuestionsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Resource
    private QuestionsService questionsService ;


    /**
     * 管理员用户列表
     *
     * @param groupid
     * @param flag
     * @param page
     * @param limit
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getQuestions")
    public @ResponseBody Map<String, Object> getQuestions(String groupid,Integer flag, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        PageInfo<Questions> admins = questionsService.getQuestions(groupid,flag, page, limit);
        if (admins != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", admins.getTotal());
            map.put("data", admins.getList());
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
    @RequestMapping(value = "/getQuestionsWithOptions")
    public @ResponseBody Map<String, Object> getQuestionsWithOptions(String groupid,Integer flag, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        PageInfo<QuestionsReq> admins = questionsService.getQuestionsWithOptions(groupid,flag, page, limit);
        if (admins != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", admins.getTotal());
            map.put("data", admins.getList());
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到记录！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }



}