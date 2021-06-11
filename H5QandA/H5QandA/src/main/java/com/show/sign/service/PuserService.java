package com.show.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.entity.BusinessReq;
import com.show.sign.entity.SignResult;
import com.show.sign.mapper.AccesslogMapper;
import com.show.sign.mapper.PuserMapper;
import com.show.sign.pojo.Adminuser;
import com.show.sign.pojo.Puser;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.*;
import java.util.Map.Entry;
import java.util.SortedMap;

@Service
public class PuserService {


    @Resource
    private AccesslogMapper accesslogMapper;

    @Resource
    private HttpService httpService;
    @Resource
    private PuserMapper puserMapper;


   public Puser addPuser(JSONObject userInfoJO){

       String openid=userInfoJO.getString("openid");

       Puser ouser=puserMapper.selectByPrimaryKey(openid);
       Puser user = new Puser();
       if(ouser!=null){
           user=ouser;
       }
       user.setCity(userInfoJO.getString("city"));
       user.setCountry(userInfoJO.getString("country"));
       user.setGroupid(userInfoJO.getString("groupid"));
       user.setHeadimgurl(userInfoJO.getString("headimgurl"));
       user.setLanguage(userInfoJO.getString("language"));
       user.setMark(0);
       user.setNickname(userInfoJO.getString("nickname"));
       user.setOpenid(userInfoJO.getString("openid"));
       user.setProvince(userInfoJO.getString("province"));
       user.setQr_scene(userInfoJO.getLong("qr_scene"));
       user.setQr_scene_str(userInfoJO.getString("qr_scene_str"));
       user.setRemark(userInfoJO.getString("remark"));

       String sex="未知";
       if(userInfoJO.getIntValue("sex")==1){
           sex="男";
       }else if(userInfoJO.getIntValue("sex")==2){
           sex="女";
       }
       user.setSex(sex);
       user.setSubscribe(userInfoJO.getIntValue("subscribe"));
       user.setSubscribe_time(userInfoJO.getString("subscribe_time"));
       user.setUnionid(userInfoJO.getString("unionid"));
       if(ouser!=null){
           puserMapper.updateByPrimaryKey(user);
       }else {
           puserMapper.insert(user);
       }
       return user;
   }



}
