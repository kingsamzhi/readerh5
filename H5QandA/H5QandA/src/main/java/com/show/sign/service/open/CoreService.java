package com.show.sign.service.open;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.show.sign.entity.WX;
import com.show.sign.entity.wx.Image;
import com.show.sign.entity.wx.MessageResponse;
import com.show.sign.entity.wx.MessageUtil;
import com.show.sign.pojo.Puser;
import com.show.sign.pojo.Require;
import com.show.sign.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;




@Service
public class CoreService {

	
	static Logger logger = LoggerFactory.getLogger(CoreService.class);
	@Resource
	MenuClickService mcs;
	
	@Resource
	RequireService requireService;

	@Resource
	private RedisService redisService;

	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */

	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "";

			// xml请求解析
			// 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 从HashMap中取出消息中的字段；
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String content = requestMap.get("Content");
			// 从HashMap中取出消息中的字段；
			
			logger.info("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);

			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
				String eventType = requestMap.get("Event");// 事件类型
				logger.info("event type is:" +eventType);

				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
					
					Require require=new Require();
					require.setAuto(3);
					List<Require> requires=requireService.getRequireByAuto(require);
					
					for(Require r:requires){
						respContent+=(r.getResponse()+"\n\n");
					}
					
					return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
					String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					
					Puser user=mcs.getClickResponse(eventKey, fromUserName , toUserName);
					String token=UUID.randomUUID().toString();
					if(user.getOpenid().equals("oeGfF6NUGBv1LdgPCGyQPERuxueg")){

						redisService.setValue(token,user.getOpenid());
					}else {
						redisService.setValueTime(token, user.getOpenid(), WX.expire);
					}
					respContent="<a href='http://hstt.fsjdwl.cn/readerh5/index.html?token="+token+"'>点击进入答题 </a>";
					return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
				}
			}else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String contentx=requestMap.get("Content");
				//自动回复图片的话，先获取图片id：
				// postman url：  https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=
				/*
				 *  {
				   		"type":"image",//获取图片(image,voice,)
				   		"offset":0,//获取从0开始
				   		"count":10//获取10个
					}
				*/
				
				if(contentx.contains("水果")){
					//User user=mcs.getClickResponse("2", fromUserName , toUserName);
					Image image=new Image();
					image.setMediaId("YCwOj9_CS-NLpmgp7Ag7S5FkQlBl5NNoFirCZM2l0Vs");
					return MessageResponse.getImageMessage(fromUserName , toUserName , image); 
				}
				

				if(contentx==null){
					respContent="您好，请输入内容~ \n";
				}
				else{
					Require require=new Require();
					require.setRequest(contentx);
					require=requireService.getRequireByRequest(require);
					if(require!=null){//如果找到。则显示内容
						respContent=require.getResponse();
					}else{
						if(contentx.equals("01")){							
							String MediaId="YCwOj9_CS-NLpmgp7Ag7S9f7HWGgjLKn4Gwbf9Q5s0g";
							//logger.error(content);
							Image image=new Image();
							image.setMediaId(MediaId);
							System.out.println("********测试发送image"+image.getMediaId());
							return MessageResponse.getImageMessage(fromUserName, toUserName, image);
						}else{
							//auto 0(关键字回复)\1（回复所有）\2（默认回复）\3（关注回复）\
							respContent="收到啦，请耐心等待回复~ \n";
							require=new Require();
							require.setRequest(respContent);
							require.setAuto(1);
							List<Require> requires=requireService.getRequireByAuto(require);
							for(Require r:requires){
									respContent+=(r.getResponse()+"\n\n");
							}
							
						}
					}
				}

				return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
			}
			
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
