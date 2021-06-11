package com.show.sign.pojo;

import javax.persistence.Table;

@Table(name = "requires")
public class Require {
	private String request;
	private String response;
	private Integer auto;

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getAuto() {
		return auto;
	}

	public void setAuto(Integer auto) {
		this.auto = auto;
	}
}
