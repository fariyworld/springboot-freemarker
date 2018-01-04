package com.offcn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

	@RequestMapping("/")
	public String home(ModelMap map){
		
		map.addAttribute("name", "hello freeMarker");
		
		return "home";
	}
}
