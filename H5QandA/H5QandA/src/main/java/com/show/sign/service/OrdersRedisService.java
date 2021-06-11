package com.show.sign.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.show.sign.entity.WX;
import com.show.sign.mapper.*;
import com.show.sign.pojo.*;
import com.show.sign.redis.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrdersRedisService {

    @Resource
    private StudentMapper studentMapper;
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

    @Resource
    private OrderstudentmonthnolimitMapper orderstudentmnothonlimitMapper;

    @Resource
    private OrderstudentweeknolimitMapper orderstudentweeknolimitMapper;

    @Resource
    private OrderweekdetailMapper orderweekdetailMapper;

    @Resource
    private OrdermonthdetailMapper ordermonthdetailMapper;


    @Autowired
    private RedisCacheManager redisCacheManager;



    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdfdate=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdftime=new SimpleDateFormat("HH:mm:ss");


    public void setNowOrders(){
        List<Orderstudent> result1 = orderStudentMapper.selectAll();
        List<Orderstudentweek> result2=orderstudentweekMapper.selectAll();
        List<Orderstudentmonth> result3=orderstudentmonthMapper.selectAll();
        List<Orderschool> result4=orderschoolMapper.selectAll();
        List<Ordertown> result5=ordertownMapper.selectAll();
        List<Orderteacher> result6=orderteacherMapper.selectAll();

        String json1 = JSON.toJSONString(result1);
        String json2 = JSON.toJSONString(result2);
        String json3 = JSON.toJSONString(result3);
        String json4 = JSON.toJSONString(result4);
        String json5 = JSON.toJSONString(result5);
        String json6 = JSON.toJSONString(result6);


        redisCacheManager.set(WX.orderstudent,json1);
        redisCacheManager.set(WX.orderstudentweek,json2);
        redisCacheManager.set(WX.orderstudentmonth,json3);
        redisCacheManager.set(WX.orderschool,json4);
        redisCacheManager.set(WX.ordertown,json5);
        redisCacheManager.set(WX.orderteacher,json6);

    }


    public void setWeekOrders(){
        List<Orderstudentweeknolimit> result = orderstudentweeknolimitMapper.selectAll();
        List<Orderstudentweeknolimit> finalresult=new LinkedList<Orderstudentweeknolimit>();
        int total=0;
        int mark=0;
        int times=0;
        for(Orderstudentweeknolimit record:result){
            if(total>=1000){
                if(record.getSummark()<mark||record.getTimes()>times) {
                    break;
                }
            }
            mark=record.getSummark();
            times=record.getTimes();
            finalresult.add(record);
            total++;
            Orderweekdetail detail=new Orderweekdetail();
            detail.setOrderdate(sdf.format(new Date()));
            detail.setStuid(record.getStuid());
            detail.setMarks(record.getSummark());
            detail.setTimes(record.getTimes());
            detail.setPosition(total);
            orderweekdetailMapper.insert(detail);

            Student student=studentMapper.selectByPrimaryKey(record.getStuid());
            student.setWeeknum(student.getWeeknum()+1);
            studentMapper.updateByPrimaryKey(student);
        }
    }

    public void setMonthOrders(){
        List<Orderstudentmonthnolimit> result = orderstudentmnothonlimitMapper.selectAll();
        List<Orderstudentmonthnolimit> finalresult=new LinkedList<Orderstudentmonthnolimit>();
        int total=0;
        int mark=0;
        int times=0;
        for(Orderstudentmonthnolimit record:result){
            if(total>=1000){
                if(record.getSummark()<mark||record.getTimes()>times) {
                    break;
                }
            }
            mark=record.getSummark();
            times=record.getTimes();
            finalresult.add(record);
            total++;

            Ordermonthdetail detail=new Ordermonthdetail();
            detail.setOrderdate(sdf.format(new Date()));
            detail.setStuid(record.getStuid());
            detail.setMarks(record.getSummark());
            detail.setTimes(record.getTimes());
            detail.setPosition(total);
            ordermonthdetailMapper.insert(detail);

            Student student=studentMapper.selectByPrimaryKey(record.getStuid());
            student.setMonthnum(student.getMonthnum()+1);
            studentMapper.updateByPrimaryKey(student);
        }
    }

    public JSONArray getOrder(String key){
        String result=redisCacheManager.get(key).toString();
        JSONArray object = JSONArray.parseArray(result);
        return object;
    }



}
