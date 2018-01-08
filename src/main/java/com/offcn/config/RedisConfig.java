package com.offcn.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @TODO: Redis Config 类
 * @Administrator: 黄土高坡的宝宝
 * @Date: 2018年1月6日下午9:51:59
 * @Version: V1.0.0
 */
@Configuration
@EnableCaching // 启用缓存，这个注解很重要；
public class RedisConfig extends CachingConfigurerSupport {
	/**
	 * 缓存管理器.
	 * 
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		
		CacheManager cacheManager = new RedisCacheManager(redisTemplate);
		
		return cacheManager;
	}

	/**
	 * redis模板操作类,类似于jdbcTemplate的一个类;
	 * 
	 * 解决redis自动生成key，key乱码，为自定义key
	 *
	 * @param factory
	 *        通过Spring进行注入，参数在application.properties进行配置；
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(factory);
		
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(redisSerializer);  
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);  
		ObjectMapper om = new ObjectMapper();  
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
		jackson2JsonRedisSerializer.setObjectMapper(om);  
		  
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);  
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);  
		redisTemplate.afterPropertiesSet(); 
		
		return redisTemplate;
	}
	
}
