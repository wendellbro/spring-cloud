package com.business.gateway.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @describe 统一异常过滤器
 * @author wupeng
 * @createtime 2017年9月12日
 */
public class ErrorFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public String filterType() {
		return FilterConstants.ERROR_TYPE;
	}
	
	@Override
	public int filterOrder() {
		return 10;
	}
	
	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		LOGGER.error("统一异常信息 : {}", throwable.getCause());
		ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return null;
	}

}
