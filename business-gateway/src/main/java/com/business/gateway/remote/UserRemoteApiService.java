package com.business.gateway.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.business.gateway.remote.model.UserLoginResponse;

/**
 * @describe UserService
 * @author wupeng
 * @createtime 2017年9月12日
 */

@FeignClient(name="users",path="/roadoor-user-biz")
public interface UserRemoteApiService {
	
	@RequestMapping(value="/user/ope/login",method=RequestMethod.POST)
	public UserLoginResponse userLogin(String requestyBody);
	
	@RequestMapping(value="/user/ope/register",method=RequestMethod.POST)
	public UserLoginResponse userRegister(String requestyBody);
	
}

/*@Service
public class UserRemoteApiService {

	@Autowired
	private RestTemplate restTemplate;

	public UserLoginResponse userlogin(String phone, String password) {
		String requestUrl = "http://users/roadoor-user-biz/user/login";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		params.add("phone", phone);
		params.add("password", password);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<UserLoginResponse> result = restTemplate.postForEntity(requestUrl, request, UserLoginResponse.class);
		return result.getBody();
	}

}*/
