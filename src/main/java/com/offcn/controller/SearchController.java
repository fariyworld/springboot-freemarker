package com.offcn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.offcn.service.RedisService;

@Controller
public class SearchController {
	
	@Autowired
	private RedisService redisService;

	@RequestMapping("/")
	public String home(ModelMap map){
		
		map.addAttribute("name", "hello freeMarker");
		
		map.addAttribute("sex", 1);
		
		return "home";
	}
	
	@RequestMapping("/testRedisSet")
	@ResponseBody
	public String testRedis1(String key,String value){
		
		redisService.set(key, value);
		
		return "success";
	}
	
	@RequestMapping("/testRedisGet")
	@ResponseBody
	public String testRedis2(String key){
		
		return redisService.get(key);
	}
	
	
	
	
	
}
