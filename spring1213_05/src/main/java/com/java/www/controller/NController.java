package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.www.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

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
	
	@RequestMapping("doNoticeInsert")
	public String doNoticeInsert(HttpServletRequest request, Model model) {
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bfile = request.getParameter("bfile");
		Timestamp bdate = new Timestamp(System.currentTimeMillis());
		
		//bulider 사용한 생성자
		BoardDto bdto = BoardDto.builder()
				.btitle(btitle)
				.bcontent(bcontent)
				.bdate(bdate)
				.bfile(bfile).build();

		model.addAttribute("bdto",bdto);
		
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("bdate",bdate);
//		model.addAttribute("bfile",bfile);

		System.out.printf("controller : %s,%s,%s",btitle,bcontent,bfile);
		return "notice/noticeView";
	}
	
	@RequestMapping("noticeView") //get방식 post방식 둘다 가능
	public String noticeView() {
		return "notice/noticeView";
	}
}
