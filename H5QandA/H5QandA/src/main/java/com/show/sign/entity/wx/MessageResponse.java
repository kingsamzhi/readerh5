package com.show.sign.entity.wx;

import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author jiangyin
 */
public class MessageResponse {
	
	/**
	 * 回复文本消息
	 * @param fromUserName
	 * @param toUserName
	 * @param respContent
	 * @return
	 */
	public static String getTextMessage(String fromUserName , String toUserName , String respContent) {
		
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		
		textMessage.setContent(respContent);
		return MessageUtil.textMessageToXml(textMessage);
	}
	
	/**
	 * 创建图文消息
	 * @param fromUserName
	 * @param toUserName
	 * @param articleList
	 * @return
	 */
	public static String getNewsMessage(String fromUserName , String toUserName , List<Article> articleList) {
		
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);
		
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		return MessageUtil.newsMessageToXml(newsMessage);
	}
	
	/**
	 * 创建图片消息
	 * @param fromUserName
	 * @param toUserName
	 * @param image
	 * @return
	 * 

--------------------- 
作者：PzzZ 
来源：CSDN 
原文：https://blog.csdn.net/qq_17635843/article/details/76223268 
版权声明：本文为博主原创文章，转载请附上博文链接！
	 */
	public static String getImageMessage(String fromUserName , String toUserName , Image image) {
		
		ImageMessage newsMessage = new ImageMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);

		// 设置图文消息包含的图文集合
		newsMessage.setImage(image);
		// 将图文消息对象转换成xml字符串
		return MessageUtil.imageMessageToXml(newsMessage);
	}
}