package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;

@Controller
public class FController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	//-------게시글 관련--------
	
	@RequestMapping("bInsert")
	public String bInsert() {
		
		return "bInsert";
	}
	
	@RequestMapping("bView")
	public String bView(BoardDto bdto, Model model) {
		model.addAttribute("bdto",bdto);
		return "bView";
	}
	
	@RequestMapping("bUpdate")
	public String bUpdate(BoardDto bdto, Model model) {
		model.addAttribute("bdto",bdto);
		return "bUpdate";
	}
	
	//---------------------------
	
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(String id, String pw, Model model) {
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		return "doLogin";
	}
}
