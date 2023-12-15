package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@RequestMapping("mInsert")
	public String mInsert() {
		return "member/memberInsert";
	}
	
	//데이터 받는 방법
	//방법 1. 객체
	//방법 2. @RequestParam, 변수
	//방법 3. 기존방법(HttpServletRequest)
	
	//방법1
	@RequestMapping("doMInsert")
	public String doMInsert(MemberDto mdto, Model model) {
		System.out.println("controller hobby : "+mdto.getHobby());
		model.addAttribute("mdto",mdto);
		return "member/memberView";
	}

	//방법2
//	@RequestMapping("doMInsert")
//	public String doMInsert(@RequestParam("id") String uid, String pw, String name, String phone, String gender, String[] hobby, Model model) {
//		String hobbys = "";
//		for (int i=0;i<hobby.length;i++) {
//			if (i==0) hobbys=hobby[i];
//			else hobbys+=","+hobby[i];
//		}
//		
//		MemberDto mdto = MemberDto.builder().id(uid).pw(pw).name(name).phone(phone).gender(gender).hobby(hobbys).build();
//		
//		model.addAttribute("mdto",mdto);
//		return "member/memberView";
//	}
	
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("doLogin")
	public String doLogin(MemberDto memberDto, BoardDto boardDto, Model model) {
		System.out.println("controller id : "+memberDto.getId());
		System.out.println("controller pw : "+memberDto.getPw());
		System.out.println("controller bno : "+boardDto.getBno());
		System.out.println("controller bid : "+boardDto.getId());
		
		String id = memberDto.getId();
		String pw = memberDto.getPw();
		int bno = boardDto.getBno();
		
		//기본생성자
		MemberDto mdto = new MemberDto();
		mdto.setId(id);
		System.out.println("mdto.getId : "+mdto.getId());
		
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		//전체생성자
		MemberDto mdto2 = new MemberDto(id, pw, "홍길동", "010-1111-1111", "male", "game,golf", mdate);
		
		//부분생성자
		MemberDto mdto3 = MemberDto.builder().id(id).pw(pw).name("유관순").gender("female").build();
		
		System.out.println("controller mdto3.getName : "+mdto3.getName());
		
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		model.addAttribute("bno",bno);
		model.addAttribute("mdto",memberDto);

		return "member/doLogin";
	}
	
	//방법2
//	@PostMapping("doLogin")
//	public String doLogin(String id,String pw,@RequestParam(defaultValue = "0")int bno) {
//		System.out.println("controller id : "+);
//		System.out.println("controller pw : "+);
//		System.out.println("controller bno : "+);
//		
//		String id = ;
//		String pw = ;
//		
//		return "doLogin";
//	}
	
	@RequestMapping("mUpdate")
	public String memberUpdate(MemberDto mdto, Model model) {

		model.addAttribute("mdto",mdto);
		return "member/memberUpdate";
	}
	
	@RequestMapping("mView")
	public String mView(MemberDto mdto, Model model) {
		
		model.addAttribute("mdto",mdto);
		return "member/memberView";
	}
}
