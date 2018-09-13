package com.business.gateway.filter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;

import com.business.gateway.config.PermissionConfig;
import com.business.gateway.model.AppConstants;
import com.business.gateway.model.CommonRequest;
import com.business.gateway.model.CommonResponse;
import com.business.gateway.remote.model.User;
import com.business.gateway.service.RedisService;
import com.business.gateway.utils.JSONUtil;
import com.business.gateway.utils.UUIDUtil;
import com.google.common.collect.Maps;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @describe session过滤器
 * @author wupeng
 * @createtime 2017年9月12日
 */
public class SessionFilter extends ZuulFilter  {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);
    
    @Autowired
	private RedisService<String> redisService;
    @Autowired
    private PermissionConfig permissionConfig;
    
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
    
    @Override
    public int filterOrder() {
        return 0;
    }
    
    @Override
    public boolean shouldFilter() {
        return true;
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
					// json解析异常（参数格式不匹配）
					ctx.setSendZuulResponse(false);
					ctx.setResponseStatusCode(HttpServletResponse.SC_BAD_REQUEST);
					CommonResponse response = new CommonResponse();
					response.setCode(AppConstants.STATUS_PARAM_ERROR);
				    response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_PARAM_ERROR));
				    ctx.setResponseBody(JSONUtil.toJSONString(response));  
				    ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);  
				    LOGGER.error("请求参数解析异常：{}", e);
				    return null;
				}
			}
			//获取session
			String userId = "";
			if(StringUtils.isNotEmpty(commonRequest.getSessionId())){
				String userInfo = redisService.get(AppConstants.REDIS_SESSION_PREFIX+commonRequest.getSessionId());
				if(StringUtils.isNotEmpty(userInfo)){
					User user = JSONUtil.parseObject(userInfo, User.class);
					userId = user.getUId()+"";
				}
			}
			//需要登录
			if(permissionConfig.isSessionRequire(request.getServletPath())){
				if(StringUtils.isEmpty(userId)){
					//session失效了
					ctx.setSendZuulResponse(false);
					ctx.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
					CommonResponse response = new CommonResponse();
			        response.setCode(AppConstants.STATUS_SESSION_TIMEOUT);
			        response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_SESSION_TIMEOUT));
			        ctx.setResponseBody(JSONUtil.toJSONString(response));  
			        ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);  
					return ctx;
				}else{
					redisService.setExpire(AppConstants.REDIS_SESSION_PREFIX+commonRequest.getSessionId(), AppConstants.REDIS_SESSION_EXPIRE);
				}
			}
			//需要token校验
			if(permissionConfig.isTokenRequire(request.getServletPath())){
				if(StringUtils.isEmpty(commonRequest.getToken())){
					CommonResponse response = new CommonResponse();
					response.setCode(AppConstants.STATUS_PARAM_ERROR);
					response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_PARAM_ERROR)+":token为空");
					ctx.setResponseBody(JSONUtil.toJSONString(response));  
					ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);  
					return null;
				}
				int expireTime = redisService.getExpire(AppConstants.REDIS_TOKEN_PREFIX+commonRequest.getToken());
				if(expireTime != -2){
					//重复提交
					ctx.setSendZuulResponse(false);
					ctx.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
					CommonResponse response = new CommonResponse();
					 response.setCode(AppConstants.STATUS_TOKEN_DUPLICATE);
			         response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_TOKEN_DUPLICATE));
			        ctx.setResponseBody(JSONUtil.toJSONString(response));  
			        ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);  
				}
				//写入token至redis
				redisService.put(AppConstants.REDIS_TOKEN_PREFIX+commonRequest.getToken(), UUIDUtil.uuid(), AppConstants.REDIS_TOKEN_EXPIRE);
			}
			
			//添加msgId和userId参数
			Map<String, List<String>> appendParams = ctx.getRequestQueryParams();
			if(appendParams == null){				
				appendParams = Maps.newLinkedHashMap();
			}
			if(StringUtils.isEmpty(commonRequest.getMsgId())){
				appendParams.put(AppConstants.MSG_ID, Arrays.asList(UUIDUtil.uuid()));				
			}
			if(StringUtils.isNotEmpty(userId)){
				appendParams.put(AppConstants.USER_ID, Arrays.asList(userId));				
			}
			ctx.setRequestQueryParams(appendParams);
			
			ctx.set(FilterType.SESSION_FILTER.name(), FilterType.SESSION_FILTER.value());  
			ctx.set(AppConstants.MONITOR_START_TIME,System.currentTimeMillis());	//访问服务的开始时间，后边统计
			
		} catch (Exception e) {
			LOGGER.error("session或token处理异常：{}", e);
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
	        CommonResponse response = new CommonResponse();
			response.setCode(AppConstants.STATUS_ERROR);
			response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_ERROR));
			response.setException(e.getMessage());
			ctx.setResponseBody(JSONUtil.toJSONString(response));  
			ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);  
		}
        return true;
    }
    
}
