package com.java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {

	@Autowired BService bService;
	@Autowired HttpSession session;
	
	@GetMapping({"/","main"})
	public String main() {
		return "main";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "main";
	}
	
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		MemberDto memDto = bService.login(mdto);
		if(memDto!=null) {
			session.setAttribute("session_id", memDto.getId());
			session.setAttribute("session_name", memDto.getName());
		}
		
		model.addAttribute("result",memDto);
		return "dologin";
	}
	
	@GetMapping("bview") //게시글 보기
	public String bview(@RequestParam(defaultValue = "1")int bno,Model model) {
		//게시글 1개 가져오기
		BoardDto bdto = bService.selectOne(bno);
		model.addAttribute("bdto",bdto);
		return "bview";
	}
	
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	@GetMapping("bInsert")
	public String bInsert() {
		return "bInsert";
	}
	
	@PostMapping("write")
	public String write(@RequestPart MultipartFile file,BoardDto bdto, Model model) throws Exception {
		if(!file.isEmpty()) {
			String orgName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName;
			bdto.setBfile(newName);
			
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			file.transferTo(f);
		}else {
			bdto.setBfile("");
		}
		
		bService.bInsert(bdto);
		model.addAttribute("result","success-bwrite");
		return "result";
	}
	
	@GetMapping("map")
	public String map() {
		return "map";
	}
	
	@PostMapping("boxoffice")
	@ResponseBody
	public String boxoffice(String date) throws Exception {
		System.out.println("searchData txt : "+date);
		String serviceKey = "9745f30872d17cb42e5e67117fa255fb";
		String result ="";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(System.currentTimeMillis());
		
		
		result=boxofficeSearch(date, serviceKey);

		return result;
	}
	
	@PostMapping("uploadImage") //summernote에서 ajax이미지 전송
	@ResponseBody
	public String uploadImage(@RequestPart MultipartFile file) throws Exception {
		String urlName = "";
		
		if(!file.isEmpty()) {
			String oriFileName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFileName = time+"_"+oriFileName; //파일이름변경
			String upload = "c:/upload/";
			File f = new File(upload+uploadFileName); //파일등록
			file.transferTo(f); // 파일서버로 전송
			urlName = "/upload/"+uploadFileName;
		} 
		return urlName;
	}
	
	@PostMapping("bwrite") //글쓰기 저장
	public String bwrite(BoardDto bdto, @RequestPart MultipartFile file, Model model) throws Exception {
		System.out.println("controller bwrite btitle : "+bdto.getBtitle());
		if(!file.isEmpty()) {
			String oriFileName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFileName = time+"_"+oriFileName; //파일이름변경
			String upload = "c:/upload/";
			bdto.setBfile(uploadFileName); //dto bfile 이름저장
			
			File f = new File(upload+uploadFileName); //파일등록
			file.transferTo(f); // 파일서버로 전송
		} else {
			bdto.setBfile(""); //dto bfile 이름저장
		}
		
		System.out.println("controller bwrite bfile : "+ bdto.getBfile());
		
		//service 연결 - 글쓰기저장
		bService.bwrite(bdto);
		
		model.addAttribute("result","success-bwrite");
		return "result";
	}
	
	
	@GetMapping("blist")
	public String blist(Model model) {
		//게시글 전체 가져오기
		List<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		return "blist";
	}
	
	//screenData 검색함
	public String boxofficeSearch(String date, String serviceKey) throws Exception {
		StringBuilder urlBuilder = new StringBuilder("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "="+serviceKey); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("targetDt","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*목록 건수*/
		URL url = new URL(urlBuilder.toString());
		//URL url = new URL("https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1?serviceKey=918RE13GA7OY7ZEmUzApgbOeAcQoZ%2FaHsXWcqPAKQ9YNNPj83KOstRMRIUrCFIAcm9qj2R6b7NFZjp%2FYsYzJLg%3D%3D&numOfRows=10&pageNo=2&MobileOS=ETC&MobileApp=AppTest&arrange=A&_type=json");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();  //String 을 계속 더하면 String변수를 계속 새롭게 만듬.
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line); //json데이터를 sb에 1줄씩 저장
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		
		return sb.toString();
	}//boxofficeSearch
}
