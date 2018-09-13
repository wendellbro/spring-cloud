package com.business.gateway.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.business.gateway.service.RedisService;
import com.google.common.collect.Sets;

@Service
public class RedisServiceImpl<T> implements RedisService<T> {

	@Autowired
	RedisTemplate<String, T> redisTemplate ;
	
	@Override
	public T get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	@Override
	public void put(String key, T value) throws Exception {
		this.redisTemplate.opsForValue().set(key, value);
	}
	
	@Override
	public void put(String key, T value, int liveTime) throws Exception {
		this.redisTemplate.opsForValue().set(key, value);
		if(liveTime > 0)		//-1为永不失效
			this.setExpire(key, liveTime);
	}

	@Override
	public int getExpire(String key) {
		return redisTemplate.getExpire(key).intValue();
	}

	@Override
	public void setExpire(String key, int liveTime) {
		redisTemplate.expire(key, liveTime, TimeUnit.SECONDS);
	}

	@Override
	public void remove(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public int size() {
		Long len = redisTemplate.getConnectionFactory().getConnection().dbSize();
		return len.intValue();
	}

	@Override
	public Set<String> keys(String prefix) {
		if(prefix == null)
			prefix = "";
		String strKey = prefix+"*";
		Set<String> set = redisTemplate.keys(strKey);
		Set<String> result = Sets.newHashSet();
		
		if(CollectionUtils.isEmpty(set)) {
			return Collections.emptySet();
		}
		
		for(String key: set) {
			result.add((String)key);
		}
		
		return result;
	}

	@Override
	public Collection<T> values(String prefix) {
		if(prefix == null)
			prefix = "";
		Set<String> keys = keys(prefix);
		List<T> values = new ArrayList<T>(keys.size());
		for(String k: keys) {
			values.add(redisTemplate.opsForValue().get(k));
		}
		return values;
	}

}
