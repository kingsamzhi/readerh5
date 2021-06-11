package com.show.sign.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "defaultchild")
public class Defaultchild {
	@Id
	private String openid;
	private Integer stuid;



	public Integer getStuid() {
		return stuid;
	}

	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}

	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
