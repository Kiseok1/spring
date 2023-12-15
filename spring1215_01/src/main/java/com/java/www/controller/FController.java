package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;



@Controller
public class FController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	//--------board 관련------------
	
	@RequestMapping("bInsert")
	public String bInser() {
		return "bInsert";
	}
	
	@RequestMapping("bUpdate")
	public String bUpdate(BoardDto bdto, Model model) {
		model.addAttribute("bdto",bdto);
		return "bUpdate";
	}
	
	@RequestMapping("bView")
	public String bView(BoardDto bdto,MemberDto mdto, Model model) {
		System.out.println("controller bdto id : "+bdto.getId());
		System.out.println("controller mdto id : "+mdto.getId());
		
		model.addAttribute("bdto",bdto);
		return "bView";
	}
	
	// ---------------------------
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//방법1
	@RequestMapping("mLogin")
	public String doLogin(String id, String pw, Model model) {
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		return "mLogin";
	}
	
	//방법2
//	@RequestMapping("mLogin")
//	public String doLogin(MemberDto mdto, Model model) {
//		model.addAttribute("mdto",mdto);
//		return "mLogin";
//	}
	
	
	
}
