package com.business.gateway.remote.model;

import java.io.Serializable;

public class UserLoginResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;

	private String msg;
	
	private User data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}
	

}