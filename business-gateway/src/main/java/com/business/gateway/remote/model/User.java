package com.business.gateway.remote.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private String uId;

	private String uCustCode;

	private String urealName;
	
	private String loginMobile;

	private String uAccNo;


	public String getUId() {
		return this.uId;
	}

	public void setUId(String uId) {
		this.uId = uId;
	}

	public String getuCustCode() {
		return uCustCode;
	}

	public void setuCustCode(String uCustCode) {
		this.uCustCode = uCustCode;
	}

	public String getUrealName() {
		return urealName;
	}

	public void setUrealName(String urealName) {
		this.urealName = urealName;
	}

	public String getuAccNo() {
		return uAccNo;
	}

	public void setuAccNo(String uAccNo) {
		this.uAccNo = uAccNo;
	}

	public String getLoginMobile() {
		return loginMobile;
	}

	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}

}