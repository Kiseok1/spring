package com.java.www.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DataController {
	
	@RequestMapping("aaa")
	public String aaa() {
		return "aaa";
	}
	
	@ResponseBody
	@RequestMapping("bbb")
	public String bbb() {
		String txt = "{'id':'aaa','pw':'1111','name':'홍길동'}";
		return txt;
	}
	
	@ResponseBody
	@RequestMapping("idCheck")
	public Map<String, Object> idCheck(String id) {
		Map<String, Object> map = new HashMap<>();
		
		if(id.equals("aaa")) {
			map.put("result", "fail");
		} else {
			map.put("result", "success");
		}
		
		return map;
	}
	
	@RequestMapping("mInsert")
	public String mInsert() {
		return "mInsert";
	}
}
