package com.business.gateway.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;

/**
 * @describe 读取服务实例及context-path
 * @author wupeng
 * @createtime 2017年9月12日
 */
@Configuration
@ConfigurationProperties(prefix="ping")
public class PingUrlConfig {

	private Map<String, String[]> contextPath;
	
	public Map<String, String[]> getContextPath() {
		return contextPath;
	}

	public void setContextPath(Map<String, String[]> contextPath) {
		this.contextPath = contextPath;
	}

	public Map<String, String> getServers() {
		Map<String, String> servers = Maps.newLinkedHashMap();
		if(contextPath != null){
			for (String key : contextPath.keySet()) {
				String[] list = contextPath.get(key);
				for (String server : list) {
					servers.put(server, key);
				}
			}
		}
		return servers;
	}

}
