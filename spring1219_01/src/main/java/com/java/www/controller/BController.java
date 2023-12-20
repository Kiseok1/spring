package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	@GetMapping("bList") //게시글 전체 가져오기
	public String bList(Model model) { 
		//db에서 가져오기
		ArrayList<BoardDto> list = bService.selectAll();
		
		//model 저장
		model.addAttribute("list",list);
		return "board/bList";
	}//bList
	
	@GetMapping("bView") //게시글 1개 가져오기 , 이전글,다음글 가져오기
	public String bView(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BController bno : "+bno);
		//db에서 가져오기
		Map<String, Object> map = bService.selectOne(bno);
		
		//model저장
		model.addAttribute("map",map);
		
		return "board/bView";
	}//bView
	
	@GetMapping("bInsert") //글쓰기 화면
	public String bInsert() {
		return "board/bInsert";
	}//bInsert
	
	@PostMapping("bInsert") //글쓰기 저장
	public String bInsert(BoardDto bdto,@RequestPart MultipartFile files, 
			Model model) throws Exception {
		//MultipartFile files - input type="file" name="files" name을 가져옴
		//파일첨부의 파일이름
		if(!files.isEmpty()) { //파일첨부가 있으면
			String orgName = files.getOriginalFilename();
			System.out.println("BController 파일첨부 이름 : "+orgName);
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName; //중복방지를 위해 새로운 이름변경
			String upload = "c:/upload/";	   //파일업로드 위치
			File f = new File(upload+newName);
			files.transferTo(f); //파일을 저장위치에 저장시킴
			bdto.setBfile(newName); //파일이름을 BoardDto에 저장시킴
		} else { //파일첨부가 없으면
			bdto.setBfile("");
			System.out.println("파일첨부가 없습니다.");
		}
		
		//db로 전송
		bService.bInsert(bdto);
		
		return "board/doBInsert";
	}//bInsert
	
	@PostMapping("bDelete") //게시글 삭제
	public String bDelete(@RequestParam(defaultValue = "1")int bno) {
		System.out.println("BController bDelete bno : "+bno);
		bService.bDelete(bno);
		
		return "board/bDelete";
	}//bDelete
	
	
	@PostMapping("bUpdate") //게시글 수정 페이지 보기
	public String bUpdate(@RequestParam(defaultValue = "1")int bno,Model model) {
		System.out.println("BController bUpdate bno : "+bno);
		Map<String, Object> map = bService.selectOne(bno);
		model.addAttribute("map",map);
		return "board/bUpdate";
	}//bUpdate
	
	@PostMapping("doBUpdate") //게시글 수정 페이지 보기
	public String doBUpdate(BoardDto dto,@RequestPart MultipartFile files) {
		
		
		return "board/doBUpdate";
	}//bUpdate
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-------------------다중 업로드---------------------
	
	@GetMapping("bList2") //게시글 전체 가져오기
	public String bList2(Model model) { 
		//db에서 가져오기
		ArrayList<BoardDto> list = bService.selectAll();
		
		//model 저장
		model.addAttribute("list",list);
		return "board/bList2";
	}//bList2
	
	@GetMapping("bView2") //게시글 1개 가져오기 , 이전글,다음글 가져오기
	public String bView2(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BController bno : "+bno);
		//db에서 가져오기
		Map<String, Object> map = bService.selectOne(bno);
		
		//model저장
		model.addAttribute("map",map);
		
		return "board/bView2";
	}//bView2
	
	@GetMapping("bInsert2") // 다중업로드 화면보기
	public String bInsert2() {
		return "board/bInsert2";
	}
	
	@PostMapping("bInsert2") // 다중업로드 저장
	public String bInsert2(BoardDto bdto,List<MultipartFile> files, Model model) throws Exception{
		//MultipartFile files - input type="file" name="files" name을 가져옴
		//복수개 일때는 List<MultipartFile> files로 받음.
		String orgName = "";
		String newName = "";
		String mergeName = "";
		int i=0;
		for(MultipartFile file:files) {
			//파일첨부하기(c:/upload/)
			orgName = file.getOriginalFilename();
			System.out.println("BController 파일첨부 이름 : "+orgName);
			long time = System.currentTimeMillis();
			newName = time+"_"+orgName; //중복방지를 위해 새로운 이름변경
			String upload = "c:/upload/";	   //파일업로드 위치
			File f = new File(upload+newName);
			file.transferTo(f); //파일을 저장위치에 저장시킴

			//파일이름을 db에 저장하기
			if(i==0) mergeName += time+"_"+orgName; //중복방지를 위해 새로운 이름변경
			else mergeName += ","+time+"_"+orgName; 
			i++;
		}
		
		bdto.setBfile(mergeName); //파일이름을 BoardDto에 저장시킴
		System.out.println("BController 최종 파일첨부 이름 : "+mergeName);
		
		//db연결 - 내용저장
		bService.bInsert(bdto);
		
		return "board/bInsert2";
	}//bInsert2
	
}//class