package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
@RequestMapping("board")
public class BController {

	@Autowired
	BService bService;
	
	@RequestMapping("bList")
	public String bList(@RequestParam(defaultValue = "1")int page,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String searchWord, Model model) {
		Map<String, Object> map = bService.selectAll(page,category,searchWord);
		model.addAttribute("map",map);
		return "board/bList";
	}
	
	@RequestMapping("bView")
	public String bView(@RequestParam(defaultValue = "1")int bno,Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		model.addAttribute("map",map);
		return "board/bView";
	}
	
	@GetMapping("bInsert")
	public String bInsert() {
		return "board/bInsert";
	}
	
	@PostMapping("bInsert")
	public String doBInsert(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		if(!files.isEmpty()) {
			String orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"-"+orgName;
			
			bdto.setBfile(newName);
			
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			
			files.transferTo(f);
		} else {
			bdto.setBfile("");
		}
		
		bService.bInsert(bdto);
		
		return "board/doBInsert";
	}
	
	@PostMapping("bDelete")
	public String bDelete(@RequestParam(defaultValue = "1")int bno, Model model) {
		System.out.println("BController bDelete bno : "+bno);
		bService.bDelete(bno);
		return "board/bDelete";
	}
	
	@PostMapping("bUpdate")
	public String bUpdate(@RequestParam(defaultValue = "1")int bno,Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		model.addAttribute("map",map);
		return "board/bUpdate";
	}
	
	@PostMapping("doBUpdate")
	public String doBUpdate(BoardDto bdto,@RequestPart MultipartFile files) throws Exception {
		if(!files.isEmpty()) {
			String orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"-"+orgName;
			
			bdto.setBfile(newName);
			
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			
			files.transferTo(f);
		} 
		
		bService.bUpdate(bdto);
		return "board/doBUpdate";
	}
	
	@PostMapping("bReply")
	public String bReply(@RequestParam(defaultValue = "1")int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		model.addAttribute("map",map);
		return "board/bReply";
	}
	
	@PostMapping("doBReply")
	public String doBReply(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		if(!files.isEmpty()) {
			String orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"-"+orgName;
			
			bdto.setBfile(newName);
			
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			
			files.transferTo(f);
		} 
		bService.doBReply(bdto);
		
		return "board/doBReply";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//-----------------다중----------------------
	
	
	@GetMapping("bInsert2")
	public String bInsert2() {
		return "board/bInsert2";
	}
	
	@PostMapping("bInsert2")
	public String bInsert2(BoardDto bdto, List<MultipartFile> files, Model model) throws Exception {
		String orgName ="";
		String newName ="";
		String mergeName ="";
		int i=0;
		for(MultipartFile file:files) {
			orgName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			newName = time+"-"+orgName;
			String upload = "c:/upload/";
			File f = new File(upload+newName);

			file.transferTo(f);
			
			if(i==0) mergeName += newName;
			else mergeName += ","+newName;
		}
		bdto.setBfile(mergeName);
		
		return "board/doBInsert";
	}
	
	
	
}
