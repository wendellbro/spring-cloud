package com.business.gateway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.gateway.model.AppConstants;
import com.business.gateway.model.CommonResponse;

import io.swagger.annotations.ApiOperation;

/**  
 * @describe 首页控制器
 * @author wupeng
 * @createtime  2017年7月5日
 */
@RestController
@RequestMapping("roadoor-gateway/")
public class IndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@ApiOperation("首页")
	@GetMapping(value="/")
	public CommonResponse index(){
		CommonResponse response = new CommonResponse();
		try {
			response.setSuccess(true);
			response.setCode(AppConstants.STATUS_SUCCESS);
			response.setMsg(AppConstants.statusMap.get(AppConstants.STATUS_SUCCESS));
		} catch (Exception e) {
			LOGGER.error("系统异常：{}", e);
			response.setException(e.getMessage());
		}
		return response;
	}
	
}
