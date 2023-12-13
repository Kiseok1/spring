package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notice")
public class NController {

	@RequestMapping("noticeList") //("notice/noticeList")가능
	public String noticeList() {
		return "notice/noticeList";
	}
	
	@RequestMapping("noticeInsert")
	public String noticeInsert() {
		return "notice/noticeInsert";
	}
	
	@RequestMapping("noticeView") //get방식 post방식 둘다 가능
	public String noticeView() {
		return "notice/noticeView";
	}
}
