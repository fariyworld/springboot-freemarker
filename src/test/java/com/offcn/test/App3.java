package com.offcn.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class App3 {
	
	@Resource
	private RedisTemplate<String,String> redisTemplate;
	
	
	@Test
	public void test(){
		
		redisTemplate.opsForValue().set("k2", "redis");
	}
	
	@Test
	public void test2(){
		
		System.out.println(redisTemplate.opsForValue().get("k2"));
	}
	
	
}
