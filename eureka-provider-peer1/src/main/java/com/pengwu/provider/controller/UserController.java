package com.pengwu.provider.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pengwu.provider.entity.User;
import com.pengwu.provider.model.AppConstants;
import com.pengwu.provider.model.CommonResponse;

import io.swagger.annotations.ApiOperation;

/**  
 * @describe 用户控制器
 * @author wupeng
 * @createtime  2017年7月5日
 */
@RestController
@RequestMapping("/user")
public class UserController {

	static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@ApiOperation("用户列表")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public CommonResponse<List<User>> list(@ModelAttribute User user){
		CommonResponse<List<User>> response = new CommonResponse<List<User>>();
		List<User> lists = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User _user = new User();
			_user.setUserName("张三"+i);
			_user.setAge(18+i);
			_user.setGender("男");
			_user.setCompany("上海融道网金融信息服务有限公司");
			lists.add(_user);
		}
		response.setSuccess(true);
		response.setCode(AppConstants.STATUS_SUCCESS);
		response.setDesc(AppConstants.statusMap.get(AppConstants.STATUS_SUCCESS));
		response.setObj(lists);
		return response;
	}
	
	@ApiOperation("用户列表")
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public List<User> userlist(@ModelAttribute User user){
		List<User> lists = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User _user = new User();
			_user.setUserName("张三"+i);
			_user.setAge(18+i);
			_user.setGender("男");
			_user.setCompany("上海融道网金融信息服务有限公司");
			lists.add(_user);
		}
		return lists;
	}
	
	@ApiOperation("用户详情")
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public CommonResponse<User> info(@ModelAttribute User user){
		CommonResponse<User> response = new CommonResponse<User>();
		try {
			User _user = new User();
			_user.setUserName("张三");
			_user.setAge(18);
			_user.setGender("男");
			_user.setCompany("上海融道网金融信息服务有限公司");
			response.setSuccess(true);
			response.setCode(AppConstants.STATUS_SUCCESS);
			response.setDesc(AppConstants.statusMap.get(AppConstants.STATUS_SUCCESS));
			response.setObj(_user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
