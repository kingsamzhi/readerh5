package com.show.sign.service;

import com.show.sign.mapper.AccesslogMapper;
import com.show.sign.pojo.Accesslog;
import com.show.sign.utils.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AccesslogService {


    @Resource
    private AccesslogMapper accesslogMapper;
    @Resource
    private HttpService httpService;

    public void log(HttpServletRequest request, String info){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ip= IpUtil.getIpAddr(request);
        Accesslog log=new Accesslog();
        log.setIpaddress(ip);
        log.setTimeer(sdf.format(new Date()));
        log.setInfo(info);
        accesslogMapper.insert(log);
    }


}
