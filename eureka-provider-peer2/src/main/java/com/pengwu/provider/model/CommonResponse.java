package com.pengwu.provider.model;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

public class CommonResponse<T>  implements Serializable {

    private static final long serialVersionUID = 5576237395711742681L;
    
	// 是否成功
	@ApiModelProperty(value="是否成功[true:成功，false:失败]")
	private boolean success;;

	// 返回编码
    @ApiModelProperty(value="返回编码 [200:成功，500：系统错误,请稍后重试或者联系管理员]")
	private long code;  // 错误代码
	
	@ApiModelProperty(value="是否成功[true:成功，false:失败]")
	private String desc;  // 错误类型
	
	@ApiModelProperty(value="返回内容")
	private T obj = null; //列表信息
	
	public CommonResponse() {
		this.success = false;
		this.code = AppConstants.SYSTEM_ERROR;
		this.desc = AppConstants.SYSTEM_ERROR_MSG;
	}
	
	public CommonResponse(long code,String desc) {
		this.code = code;
		this.desc = desc;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

}
