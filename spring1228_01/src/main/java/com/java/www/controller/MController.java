package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {

	@Autowired
	MService mService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		MemberDto memDto = mService.login(mdto);
		
		if(memDto!=null) {
			session.setAttribute("session_id", memDto.getId());
			session.setAttribute("session_name", memDto.getName());
		}
		
		return "member/doLogin";
	}
	
	@PostMapping("ajaxLogin")
	@ResponseBody
	public String ajaxLogin(MemberDto mdto, Model model) {
		MemberDto memDto = mService.login(mdto);
		String result = "0";
		if(memDto!=null) {
			session.setAttribute("session_id", memDto.getId());
			session.setAttribute("session_name", memDto.getName());
			result = "1";
		}
		
		return result;
	}
	
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "layout/main";
	}
	
	@GetMapping("step03")
	public String step03() {
		return "member/step03";
	}
	
	@PostMapping("step03")
	public String step03(MemberDto mdto, String f_phone, String m_phone, String l_phone,  Model model) {
		String phone = f_phone+"-"+m_phone+"-"+l_phone;
		mdto.setPhone(phone);
		
		mService.mInsert(mdto);
		return "member/step04";
	}
	
	
}
