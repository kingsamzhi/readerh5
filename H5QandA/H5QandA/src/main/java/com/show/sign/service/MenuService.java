package com.show.sign.service;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.show.sign.entity.WX;
import com.show.sign.utils.HttpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MenuService {
 
	@Resource
    private RedisService redisService;
	@Resource
	private HttpService httpService;

	
	public Map<String,Object> getMenu(){
		String access_token=redisService.getValue(WX.tokenname).toString();
		
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
		String requestUrl = url.replace("ACCESS_TOKEN", access_token);
		// 发起GET请求获取凭证
		String jsonObject = null;
		Map<String,Object> map=new HashMap<String,Object>();
		SortedMap<String,Object> headerMap=new TreeMap<String,Object>();

		try {
			jsonObject = httpService.postMap(requestUrl, headerMap, null);
			JSONObject json=JSONObject.parseObject(jsonObject);
			for (Map.Entry<String, Object> entry : json.entrySet()) {
				map.put(entry.getKey(), entry.getValue());
			}
			if(map.get("errcode")!=null&&Integer.parseInt(map.get("errcode").toString())==46003){
				map.clear();
				String defaultmenu="{\"menu\": {\"button\": [{\"name\": \"默认菜单\",\"sub_button\": [],\"type\": \"click\",\"key\": \"2\"}]}}";
				JSONObject json2=JSONObject.parseObject(defaultmenu);
				for (Map.Entry<String, Object> entry : json2.entrySet()) {
					map.put(entry.getKey(), entry.getValue());
				}
			}
		} catch (Exception e) {
			// 获取token失败
			// log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.get("errcode"), jsonObject.get("errmsg"));
			System.err.println("获取token失败 errcode:"+e.toString());
		}
		return map;
		
	}
	public Map<String,Object> getMaterial(String type,String page,String limit){
		String access_token=redisService.getValue(WX.tokenname).toString();
		
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		String requestUrl = url.replace("ACCESS_TOKEN", access_token);

		
		String data="{\"type\":\""+type+"\",\"offset\":\""+page+"\",\"count\":\""+limit+"\"}";
		// 发起GET请求获取凭证
		String jsonObject = null;
		Map<String,Object> map=new HashMap<String,Object>();

		try {
			jsonObject = httpService.postMap(requestUrl, null, null);
			JSONObject json=JSONObject.parseObject(jsonObject);
			for (Map.Entry<String, Object> entry : json.entrySet()) {
				map.put(entry.getKey(), entry.getValue());
			}
		} catch (Exception e) {
			// 获取token失败
			// log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.get("errcode"), jsonObject.get("errmsg"));
			System.err.println("获取token失败 errcode:"+e.toString());
		}
		return map;
		
	}
	
	public Map<String,Object> setMenu(String menu){
		String access_token=redisService.getValue(WX.tokenname).toString();

		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		String requestUrl = url.replace("ACCESS_TOKEN", access_token);

		// 发起GET请求获取凭证
		Map<String,Object> jsonObject = null;
		try {
			jsonObject = HttpsUtil.httpRequest(requestUrl, "POST", menu);
		} catch (Exception e) {
			// 获取token失败
			// log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.get("errcode"), jsonObject.get("errmsg"));
			System.err.println("获取token失败 errcode:"+e.toString());
		}
		return jsonObject;
		
	}
	
}
