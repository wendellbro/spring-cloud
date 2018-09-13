package com.business.gateway.model;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.business.gateway.utils.UUIDUtil;

import io.swagger.annotations.ApiModelProperty;

public class CommonResponse  implements Serializable {

    private static final long serialVersionUID = 5576237395711742681L;
    
	// 是否成功
	@ApiModelProperty(value="是否成功[true:成功，false:失败]")
	private boolean success;;

	// 返回编码
    @ApiModelProperty(value="返回编码 [200:成功，500：系统错误,请稍后重试或者联系管理员]")
	private String code; 
	
	@ApiModelProperty(value="返回消息")
	private String msg;
	
	@ApiModelProperty(value="异常信息")
	private String exception;
	
	@ApiModelProperty(value="返回内容")
	private Object data = null; 
	
	@ApiModelProperty(value="token值")
	private String token;
	
	public CommonResponse() {
		this.success = false;
		this.code = AppConstants.STATUS_ERROR;
		this.msg = AppConstants.STATUS_ERROR_MSG;
	}
	
	public CommonResponse(String code,String desc) {
		this.code = code;
		this.msg = desc;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

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

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getToken() {
		if(StringUtils.isEmpty(token)){
			token = UUIDUtil.uuid();
		}
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

}
