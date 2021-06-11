package com.show.sign.service;

import com.show.sign.redis.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisCacheManager redisMapper;


    public boolean setValueTime(String key,String value,long time){
        return redisMapper.set(key,value,time);
    }

    public boolean setValue(String key,String value){
        return redisMapper.set(key,value);
    }
    public String getValue(String key){
        return redisMapper.get(key)!=null ? redisMapper.get(key).toString():null;
    }

    public boolean lpush(String key,String value,long time){
        return redisMapper.lpush(key,value,time);
    }
    public String rpop(String key,long time){
        return redisMapper.rpop(key,time);
    }

    public boolean setURL(String value){
        return redisMapper.set("VIPRESPONSEURL",value);
    }

    public String getURL(){
        return redisMapper.get("VIPRESPONSEURL").toString();
    }
}
