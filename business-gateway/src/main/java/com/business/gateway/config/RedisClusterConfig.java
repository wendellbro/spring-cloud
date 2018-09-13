package com.business.gateway.config;
/*package com.roadoor.gateway.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.roadoor.gateway.model.AppConstants;

import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@EnableCaching
public class RedisClusterConfig extends CachingConfigurerSupport{
	
    @Value("${spring.redis.pool.max-active}")		//最大连接数, 默认8个
    private int poolMaxActive;
    
    @Value("${spring.redis.pool.min-idle}")		//最小空闲连接数, 默认0
    private int poolMinIdle;
    
    @Value("${spring.redis.pool.max-idle}")		//最大空闲连接数, 默认8个
    private int poolMaxIdle;
    
    @Value("${spring.redis.pool.max-wait}")		//获取连接时的最大等待毫秒数，小于零：阻塞不确定的时间, ；默认-1（如果设置为阻塞时BlockWhenExhausted超时就抛异常）,
    private int poolMaxWait;
	
    @Value("${spring.redis.timeout}")		//连接超时时间
    private int timeout;
    
    
    *//**
     * Redis集群配置
     *//*
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private int clusterRedirects;
    
    *//**
     * Redis集群配置
     * @return RedisClusterConfiguration
     *//*
    @Bean
    public RedisClusterConfiguration getRedisClusterConfiguration(){
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("spring.redis.cluster.nodes", clusterNodes);
        source.put("spring.redis.cluster.max-redirects", clusterRedirects); 
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    }
	
	*//**
	 * Redis配置信息
	 * @return
	 *//*
    @Bean
    public JedisPoolConfig getJedisPoolConfig(){  
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxIdle(poolMaxIdle);
        config.setMaxWaitMillis(poolMaxWait);
        config.setMaxTotal(poolMaxActive);
        config.setMinIdle(poolMinIdle);
        return config;  
    }  
    
    *//**
     * Redis连接工厂
     * @return
     *//*
	@Bean
	public JedisConnectionFactory getConnectionFactory(){		
		JedisConnectionFactory factory = new JedisConnectionFactory(getRedisClusterConfiguration(), getJedisPoolConfig());
		factory.setTimeout(timeout);
		return factory;
	}
	
	*//**
	 * RedisTemplate缓存操作类;
	 * 
	 * @param factory
	 * @return
	 *//*
	@Bean(name="shiroRedisTemplate")
	public RedisTemplate<String, Object> shiroRedisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String,Object> template = new RedisTemplate<String,Object>();
		template.setConnectionFactory(getConnectionFactory());
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		return template;
	}
	
	@Bean(name="redisTemplate")
	public RedisTemplate<String, String> redisTemplate(){
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(getConnectionFactory());
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(genericJackson2JsonRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		//template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
		return template;
	}
	
	*//**
	 * 缓存管理器.
	 * @param redisTemplate
	 * @return
	 *//*
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Object> template) {
		CacheManager cacheManager = new RedisCacheManager(redisTemplate());
		return cacheManager;
	}
	
	*//**
	 * 默认redis key生成器
	 * @return
	 *//*
	@Bean
	public KeyGenerator defaultKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return AppConstants.REDIS_KEY_PREFIX + sb.toString();
			}
		};
	}
	
	*//**
	 * 模块列表redis key生成器
	 * @return
	 *//*
	@Bean
	public KeyGenerator moduleListKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				return AppConstants.REDIS_KEY_PREFIX + "ModuleListCacheKey";
			}
		};
	}
	
}
*/