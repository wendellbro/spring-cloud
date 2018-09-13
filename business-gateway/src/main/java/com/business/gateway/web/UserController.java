package com.business.gateway.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.business.gateway.model.AppConstants;
import com.business.gateway.model.CommonRequest;
import com.business.gateway.model.CommonResponse;
import com.business.gateway.remote.UserRemoteApiService;
import com.business.gateway.remote.model.User;
import com.business.gateway.remote.model.UserLoginResponse;
import com.business.gateway.service.RedisService;
import com.business.gateway.utils.JSONUtil;
import com.business.gateway.utils.UUIDUtil;

import io.swagger.annotations.ApiOperation;

/**  
 * @describe 用户控制器
 * @author wupeng
 * @createtime  2017年7月5日
 */
@RestController
@RequestMapping("/roadoor-gateway/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRemoteApiService userRemoteApiService;
	@Autowired
	private RedisService<String> redisService;
	
	@ApiOperation(value="用户登录")
	@PostMapping(value="/login")
	public CommonResponse login(@RequestParam("argv")  String requestBody){
		CommonResponse response = new CommonResponse();
		try {
			CommonRequest commonRequest	 = JSONUtil.parseObject(requestBody, CommonRequest.class);
			if(StringUtils.isEmpty(commonRequest.getSessionId())){
				response.setSuccess(true);
				response.setCode("01");
				response.setMsg("参数有误：session_id为空");
				return response;
			}
			commonRequest.setMsgId(UUIDUtil.uuid());		//网关独立接口，须设置msg_id参数
			UserLoginResponse userLoginResponse = userRemoteApiService.userLogin(JSONUtil.toJSONString(commonRequest));
			if(userLoginResponse != null){
				if("00".equals(userLoginResponse.getCode())){
					User user = userLoginResponse.getData();
					// 用户session_id写入redis
					redisService.put(AppConstants.REDIS_SESSION_PREFIX+commonRequest.getSessionId(), JSONObject.toJSONString(user), AppConstants.REDIS_SESSION_EXPIRE);
					response.setSuccess(true);
					response.setData(user);
					response.setCode(AppConstants.STATUS_SUCCESS);
					response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_SUCCESS));
				}else{
					response.setSuccess(true);
					response.setCode(userLoginResponse.getCode());
					response.setMsg(userLoginResponse.getMsg());
				}
			}
		} catch (Exception e) {
			LOGGER.error("登录异常：{}", e);
			response.setException(e.getMessage());
		}
		return response;
	}
	
	@ApiOperation(value="用户注册")
	@PostMapping(value="/register")
	public CommonResponse register(@RequestParam("argv") String requestBody,HttpServletRequest httpServletRequest){
		CommonResponse response = new CommonResponse();
		try {
			CommonRequest commonRequest	 = JSONUtil.parseObject(requestBody, CommonRequest.class);
			if(StringUtils.isEmpty(commonRequest.getSessionId())){
				response.setSuccess(true);
				response.setCode("01");
				response.setMsg("参数有误：session_id为空");
				return response;
			}
			commonRequest.setMsgId(UUIDUtil.uuid());		//网关独立接口，须设置msg_id参数
			UserLoginResponse userLoginResponse = userRemoteApiService.userRegister(JSONUtil.toJSONString(commonRequest));
			if(userLoginResponse != null){
				if("00".equals(userLoginResponse.getCode())){
					User user = userLoginResponse.getData();
					// 用户session_id写入redis
					redisService.put(AppConstants.REDIS_SESSION_PREFIX+commonRequest.getSessionId(), JSONObject.toJSONString(user), AppConstants.REDIS_SESSION_EXPIRE);
					response.setSuccess(true);
					response.setData(user);
					response.setCode(AppConstants.STATUS_SUCCESS);
					response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_SUCCESS));
				}else{
					response.setSuccess(true);
					response.setCode(userLoginResponse.getCode());
					response.setMsg(userLoginResponse.getMsg());
				}
			}
		} catch (Exception e) {
			LOGGER.error("登录异常：{}", e);
			response.setException(e.getMessage());
		}
		return response;
	}
	/**
	 * 退出登录
	 * @author wupeng
	 * @param loginName
	 * @param password
	 * @return
	 */
	@ApiOperation(value="退出登录")
	@PostMapping(value="/logout")
	public CommonResponse logout(@RequestParam("argv")  String requestBody){
		CommonResponse response = new CommonResponse();
		try {
			CommonRequest commonRequest	 = JSONUtil.parseObject(requestBody, CommonRequest.class);
			if(StringUtils.isEmpty(commonRequest.getSessionId())){
				response.setSuccess(true);
				response.setCode("01");
				response.setMsg("参数有误：session_id为空");
				return response;
			}
			redisService.remove(AppConstants.REDIS_SESSION_PREFIX+commonRequest.getSessionId());
			response.setSuccess(true);
			response.setCode(AppConstants.STATUS_SUCCESS);
			response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_SUCCESS));
		} catch (Exception e) {
			LOGGER.error("退出登录异常：{}", e);
		}
		return response;
	}
	
}
