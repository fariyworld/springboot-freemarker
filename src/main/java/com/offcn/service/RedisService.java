package com.offcn.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import com.offcn.util.Assert;

@Service
public class RedisService {

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 
	 * @TODO: 设置 String数据类型的缓存
	 * @param key
	 * @param value
	 * @return String
	 */
	public String set(String key, String value) {

		if (Assert.isVaild(key)) {

			if (Assert.isVaild(value)) {

				redisTemplate.opsForValue().set(key, value);

				return "success";
			} else {

				return "value不能为null或者空";
			}

		} else {

			return "key不能为null或者空";
		}
	}

	/**
	 * 
	 * @TODO: 获取String类型数据缓存
	 * @param key
	 * @return String
	 */
	public String get(String key) {

		if (Assert.isVaild(key)) {

			String result = redisTemplate.opsForValue().get(key);

			if (Assert.isVaild(result))
				return result;
			else
				return "没有查询到key对应的缓存值";
		}
		return "key为null或者为空";
	}

	/**
	 * 
	 * @TODO: 存入到sortedSet类型的缓存
	 * @param key
	 * @param values
	 * @param scores
	 * @return boolean
	 */
	public boolean set(String key, String[] values, double[] scores) {

		boolean flag = false;

		for (int i = 0; i < scores.length; i++) {

			flag = redisTemplate.opsForZSet().add(key, values[i], scores[i]);
		}

		return flag;
	}

	/**
	 * 
	 * @TODO: 更新sortedSort某个value的分数
	 * @param key
	 * @param value
	 * @param score
	 * @return Double
	 */
	public Double set(String key, String value, double score) {

		return redisTemplate.opsForZSet().incrementScore(key, value, score);
	}

	/**
	 * 
	 * @TODO: 获取sortedSort的分数集合  降序
	 * @param key
	 * @param start
	 * @param end
	 * @return Set<String>
	 */
	public Map<Integer,Map<String,Double>> get(String key, long start, long end) {

		Set<TypedTuple<String>> scores = redisTemplate.opsForZSet().reverseRangeWithScores("voteSet", 0, -1);
		
		if( scores == null || scores.size() ==0 ){
			
			return null;
			
		}else{
			
			Map<Integer,Map<String,Double>> sortMap = new HashMap<Integer, Map<String,Double>>();
			
			
			int i = 0;
			
			for (TypedTuple<String> typedTuple : scores) {
				
				Map<String,Double> map = new HashMap<String,Double>();
				
				Double score = typedTuple.getScore();
				
				String value = typedTuple.getValue();
				
				//System.out.println("分数：" + score + " 对象：" + value);
				
				map.put(value, score);
				
				sortMap.put(i, map);
				
				i++;
			}
			
			return sortMap;
		}
	}
}
