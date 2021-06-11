package com.show.sign.entity.wx;

import java.util.List;

/** 
 * 图文消息 
 *  
 * @author jiangyin 
 * NewsMessage.java
 * 	 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[image]]></MsgType>
<Image>
<MediaId><![CDATA[media_id]]></MediaId>
</Image>
</xml>
 */
public class ImageMessage  {
	

	// 接收方帐号（收到的OpenID�? 
    private String ToUserName;  
    // 发送者微信号  
    private String FromUserName;  
    // 消息创建时间 （整型）  
    private long CreateTime;  
    // 消息类型（text/music/news�? 
    private String MsgType;  
	
    public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image Image) {
		this.Image = Image;
	}
    
}
