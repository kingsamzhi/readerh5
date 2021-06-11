package com.show.sign.service;


import com.alibaba.fastjson.JSONObject;

import com.show.sign.mapper.AccesslogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.show.sign.entity.WX;

@Service
public class WxService {
    @Resource
    private AccesslogMapper accesslogMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private HttpService httpService;

    public void updateToken(){
        String appid=WX.appid;
        String secret=WX.secret;
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret+"";

        String access_token="";
        int expires_in=7200;
        try {
            System.out.println("获取accesstoken");//TODO:dd
            String userInfoJson=httpService.postMap(url,null,null);
            System.out.println("userInfoJson:"+userInfoJson);//TODO:dd
            JSONObject json = JSONObject.parseObject(userInfoJson);

            access_token = json.getString("access_token");
            expires_in=json.getInteger("expires_in");
            redisService.setValueTime(WX.tokenname, access_token, expires_in); //7200

        } catch (Exception e) {
            System.out.print("err message：：："+e.getMessage());
        }
        if(access_token!=null) {
            String url2 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
            String requestUrl = url2.replace("ACCESS_TOKEN", access_token);
            // 发起GET请求获取凭证
            String ticket = null;

            try {
                String jsons = httpService.postMap(requestUrl, null, null);
                JSONObject jsonx = JSONObject.parseObject(jsons);
                ticket = jsonx.getString("ticket");
                int expires = jsonx.getIntValue("expires_in");
                redisService.setValueTime(WX.ticketname, ticket, expires); //7200

            } catch (Exception e) {
                System.out.print("err message" + e.getMessage());
            }
        }

    }



}
