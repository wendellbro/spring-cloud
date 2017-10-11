package com.pengwu.provider.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * @author wupeng
 *
 */
public class AppConstants {
	
	/** redis缓存前缀*/
	public static final String REDIS_KEY_PREFIX = "instudio:"; 
	/**db create_by = 系统创建*/
	public final static String CREATEBY_SYSTEM = "sys";
	
	
	public static Map<Long, String> statusMap = new HashMap<Long, String>();
	/**
	 * 系统编码----------------------------------------------------------------
	 */
	public static final long STATUS_SUCCESS = 200;
	public static final String STATUS_SUCCESS_MSG = "成功";
	public static final long SYSTEM_ERROR = 500;
	public static final String SYSTEM_ERROR_MSG = "系统错误,请稍后重试或者联系管理员";
	/**
	 * 数据同步编码 300 = 已无待同步的数据
	 */
	public static final long SYSN_ERROR = 300;
	public static final String SYSN_ERROR_MSG = "已无待同步的数据";
	
	/**
	 * 用户错误码----------------------------------------------------------------
	 */
	public static final long USER_ERROR_NOT_EXISTS = 101;
	public static final String USER_ERROR_NOT_EXISTS_MSG = "用户名不存在";
	public static final long USER_ERROR_ACCOUNT_PWD = 102;
	public static final String USER_ERROR_ACCOUNT_PWD_MSG = "用户名或密码错误";
	public static final long USER_ERROR_DUPLICATEDACCOUNT = 103;
	public static final String USER_ERROR_DUPLICATEDACCOUNT_MSG = "用户名已存在";
	public static final long USER_ERROR_OLD_PWD = 104;
	public static final String USER_ERROR_OLD_PWD_MSG = "旧密码错误";
	public static final long USER_ERROR_NOPERMISSION = 109;
	public static final String USER_ERROR_NOPERMISSION_MSG = "没有权限";

	static {
		statusMap.put(SYSTEM_ERROR, SYSTEM_ERROR_MSG);
		statusMap.put(STATUS_SUCCESS, STATUS_SUCCESS_MSG);
		statusMap.put(SYSN_ERROR, SYSN_ERROR_MSG);
		statusMap.put(USER_ERROR_NOT_EXISTS, USER_ERROR_NOT_EXISTS_MSG);
		statusMap.put(USER_ERROR_ACCOUNT_PWD, USER_ERROR_ACCOUNT_PWD_MSG);
		statusMap.put(USER_ERROR_DUPLICATEDACCOUNT, USER_ERROR_DUPLICATEDACCOUNT_MSG);
		statusMap.put(USER_ERROR_OLD_PWD, USER_ERROR_OLD_PWD_MSG);
		statusMap.put(USER_ERROR_NOPERMISSION, USER_ERROR_NOPERMISSION_MSG);
	}
	
	/**
	 * 商品列表同步状态
	 */
	public final static String SYSN_STATUS_NO = "0";
	public final static String SYSN_STATUS_YES = "1";
	
	/**
	 * 电商平台
	 */
	public final static String PLATFORM_EBAY = "ebay";
	public final static String PLATFORM_WISH = "wish";
	public final static String PLATFORM_ALIEXPRESS = "aliexpress";
	
	/**
	 * 电商平台产品链接前缀
	 */
	public final static String PLATFORM_PRODUCT_URL_EBAY = "http://www.ebay.com/itm/";
	public final static String PLATFORM_PRODUCT_URL_WISH = "https://www.wish.com/#cid=";
	public final static String PLATFORM_PRODUCT_URL_ALIEXPRESS = "https://www.aliexpress.com/item/name/%s.html";
	
	
	/**
	 * 平台编码——对应数据库表cms_platform的platform_id
		<br>1		Ebay		
		<br>2		Amazon
		<br>5		Taobao
		<br>10	AliExpress
		<br>16	Wish
		<br>18	Lazada
		<br>23	Tophatter
		<br>26	Alibaba
		<br>85	Joom
	 */
	public static final int PLATFORM_ID_EBAY = 1;
	public static final int PLATFORM_ID_AMAZON = 2;
	public static final int PLATFORM_ID_TAOBAO = 5;
	public static final int PLATFORM_ID_ALIEXPRESS = 10;
	public static final int PLATFORM_ID_WISH = 16;
	public static final int PLATFORM_ID_JOOM = 85;
	public static final int PLATFORM_ID_TOPHATTER = 23;
	
	
	/**马帮shop同步地址*/
	public static final String MABANG_SHOP_URL = "http://instudio.gnway.cc:92/SysnachDemo/shop/findshop";
}
