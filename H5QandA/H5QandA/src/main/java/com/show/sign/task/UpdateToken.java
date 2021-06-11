package com.show.sign.task;

import com.google.gson.Gson;
import com.show.sign.entity.QuestionRedisReq;
import com.show.sign.entity.RedisRequire;
import com.show.sign.entity.WX;
import com.show.sign.redis.RedisCacheManager;
import com.show.sign.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Calendar;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class UpdateToken {

    @Resource
    private HttpService httpService;

    @Resource
    private AccesslogService accesslogService;

    @Resource
    private WxService wxService;

    @Resource
    private ExporttaskService exporttaskService;

    @Resource
    private QuestionsService questionService;
    @Autowired
    private RedisCacheManager redisCacheManager;

    @Resource
    private OrdersRedisService orderRedisService;


    @Scheduled(cron="0 0/30 * * * ?")   //每30分钟执行一次
    public void updateToken(){

        wxService.updateToken();
    }


    @Scheduled(fixedDelay = 30000)
    public void exportData(){

        exporttaskService.exportTasks();
    }

    @Scheduled(cron="0 0 0 */1 * ?")   //每天0点执行一次
    public void updateStatistics1(){

        orderRedisService.setNowOrders();
    }

    @Scheduled(cron="0 0 12 * * ?")   //每天中午十二点执行一次
    public void updateStatistics2(){
        orderRedisService.setNowOrders();
    }

    @Scheduled(cron = "0 15 10 28-31 * ?")
    public void getMonthOrder() {//每月最后一天23点55分 执行一次
        final Calendar c = Calendar.getInstance();
        if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
            //是最后一天
            orderRedisService.setMonthOrders();
        }
    }


    @Scheduled(cron="0 55 23 ? * SAT")   //每周执行一次
    public void getWeekOrder(){
        orderRedisService.setWeekOrders();
    }

}