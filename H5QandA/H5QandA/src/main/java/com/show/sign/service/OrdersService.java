package com.show.sign.service;

import com.show.sign.mapper.*;
import com.show.sign.pojo.*;
import com.show.sign.utils.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrdersService {


    @Resource
    private OrderstudentMapper orderStudentMapper;

    @Resource
    private OrderstudentweekMapper orderstudentweekMapper;

    @Resource
    private OrderstudentmonthMapper orderstudentmonthMapper;

    @Resource
    private OrderschoolMapper orderschoolMapper;

    @Resource
    private OrdertownMapper ordertownMapper;

    @Resource
    private OrderteacherMapper orderteacherMapper;

    public List<Orderstudent> orderStudent(){
        return orderStudentMapper.selectAll();
    }

    public List<Orderstudentweek> orderStudentweek(){
        return orderstudentweekMapper.selectAll();
    }

    public List<Orderstudentmonth> orderStudentmonth(){
        return orderstudentmonthMapper.selectAll();
    }

    public List<Orderschool> orderSchool(){
        return orderschoolMapper.selectAll();
    }

    public List<Ordertown> orderTown(){
        return ordertownMapper.selectAll();
    }

    public List<Orderteacher> orderTeacher(){
        return orderteacherMapper.selectAll();
    }

}
