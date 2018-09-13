package com.business.gateway.filter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.business.gateway.model.AppConstants;
import com.business.gateway.model.CommonRequest;
import com.business.gateway.model.CommonResponse;
import com.business.gateway.utils.DateUtil;
import com.business.gateway.utils.JSONUtil;
import com.business.gateway.utils.UUIDUtil;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class LoggerFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFilter.class);

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}
	
	@Override
	public int filterOrder() {
		return 10;
	}
	
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return ctx.getThrowable() == null
				&& ctx.get(FilterType.SESSION_FILTER.name()) != null;
	}
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		try {
			String request_data = request.getParameter(AppConstants.REQUEST_KEY);
			CommonRequest commonRequest = new CommonRequest();
			if(StringUtils.isNotEmpty(request_data)){
				try {
					commonRequest = JSONUtil.parseObject(request_data, CommonRequest.class);
				} catch (Exception e) {
				    
				}
			}
			String method = request.getMethod();		//请求的类型，post get ..
			String params = JSONUtil.toJSONString(request.getParameterMap());		//请求的参数
			String errorMsg = "";	//异常信息
			Throwable throwable = ctx.getThrowable();		//请求的异常，如果有的话
			if(throwable != null)
				errorMsg = throwable.getMessage();
			String requestUrl = request.getRequestURI(); 		//请求的uri
			int statusCode = ctx.getResponseStatusCode();		//请求的状态
			String startTime = null;
			long duration = 0;
			if(ctx.get(AppConstants.MONITOR_START_TIME) != null){
				long _startTime = Long.parseLong(ctx.get(AppConstants.MONITOR_START_TIME).toString());		//请求的开始时间
				duration=System.currentTimeMillis() - _startTime;		//请求耗时
				startTime = DateUtil.format(_startTime);
			}
			Map<String,Object> logger = Maps.newLinkedHashMap();
			logger.put("method", method);
			logger.put("requestUrl", requestUrl);
			logger.put("params", params);
			logger.put("duration", duration);
			logger.put("reqTime", startTime);
			logger.put("statusCode", statusCode);
			logger.put("exception", errorMsg);
			
			logger.put("source", commonRequest.getSource());		
			logger.put("version", commonRequest.getVersion());		//版本
			logger.put("client_ip", commonRequest.getClientIp());	//客户端IP地址
			logger.put("deviceId", commonRequest.getDeviceId());		//
			logger.put("msg_id", commonRequest.getMsgId());
			logger.put("session_id", commonRequest.getSessionId());
			logger.put("token", commonRequest.getToken());
			
			LOGGER.info(JSONUtil.toJSONString(logger));

			//修改返回数据
			if(!(requestUrl.contains("/pinan/return") || requestUrl.contains("/pinan/notify"))){
				String responseData = ctx.getResponseBody();
				if(responseData == null){
					final InputStream responseDataStream = ctx.getResponseDataStream();
					if(responseDataStream != null){
						responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
					}
				}
				JSONObject response = new JSONObject();
				try {
					response = JSONObject.parseObject(responseData);
					response.put(AppConstants.TOKEN, UUIDUtil.uuid());
				} catch (Exception e) {
					response = new JSONObject();
					response.put("data", responseData);
					response.put(AppConstants.TOKEN, UUIDUtil.uuid());
				}
				ctx.setResponseBody(JSONUtil.toJSONString(response));
			}
			
		} catch (Exception e) {
			CommonResponse response = new CommonResponse();
			response.setCode(AppConstants.STATUS_ERROR);
			response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_ERROR));
			response.setException(e.getMessage());
			ctx.setResponseBody(JSONUtil.toJSONString(response));  
			ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);  
			LOGGER.error("请求返回内容处理异常：{}",e);
		}

		return null;
	}

}
