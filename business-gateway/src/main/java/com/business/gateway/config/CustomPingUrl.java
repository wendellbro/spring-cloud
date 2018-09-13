package com.business.gateway.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.business.gateway.config.PingUrlConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

import java.util.Map;

/**
 * 自定义ping url的实现
 */
public class CustomPingUrl implements IPing {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomPingUrl.class);
	
	static int count = 1;

	static Map<String, String> servers;

	@Autowired
	private PingUrlConfig pingUrlConfig;

	private String pingAppendString = "";
	
	private boolean isSecure = false;

	private String expectedContent = null;

	/**
	 *只发送一个Ping。那么，发送您需要的东西来确定服务器是否还活着。应该在“合理”的时间内返回。
	 */
	public CustomPingUrl() {
		
	}

	public CustomPingUrl(boolean isSecure, String pingAppendString) {
		this.isSecure = isSecure;
		this.pingAppendString = (pingAppendString != null) ? pingAppendString : "";
	}

	public void setPingAppendString(String pingAppendString) {
		this.pingAppendString = (pingAppendString != null) ? pingAppendString : "";
	}

	public String getPingAppendString() {
		return pingAppendString;
	}

	public boolean isSecure() {
		return isSecure;
	}

	/**
	 * 是否使用安全协议https应用于Ping
	 * @param isSecure
	 */
	public void setSecure(boolean isSecure) {
		this.isSecure = isSecure;
	}


	public String getExpectedContent() {
		return expectedContent;
	}

	/**
	 * 设置期望看到的返回内容；请注意，如果设置了该内容，该内容应该与实际内容完全匹配，否则会返回节点不可用的状态
	 * @param expectedContent
	 */
	public void setExpectedContent(String expectedContent) {
		this.expectedContent = expectedContent;
	}

	public boolean isAlive(Server server) {
		String urlStr   = "";
		if (isSecure){
			urlStr = "https://";
		}else{
			urlStr = "http://";
		}
		urlStr += server.getId();

		if(servers == null) {
			servers = pingUrlConfig.getServers();
		}

		pingAppendString = servers.get(server.getId());
		if(!pingAppendString.isEmpty()) {
			urlStr = urlStr + "/" + pingAppendString;
		}

		boolean isAlive = false;
		
		CloseableHttpClient httpClient = null;  
		HttpGet httpGet = null;  
		CloseableHttpResponse httpResponse = null;
		String content = null;  
		try {
			RequestConfig requestConfig = RequestConfig.custom()
				    .setSocketTimeout(5000)
				    .setConnectTimeout(3000)
				    .setConnectionRequestTimeout(5000)
				    .build();
			httpClient = HttpClientBuilder.create().build();
			httpGet = new HttpGet(urlStr);
			httpGet.setConfig(requestConfig);
			httpResponse = httpClient.execute(httpGet);
			if(httpResponse != null){  
				isAlive = (httpResponse.getStatusLine().getStatusCode() == 200);
				if (getExpectedContent()!=null){
					content = EntityUtils.toString(httpResponse.getEntity());
					LOGGER.info("service node response content = " + content);
					if (content == null){
						isAlive = false;
					}else{
						if (content.equals(getExpectedContent())){
							isAlive = true;
						}else{
							isAlive = false;
						}
					}
				}
			}  
		} catch (Exception e) {
			// LOGGER.error("ping节点[{}]异常：{}", urlStr, e.getMessage());
		}finally{
			try{
				if (httpResponse != null){
					httpResponse.close();
				}
				if (httpClient != null){
					httpClient.close();
				}
			} catch (Exception e){
				LOGGER.error("http close异常：{}",  e);
			}
			
			if(!isAlive){
				LOGGER.error("======================ping节点[{}]失败，当前次数count={}======================", urlStr, count++);
			}
		}

		return isAlive;
	}

}