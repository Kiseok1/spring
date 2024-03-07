package com.java.www.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;
import com.java.www.service.BService;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {

	@Autowired HttpSession session;
	@Autowired MService mService;
	@Autowired BService bService;
	
	@GetMapping("/")
	public String index(Model model, @RequestParam(defaultValue = "all") String gender, @RequestParam(defaultValue = "1") int page,
			@RequestParam (defaultValue = "") String category, @RequestParam(defaultValue = "") String searchWord, @RequestParam(defaultValue = "5") int ppr ) {
		String result = "";
		if(session.getAttribute("session_id")==null) {
			result = "login";
		} else {
			result = "index";
			System.out.println("con : "+gender);
			Map<String, Object> map = bService.selectAll(gender,page,ppr);
			model.addAttribute("map",map);
		}
		return result;
	}// index
	
	@GetMapping("login")
	public String login() {
		return "login";
	}//login
	
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		System.out.println(mdto.getId());
		System.out.println(mdto.getPw());
		MemberDto mdto2 = mService.selectOne(mdto);
		String result = "";
		if(mdto2!=null) {
			session.setAttribute("session_id", mdto2.getId());
			session.setAttribute("session_name", mdto2.getName());
			model.addAttribute("result","login-success");
			System.out.println("1");
		} else {
			model.addAttribute("result","login-error");
			System.out.println("2");
		}
		return "result";
	}//login
	
	@GetMapping("logout")
	public String logout(Model model) {
		session.invalidate();	
		model.addAttribute("result","logout-success");
		return "result";
	}//login
	
	@GetMapping("memInfo")
	public String memInfo() {
		return "memInfo";
	}//memInfo
	
	@GetMapping("mInsert")
	public String mInsert() {
		return "mInsert";
	}

}// FController(컨트롤러)
