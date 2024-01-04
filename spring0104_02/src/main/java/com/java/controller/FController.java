package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.service.BService;

@Controller
public class FController {

	@Autowired BService bService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("blist")
	public String blist(Model model) {
		List<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		return "blist";
	}
	
	//내용부분 - 이미지 추가시 파일업로드
	@PostMapping("summernoteUpload")
	@ResponseBody
	public String summernoteUpload(@RequestPart MultipartFile file) throws Exception  {
		String urlLink = "";
		if(!file.isEmpty()) {
			String oriFName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String upFName = time+"_"+oriFName;
			
			//파일업로드 부분
			String fupload = "c:/upload/";
			File f = new File(fupload+upFName);
			file.transferTo(f);
			
			//파일위치
			urlLink = "/upload/"+upFName;
			System.out.println("summernoteUpload 파일 저장위치 : "+urlLink);
		} 
		
		return urlLink;
	}
	
	
	@PostMapping("bwrite")
	public String bwrite(BoardDto bdto,@RequestPart MultipartFile file) throws Exception {
		if(!file.isEmpty()) {
			String orgName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName;
			
			//bdto-bfile에 파일이름 담아줌
			bdto.setBfile(newName);
			
			// c:/upload에 저장
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			file.transferTo(f);
		} else {
			bdto.setBfile("");
		}
		
		bService.bwrite(bdto);
		
		return "dobwrite";
	}
	@GetMapping("dobwrite")
	public String dobwrite() {
		return "dobwrite";
	}
	
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}
	@GetMapping("bview")
	public String bview() {
		return "bview";
	}
}
