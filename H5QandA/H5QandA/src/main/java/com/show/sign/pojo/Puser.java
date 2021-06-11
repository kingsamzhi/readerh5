package com.show.sign.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "puser")
public class Puser {
	@Id
	private String openid;
	private int subscribe;
	private String nickname;
	private String sex;
	private String language;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private String subscribe_time;
	private String unionid;
	private String remark;
	private String groupid;
	private long qr_scene;
	private String qr_scene_str;
	
	
	
	
	private long mark;

	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getMark() {
		return mark;
	}
	public void setMark(long mark) {
		this.mark = mark;
	}
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public long getQr_scene() {
		return qr_scene;
	}
	public void setQr_scene(long qr_scene) {
		this.qr_scene = qr_scene;
	}
	public String getQr_scene_str() {
		return qr_scene_str;
	}
	public void setQr_scene_str(String qr_scene_str) {
		this.qr_scene_str = qr_scene_str;
	}	
	
	
	
}
