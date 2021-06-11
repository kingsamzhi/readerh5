package com.show.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.show.sign.entity.*;
import com.show.sign.service.HttpService;
import com.show.sign.service.RedisService;
import com.show.sign.service.PuserService;
import com.show.sign.service.open.CoreService;
import com.show.sign.utils.CheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 签名测试
 * 
 * @author show
 * @date 10:53 2019/5/30
 */
@Controller
@RequestMapping("/sign")
public class SignController {


    @Resource
    private PuserService puserService;
    @Resource
    private RedisService redisService;
    @Resource
    private HttpService httpService;
    @Resource
    CoreService coreService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getOpenId")
    public String getOpenId(String code,HttpServletRequest request){
            String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WX.appid+"&secret="+ WX.secret+"&code="+code+"&grant_type=authorization_code";
            String message=httpService.postMap(url,null,null);
            JSONObject json = JSONObject.parseObject(message);
            String openId  = json.getString("openid");

            request.setAttribute("openid",openId);
            return "/main";
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/saveUser")
    public String saveUser(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        String openid = request.getParameter("openid");
        String unionid = request.getParameter("unionid");

        if(openid!=null&&!openid.equals("")){
            String access_token=redisService.getValue(WX.tokenname).toString();
            if(access_token==null||access_token.equals("")){
                return "/notoken";
            }
            String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
            String userInfoJson=httpService.postMap(url,null,null);
            JSONObject userInfoJO = JSONObject.parseObject(userInfoJson);

            if(userInfoJO.getIntValue("subscribe")==0){
                return "/attention";
            }
            puserService.addPuser(userInfoJO);
            request.setAttribute("openid",openid);

            return "/main";
        }
        return "/main";
    }




    @PostMapping("/setURL")
    public @ResponseBody SortedMap<String, Object> setURL(@RequestBody RedisReq redisReq) {
        HttpServletResponse response =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        SortedMap<String, Object> responseparams=new TreeMap<>();
        Date now=new Date();
        long timestamp=now.getTime();

        if(redisReq.getServiceId()!=null&&redisReq.getServiceId().equals(Params.serviceId)) {
           boolean res=redisService.setURL(redisReq.getUrl());
           if(res) {

               responseparams.put("message", "操作成功！");
               responseparams.put("code", 10000 + "");
           }
        }else{
            responseparams.put("message", "参数校验成功,serviceId错误！");
            responseparams.put("code", 10001+"");
            responseparams.put("timestamp",timestamp+"");
        }

        return responseparams;
    }

    //做服务器校验
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value= "/checkWX",method = RequestMethod.GET)
    public @ResponseBody String valid(String signature,String timestamp,String nonce,String echostr) throws IOException{


        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
            return echostr;
        }
        return "error";
    }

    @RequestMapping(value="/checkWX",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getWeiXinMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
        //初始化配置文件
        String respMessage = coreService.processRequest(request);//调用CoreService类的processRequest方法接收、处理消息，并得到处理结果；

        // 响应消息
        //调用response.getWriter().write()方法将消息的处理结果返回给用户
        return respMessage;
    }
}
