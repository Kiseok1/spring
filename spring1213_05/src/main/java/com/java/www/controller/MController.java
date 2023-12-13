package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.www.dto.MemberDto;

@Controller
public class MController {

	@GetMapping("member/memberInsert")
	public String memberInsert(Model model) {
		//id를 전달
		String id = "admin";
		//request.setAttribute("id",id);
		//현재날짜와 시간을 Timestamp파일에 저장
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		MemberDto mdto 
		= new MemberDto("aaa","1111","홍길동","010-1111-1111","male","game,golf", mdate);
		
		//model.addAttribute("id",id);
		model.addAttribute("mdto",mdto);
		
		System.out.println("MemberDto id : "+mdto.getId());
		return "member/memberInsert";
	}
}
