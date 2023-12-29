package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.CommentDto;
import com.java.www.dto.NoticeDto;
import com.java.www.service.NService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
public class CustomerContoller {

	@Autowired
	NService nService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("notice")
	public String notice(@RequestParam(defaultValue = "1")int page, Model model) {
		Map<String, Object> map = nService.selectAll(page);
		model.addAttribute("map",map);
		return "customer/notice";
	}
	
	@RequestMapping("notice_view")
	public String notice_view(@RequestParam(defaultValue = "1") int bno, Model model ) {
		Map<String, Object> map = nService.selectOne(bno);
		model.addAttribute("map",map);
		return "customer/notice_view";
	}
	
	@GetMapping("notice_write")
	public String notice_write() {
		return "customer/notice_write";
	}
	
	@PostMapping("notice_write")
	public String notice_write(NoticeDto ndto, @RequestPart MultipartFile files) throws Exception {
		if(!files.isEmpty()) {
			String orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName;
			
			ndto.setBfile(newName);
			
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			files.transferTo(f);
		} else {
			ndto.setBfile("");
		}
		
		ndto.setId((String) session.getAttribute("session_id"));
		nService.notice_write(ndto);
		
		return "customer/doNotice_write";
	}
	
	@PostMapping("cInsert")
	@ResponseBody
	public CommentDto cInsert(CommentDto cdto) {
		cdto.setId((String) session.getAttribute("session_id"));
		CommentDto commentDto = nService.cInsert(cdto);
		return commentDto;
	}
	
	@PostMapping("cDelete")
	@ResponseBody
	public String cDelete(CommentDto cdto) {
		String result = nService.cDelete(cdto);
		return result;
	}
	
	
}
