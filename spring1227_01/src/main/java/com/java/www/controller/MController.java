package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.Member2Dto;
import com.java.www.dto.MemberDto;
import com.java.www.service.EmailService;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	
	@Autowired
	MService mService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("id")
	public String id() {
		return "member/id";
	}
	@GetMapping("id2")
	public String id2(String id,Model model) {
		model.addAttribute("id",id);
		return "member/id2";
	}
	
	@GetMapping("idsearch")
	public String idsearch() {
		return "member/idsearch";
	}
	
	@PostMapping("pwsearch")
	@ResponseBody
	public String pwsearch(String id, String email) {
		System.out.println("MController id : "+id);
		System.out.println("MController email : "+email);
		//service 연결 - 비번 찾기 - 아이디,이메일 검색
		String result = mService.pwsearch(id,email);
		
		
		return "success";
	}
	
	//jsp방식
	@PostMapping("idSearch")
	public String idSearch(String name,String email,Model model) {
		Member2Dto mdto = mService.idSearch(name,email);
		model.addAttribute("mdto",mdto);
		return "member/id";
	}
	
	//ajax방식
	@PostMapping("idSearchAjax")
	@ResponseBody
	public String idSearchAjax(String name,String email) {
		Member2Dto mdto = mService.idSearch(name,email);
		String result ="";
		session.removeAttribute("findId");
		if(mdto!=null) {
			result = mdto.getId();
			session.setAttribute("findId", result);
		} 
		return result;
	}
	
	//수업방식
	@PostMapping("idSearchAjax2") //ajax 아이디찾기 - name,email
	@ResponseBody
	public String idSearchAjax2(String name,String email) {
		System.out.println("MController idSearchAjax2 name : "+name);
		System.out.println("MController idSearchAjax2 email : "+email);
		Member2Dto mdto = mService.idSearch(name, email);
		String result ="";
		String tempId=""; //임시아이디
		//찾은 아이디 뒤 두자리에 **로 변경
		if(mdto!=null) {
			tempId = mdto.getId().substring(0,mdto.getId().length()-2);
			tempId += "**";
			System.out.println("찾은 아이디 : "+tempId);
			result = tempId;
		} else {
			result = "fail";
		}
		
		return result;
	}
	
	@GetMapping("step01")
	public String step01() {
		return "member/step01";
	}
	@GetMapping("step02")
	public String step02() {
		return "member/step02";
	}
	@GetMapping("step03")
	public String step03() {
		return "member/step03";
	}
	
	@PostMapping("idCheck")
	@ResponseBody
	public String idCheck(String id) {
		String result = "";
		Member2Dto mdto = mService.idCheck(id);
		if(mdto==null) {
			result="사용가능";
		}
		return result;
	}
	
	
	
	@PostMapping("step04")
	public String step04(Member2Dto mdto) {
		System.out.println("MController step04 phone : "+mdto.getPhone());
		return "member/step04";
	}
	
	@PostMapping("email")
	@ResponseBody
	public String email(String email) {
		System.out.println("MContorller email : "+email);
		
		//service 연결 - 이메일주소 보냄.
		String result = emailService.mailSend(email);
		
		return result;
	}
	
	@PostMapping("pwChk") //인증코드 확인
	@ResponseBody
	public String pwChk(String pwcode) {
		System.out.println("MContorller pwcode : "+pwcode);
		String pw = (String) session.getAttribute("email_pwcode");
		String result = "fail";
		if(pw.equals(pwcode)) {
			result="success";
		}
		
		return result;
	}
	
	//----login 부분----
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}

	//--------- 로그인 확인부분 ----------
	//--------- ajax 형태 ----------
	@PostMapping("ajaxLogin")
	@ResponseBody
	public String ajaxLogin(MemberDto mdto) {
		System.out.println("MController login id : "+mdto.getId());
		System.out.println("MController login pw : "+mdto.getPw());
		
		//service 연결 - result = 1(로그인), 0(아이디 또는 패스워드가 일치X)
		int result = mService.login(mdto);

		return result+"";
	}
	
	//--------- jsp 형태 ----------
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		System.out.println("MController login id : "+mdto.getId());
		System.out.println("MController login pw : "+mdto.getPw());
		
		//service 연결 - result = 1(로그인), 0(아이디 또는 패스워드가 일치X)
		int result = mService.login(mdto);
		//model 전송
		model.addAttribute("result",result);
		return "member/doLogin";
	}
	
	
	
}
