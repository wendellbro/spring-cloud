package com.business.gateway.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe 常量类
 * @author wupeng
 * @createtime 2017年9月12日
 */
public class AppConstants {
	
	public static final String REDIS_KEY_PREFIX = "roadoor::"; // redis缓存前缀
	
	/** redis session 前缀*/
	public static final String REDIS_SESSION_PREFIX = "roadoor:session:"; 
	/** redis缓存 session失效时间 秒*/
	public static final int REDIS_SESSION_EXPIRE =  60*60*24*180; 
	
	/** redis token 前缀*/
	public static final String REDIS_TOKEN_PREFIX = "roadoor:token:"; 
	/** redis缓存 token失效时间 秒*/
	public static final int REDIS_TOKEN_EXPIRE =  300; 
	
	/** session_id key*/
	public static final String SESSION_ID =  "session_id"; 
	/** token key*/
	public static final String TOKEN =  "token"; 
	/** 前端请求参数key*/
	public static final String REQUEST_KEY =  "argv"; 
	/** 后端请求参数msgId*/
	public static final String MSG_ID =  "msg_id"; 
	/** 后端请求参数userId*/
	public static final String USER_ID =  "user_id"; 
	/** 接口性能监控请求开始时间字段*/
	public static final String MONITOR_START_TIME =  "startTime"; 
	
	/**db create_by = 系统创建*/
	public final static String CREATEBY_SYSTEM = "sys";
	
	
	public static Map<String, String> statusMap = new HashMap<String, String>();
	/**
	 * 系统编码----------------------------------------------------------------
	 */
	public static final String STATUS_SUCCESS = "00";
	public static final String STATUS_SUCCESS_MSG = "成功";
	public static final String STATUS_ERROR = "01";
	public static final String STATUS_ERROR_MSG = "服务器异常";
	public static final String STATUS_SESSION_TIMEOUT= "401";
	public static final String STATUS_SESSION_TIMEOUT_MSG = "会话已失效";
	public static final String STATUS_TOKEN_DUPLICATE= "402";
	public static final String STATUS_TOKEN_DUPLICATE_MSG = "请勿重复提交";
	public static final String STATUS_PARAM_ERROR= "403";
	public static final String STATUS_PARAM_ERROR_MSG = "请求参数有误";
	
	/**
	 * 用户错误码----------------------------------------------------------------
	 */
	public static final String USER_ERROR_NOT_EXISTS = "101";
	public static final String USER_ERROR_NOT_EXISTS_MSG = "用户名不存在";
	public static final String USER_ERROR_ACCOUNT_PWD = "102";
	public static final String USER_ERROR_ACCOUNT_PWD_MSG = "用户名或密码错误";
	public static final String USER_ERROR_DUPLICATEDACCOUNT = "103";
	public static final String USER_ERROR_DUPLICATEDACCOUNT_MSG = "用户名已存在";
	public static final String USER_ERROR_OLD_PWD = "104";
	public static final String USER_ERROR_OLD_PWD_MSG = "旧密码错误";
	public static final String USER_ERROR_NOPERMISSION = "109";
	public static final String USER_ERROR_NOPERMISSION_MSG = "没有权限";

	static {
		statusMap.put(STATUS_ERROR, STATUS_ERROR_MSG);
		statusMap.put(STATUS_SUCCESS, STATUS_SUCCESS_MSG);
		statusMap.put(STATUS_SESSION_TIMEOUT, STATUS_SESSION_TIMEOUT_MSG);
		statusMap.put(STATUS_TOKEN_DUPLICATE, STATUS_TOKEN_DUPLICATE_MSG);
		statusMap.put(STATUS_PARAM_ERROR, STATUS_PARAM_ERROR_MSG);
		
		statusMap.put(USER_ERROR_NOT_EXISTS, USER_ERROR_NOT_EXISTS_MSG);
		statusMap.put(USER_ERROR_ACCOUNT_PWD, USER_ERROR_ACCOUNT_PWD_MSG);
		statusMap.put(USER_ERROR_DUPLICATEDACCOUNT, USER_ERROR_DUPLICATEDACCOUNT_MSG);
		statusMap.put(USER_ERROR_OLD_PWD, USER_ERROR_OLD_PWD_MSG);
		statusMap.put(USER_ERROR_NOPERMISSION, USER_ERROR_NOPERMISSION_MSG);
	}
	
	/**
	 * 同步状态
	 */
	public final static String SYSN_STATUS_NO = "0";
	public final static String SYSN_STATUS_YES = "1";
	
}
