package com.show.sign.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

import com.show.sign.entity.Params;
import com.show.sign.entity.SignResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 签名工具类
 * 
 * @author xuanweiyao
 * @date 10:01 2019/5/30
 */
@Slf4j
public class SignUtil {
    public static Logger logger = LoggerFactory.getLogger(SignUtil.class);
    /**
     * @param params
     *            所有的请求参数都会在这里进行排序加密
     * @return 验证签名结果
     */
    public static SignResult verifySign(SortedMap<String, Object> params) {
        SignResult result=new SignResult();

        String appId = params.get("appId").toString();
        String timestamp = params.get("timestamp").toString();
        String signType = params.get("signType").toString();
        String authType = params.get("authType").toString();
        String sign = params.get("sign").toString();

        if(!authType.equals("openapi")){
            result.setCode(10003);
            result.setMessage("参数authType错误！");
            return result;
        }

        int outtime=10*60*1000;//10分钟有效
        try {
            Long gettime = Long.parseLong(timestamp);
            Date nowdate=new Date();
            Long nowtime= nowdate.getTime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (nowtime-gettime>outtime||gettime-nowtime>outtime){
                result.setCode(10005);
                result.setMessage("时间戳超时：服务器时间："+sdf.format(nowdate)+"发送的时间戳为"+gettime+" 当前时间戳为:"+nowtime+"相差:"+(nowtime-gettime)+"有效范围:"+outtime);
                return result;
            }
        }catch(Exception ex){
            result.setCode(10006);
            result.setMessage("时间戳转换错误:"+ex.toString());
            return result;
        }
       // log.info("Url Sign : {}", sign);
        if (StringUtils.isEmpty(appId)&&StringUtils.isEmpty(timestamp)&&StringUtils.isEmpty(signType)&&StringUtils.isEmpty(authType)&&StringUtils.isEmpty(sign)) {
           result.setCode(10007);
           result.setMessage("缺少签名参数");
           return result;
        }

        if(!appId.equals(Params.APPID)){
            result.setCode(10004);
            result.setMessage("未找到匹配的APPID");
            return result;
        }

        // 把参数加密
        String paramsSign = getParamsSign(params);

        System.out.println("签名字符串："+paramsSign);
        logger.error("正确的签名:"+paramsSign);
        if(!StringUtils.isEmpty(paramsSign) && sign.equals(paramsSign)){
            result.setCode(10000);
            result.setMessage("验证成功");
        }else{
            result.setCode(10008);
            result.setMessage("签名错误！");
        }

        return result;
    }

    /**
     * @param params
     *            所有的请求参数都会在这里进行排序加密
     * @return 得到签名
     */
    public static String getParamsSign(SortedMap<String, Object> params) {
        // 要先去掉 Url 里的 Sign
        params.remove("sign");
        params.remove("authType");
        String url="";
        for (SortedMap.Entry<String, Object> entry : params.entrySet()) {
             url+=entry.getKey() +"=" + entry.getValue().toString()+"&";
        }
        url=url.substring(0,url.length()-1);
        url+=Params.APPSECRET;
        System.out.println("加密字符串："+url);
        logger.error("组合用于签名的字符串:"+url);
        return DigestUtils.md5DigestAsHex(url.getBytes()).toUpperCase();
    }
}