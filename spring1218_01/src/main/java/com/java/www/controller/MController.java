package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {

	@Autowired //IOC컨테이너에 있는 객체 가져오기 
	MService mService;
	
	//HttpSession session = request.getSession();
	@Autowired
	HttpSession session;
	
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(MemberDto mdto, Model model, HttpServletRequest request) {
		int result = 0;
		System.out.println("Mcontroller id : "+mdto.getId());
		System.out.println("Mcontroller pw : "+mdto.getPw());
		// db연결 - 리턴값:dto dto있는지 확인
		MemberDto memberDto = mService.loginSelect(mdto); //id,pw
		
		if(memberDto!=null) {
			System.out.println("Mcontroller id 있음 : "+memberDto.getId());
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = 1;
		} else {
			System.out.println("Mcontroller memberDto : null");
		}
		
				
		// result 1: 성공, 0: 로그인 실패
		model.addAttribute("result",result);
		return "member/doLogin";
	}
	
	
}
