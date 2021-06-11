package com.show.sign.task;

import com.google.gson.Gson;
import com.show.sign.entity.QuestionRedisReq;
import com.show.sign.entity.RedisRequire;
import com.show.sign.entity.WX;
import com.show.sign.redis.RedisCacheManager;
import com.show.sign.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kingsam
 * createTime 2020-11-07 22:37
 **/
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Resource
    private QuestionsService questionService;
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //if(false)
                while(true){
                    try{
                        String res=redisCacheManager.rpop(WX.updatequestion, WX.timeout);
                        if(res!=null){
                            Gson gson=new Gson();
                            //获得数据
                            RedisRequire rr=gson.fromJson(res, RedisRequire.class);
                            System.out.println("处理更新！"+rr.getRequireid()+rr.getData());
                            QuestionRedisReq req=gson.fromJson(rr.getData(), QuestionRedisReq.class);
                            //处理数据及获得结果
                            int result=questionService.update(req.getId(),req.getType());

                            //放回队列返回给前端
                            if(redisCacheManager.lpush(rr.getRequireid(), ""+result,WX.timeout))
                                System.out.println("修改成功！"+rr.getRequireid()+" id: "+rr.getData());
                            else
                                System.out.println("放入redis错误！");
                        }
                    }catch(Exception ex){
                        System.out.println("ScanOrderTask: ERROR: "+ex.toString());
                        try{Thread.sleep(1000);}catch(Exception e){}
                    }
                }
            }
        }).start();

    }
}