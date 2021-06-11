package com.show.sign.controller;

import com.show.sign.entity.WX;
import com.show.sign.pojo.*;
import com.show.sign.service.OrdersRedisService;
import com.show.sign.service.OrdersService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redisorders")
public class OrderRedisController {

    @Resource
    private OrdersRedisService orderRedisService ;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getOrderstudent")
    public @ResponseBody Map<String, Object> getOrderstudent() {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        List<Object> datas = orderRedisService.getOrder(WX.orderstudent);
        if (datas != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", datas.size());
            map.put("data", datas);
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
    @RequestMapping(value = "/getOrderstudentweek")
    public @ResponseBody Map<String, Object> getOrderstudentweek() {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        List<Object> datas = orderRedisService.getOrder(WX.orderstudentweek);
        if (datas != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", datas.size());
            map.put("data", datas);
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
    @RequestMapping(value = "/getOrderstudentmonth")
    public @ResponseBody Map<String, Object> getOrderstudentmonth() {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        List<Object> datas = orderRedisService.getOrder(WX.orderstudentmonth);
        if (datas != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", datas.size());
            map.put("data", datas);
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
    @RequestMapping(value = "/getOrderteacher")
    public @ResponseBody Map<String, Object> getOrderteacher() {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        List<Object> datas = orderRedisService.getOrder(WX.orderteacher);
        if (datas != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", datas.size());
            map.put("data", datas);
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
    @RequestMapping(value = "/getOrderschool")
    public @ResponseBody Map<String, Object> getOrderschool() {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        List<Object> datas = orderRedisService.getOrder(WX.orderschool);
        if (datas != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", datas.size());
            map.put("data", datas);
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
    @RequestMapping(value = "/getOrdertown")
    public @ResponseBody Map<String, Object> getOrdertown() {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        List<Object> datas = orderRedisService.getOrder(WX.ordertown);
        if (datas != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", datas.size());
            map.put("data", datas);
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