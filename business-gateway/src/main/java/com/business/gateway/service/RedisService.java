package com.business.gateway.service;

import java.util.Collection;
import java.util.Set;

/**
 * @describe Redis操作
 * @author wupeng
 * @createtime 2017年9月12日
 */
public interface RedisService<T> {
	
	public T get(String key);
	
	public void put(String key, T value) throws Exception;
	
	public void put(String key, T value, int liveTime) throws Exception;
	
	public int getExpire(String key);
	
	public void setExpire(String key, int liveTime);
	
	public void remove(String key);

	public int size();

	public Set<String> keys(String prefix);

	public Collection<T> values(String prefix);
	
}
