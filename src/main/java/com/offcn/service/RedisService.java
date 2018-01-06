package com.offcn.service;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	@Resource
	private RedisTemplate<String,String> redisTemplate;
	 
	 public void set(String key, String value){
		 
		 redisTemplate.opsForValue().set(key, value);
	 }
	 
	 public String get(String key){
		 
		 return redisTemplate.opsForValue().get(key);
	 }
}
