package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.*;
import com.show.sign.service.AdminuserService;
import com.show.sign.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrdersService ordersService ;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getOrderstudent")
    public @ResponseBody Map<String, Object> getOrderstudent() {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        List<Orderstudent> datas = ordersService.orderStudent();
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
        List<Orderstudentweek> datas = ordersService.orderStudentweek();
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
        List<Orderstudentmonth> datas = ordersService.orderStudentmonth();
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
        List<Orderteacher> datas = ordersService.orderTeacher();
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
        List<Orderschool> datas = ordersService.orderSchool();
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
        List<Ordertown> datas = ordersService.orderTown();
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