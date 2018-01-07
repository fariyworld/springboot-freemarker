package com.offcn.test;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
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
	
	@Test
	public void test3(){
		
		//升序
		Set<TypedTuple<String>> scores = redisTemplate.opsForZSet().rangeWithScores("voteSet", 0, -1);
		
		for (TypedTuple<String> typedTuple : scores) {
			
			Double score = typedTuple.getScore();
			
			String value = typedTuple.getValue();
			
			System.out.println("分数："+score+" 对象："+value);
		}
	}
	
	@Test
	public void test4(){
		
		//降序
		Set<TypedTuple<String>> scores = redisTemplate.opsForZSet()
							.reverseRangeWithScores("voteSet", 0, -1);
		
		for (TypedTuple<String> typedTuple : scores) {
			
			Double score = typedTuple.getScore();
			
			String value = typedTuple.getValue();
			
			System.out.println("分数："+score+" 对象："+value);
		}
	}
	
	
}
