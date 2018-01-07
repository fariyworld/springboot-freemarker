package com.offcn.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.offcn.bean.QueryVO;
import com.offcn.service.RedisService;

@Controller
public class SearchController {
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 
	 * @TODO: 模板类freemark 伪静态
	 * @param map
	 * @return String
	 */
	@RequestMapping("/")
	public String home(ModelMap map){
		
		map.addAttribute("name", "hello freeMarker");
		
		map.addAttribute("sex", 1);
		
		return "home";
	}
	
	@RequestMapping("/testRedisSet")
	@ResponseBody
	public String testRedis1(String key,String value){
		
		return redisService.set(key, value);
	}
	
	@RequestMapping("/testRedisGet")
	@ResponseBody
	public String testRedis2(String key){
		
		return redisService.get(key);
	}
	
	@RequestMapping("/initVote")
	@ResponseBody
	public QueryVO initVote(QueryVO vo){
		
		//存到redis缓存中
		boolean flag = redisService.set("voteSet", vo.getPeoArr(), vo.getVoteArr());
		
		QueryVO queryVO = new QueryVO();
		if( flag )
			queryVO.setResult("success");
		else 	
			queryVO.setResult("faild");
		
		return queryVO;
	}
	
	
	@RequestMapping("/updateVote")
	@ResponseBody
	public QueryVO updateVote(String value, double score){
		
		System.out.println("人："+value+" 票数："+score);
		
		//更新redis缓存中 负数为减
		Double res = redisService.set("voteSet", value, 1);
		
		System.out.println("更新后的分数："+res);
		
		QueryVO queryVO = new QueryVO();
		if( res != null && !res.equals("") )
			queryVO.setResult("success");
		else 	
			queryVO.setResult("faild");
		
		return queryVO;
	}
	
	
	@RequestMapping("/readCache")
	@ResponseBody
	public QueryVO readCache(){
		
		QueryVO queryVO = new QueryVO();
		
		Map<Integer, Map<String, Double>> map = redisService.get("voteSet", 0, -1);
		
		if( map.size() == 0 || map == null ){
			
			queryVO.setResult("faild");
		
		}else{
			queryVO.setResult("success");
			queryVO.setMap(map);
		}
		
		return queryVO;
	}
	
	
	
}
