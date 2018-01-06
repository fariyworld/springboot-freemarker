package com.offcn.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class App2 {
	
	@Test
	public void testRedis(){
		
		Jedis jedis = new Jedis("10.0.0.229", 6379);
		
		jedis.auth("root");
		
		String string = jedis.get("k1");
		
		System.out.println(string);
		
		jedis.close();
	}
	
	
	@Test
	public void testJedisPool(){
		
		JedisPool jedisPool = new JedisPool(new GenericObjectPoolConfig(), 
				"10.0.0.229", 6379, 0, "root");
		
		Jedis jedis = jedisPool.getResource();
		
		String string = jedis.get("k1");
		
		System.out.println(string);
		
		jedis.close();
		
		jedisPool.close();
	}
}
