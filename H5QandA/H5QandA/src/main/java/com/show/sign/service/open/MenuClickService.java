package com.show.sign.service.open;


import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.show.sign.entity.WX;
import com.show.sign.mapper.PuserMapper;
import com.show.sign.pojo.Puser;
import com.show.sign.service.HttpService;
import com.show.sign.service.PuserService;
import com.show.sign.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * 创建人：herosky   
 * 创建时间：2015-3-30 下午5:13:57   
 * 描述：菜单点击事件，处理
 *
 */
@Service
public class MenuClickService {


	static Logger logger = LoggerFactory.getLogger(MenuClickService.class);
	@Resource
	private RedisService redisService;

	@Resource
	private PuserMapper puserMapper;

	@Resource
	private PuserService puserService;



	@Resource
	private HttpService httpService;
	/**
	 *
	 * 描述：@param eventKey
	 * 描述：@param fromUserName
	 * 描述：@param toUserName
	 * 描述：@return 接受用户点击事件，通过微信推送给用户消息，跳转页面，发送消息等
	 * 作者：herosky
	 */
	@Transactional
	public Puser getClickResponse(String eventKey, String fromUserName, String toUserName) {
		logger.info("eventKey is:" +eventKey);
		// TODO 判断evetKey事件处理
		if(eventKey.equals("2"))
		{
			if(redisService==null||redisService.getValue(WX.tokenname)==null){
		        	return null;
		    }
			// logger.info("***************************getaccess_token");
			String access_token=redisService.getValue(WX.tokenname).toString();
			
			// logger.info("***************************access_token"+access_token);
	        if(access_token.equals("")){
	        	return null;
	        }
	        //logger.info("fromUserName is:" +fromUserName+" toUserName is:" +toUserName);
			
			
//			 String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code".replace("APPID", WX.appid).replace("SECRET", WX.secret).replace("CODE", fromUserName);
//			 logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&"+URL);
//			 	//URLConnectionHelper是一个模拟发送http请求的类
//		        String jsonStr = HttpsGetUtil.doHttpsGetJson(URL);
//		        //System.out.println(jsonStr);
//		        //out.print(jsonStr);
//		        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
//		        String openid = jsonObj.get("openid").toString();
		       // logger.info("***************"+fromUserName+"******openid:*"+toUserName+"************************");
		        		
		        String user_info = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+fromUserName+"&lang=zh_CN";
	            
		       logger.info("%%%%%%%%%%%%"+user_info);
	            
	            String userInfoJson = httpService.postMap(user_info,null,null);
	            JSONObject userInfoJO = JSONObject.parseObject(userInfoJson);
	            if(userInfoJO.getIntValue("subscribe")==0){
	            	return null;
	            }
				logger.info("%%%%%%%%%%%%:::"+userInfoJO);
	            Puser user= puserService.addPuser(userInfoJO);
		        return user;	
		}
		
		
		return null;
	}

}
