package com.show.sign.controller;

import com.show.sign.entity.AnswerResult;
import com.show.sign.entity.MissionResult;
import com.show.sign.entity.SiginRecordShow;
import com.show.sign.pojo.Mission;
import com.show.sign.pojo.Signin;
import com.show.sign.pojo.Signrecord;
import com.show.sign.service.MissionService;
import com.show.sign.service.SigninService;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/signin")
public class SigninController {

    @Resource
    private SigninService signinService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getSignin")
    public @ResponseBody Map<String, Object> getSignin(String token,Integer stuid,String date){
        List<Signrecord> res = signinService.getSignin(token,stuid,date);
        Map<String, Object> map = new HashMap<String, Object>();
        if(res!=null){
            map.put("code", "0");
            map.put("msg", "查询成功");
            map.put("count", res.size());
            map.put("data", res);
        }else {
            map.put("code", "1");
            map.put("msg", "找不到记录");
            map.put("count", 0);
            map.put("data", null);
        }
            return map;

    }

    /**
     * 获取连续打卡天数并生成图片
     *
     * @param
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/getSignDates")
    public @ResponseBody
    Map<String, Object> getSignDates(String token, Integer stuid) {
        Map<String, Object> map = new HashMap<String, Object>();
        //String  rootPath= ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String  rootPath= "/data/tomcat/webapps/readerh5/WEB-INF/classes/static/";
        SiginRecordShow n = signinService.getSignDates(token,stuid,rootPath);
        if (n != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", 1);
            map.put("data", n);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "查询失败！");
            map.put("count", 0);
            map.put("data", n);
            return map;
        }
    }


}