package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;
	
	//게시글 검색
	@Override
	public Map<String, Object> selectSearch(int page, String category, String searchWord) {
		//하단넘버링 관련
		//page,rowPerPage(1페이지에 표시되는 게시글 개수),countAll,startPage,endPage,maxPage
		if(page<=0) page=1;
		int countPerPage = 10; //페이지당 게시글수
		int bottomPerNum = 10; //하단넘버링 개수
		int countAll = boardMapper.selectSearchCount(category,searchWord); //게시글 검색 총개수
		System.out.println("BServiceImpl CountAll : "+countAll);
		int maxPage = (int)Math.ceil((double)countAll/countPerPage);
		int startPage = ((page-1)/bottomPerNum)*bottomPerNum+1;
		int endPage = startPage+bottomPerNum-1;
		
		int startRow = (page-1)*countPerPage+1;
		int endRow = startRow+countPerPage-1;
		
		//endPage가 maxPage보다 크면 endPage를 maxPage에 맞춰줌
		if(endPage>maxPage) endPage=maxPage;
		
		ArrayList<BoardDto> list = boardMapper.selectSearch(startRow,endRow,category,searchWord);
		//데이터전송 - list,page,maxPage,startPage,endPage
		Map<String, Object> map = new HashMap<>(); 
		map.put("list", list);
		map.put("countAll", countAll);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);

		return map;
	}
	
	//게시글 전체 가져오기
	@Override
	public Map<String, Object> selectAll(int page, String category, String searchWord) {
		//하단넘버링 관련
		//page,rowPerPage(1페이지에 표시되는 게시글 개수),countAll,startPage,endPage,maxPage
		if(page<=0) page=1;
		int countPerPage = 10; //페이지당 게시글수
		int bottomPerNum = 10; //하단넘버링 개수
		int countAll = boardMapper.selectCountAll(category,searchWord); //게시글 총개수
		int maxPage = (int)Math.ceil((double)countAll/countPerPage);
		int startPage = ((page-1)/bottomPerNum)*bottomPerNum+1;
		int endPage = startPage+bottomPerNum-1;
		
		int startRow = (page-1)*countPerPage+1;
		int endRow = startRow+countPerPage-1;
		
		//endPage가 maxPage보다 크면 endPage를 maxPage에 맞춰줌
		if(endPage>maxPage) endPage=maxPage;
		
		ArrayList<BoardDto> list = boardMapper.selectAll(startRow,endRow,category,searchWord);
		//데이터전송 - list,page,maxPage,startPage,endPage
		Map<String, Object> map = new HashMap<>(); 
		map.put("list", list);
		map.put("countAll", countAll);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);

		return map;
	}

	//게시글 1개 가져오기
	@Override
	public Map<String, Object> selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		BoardDto prevdto = boardMapper.selectOnePrev(bno);
		BoardDto nextdto = boardMapper.selectOneNext(bno);
		
		//조회수 1증가
		boardMapper.bhitUP(bno);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevdto", prevdto);
		map.put("nextdto", nextdto);
		
		return map;
	}

	//게시글 저장
	@Override
	public void bInsert(BoardDto bdto) {
		int result = boardMapper.bInsert(bdto);
		System.out.println("BServiceImpl bInsert result "+result);
	}

	//게시글 삭제
	@Override
	public void bDelete(int bno) {
		int result = boardMapper.bDelete(bno);
		System.out.println("BServiceImpl bDelete result "+result);
		
	}

	//게시글 수정
	@Override
	public void doBUpdate(BoardDto bdto) {
		int result = boardMapper.doBUpdate(bdto);
		System.out.println("BServiceImpl doBUpdate result "+result);
	}

	//답변달기 저장
	@Override
	public void doBReply(BoardDto bdto) {
		//bgroup,bstep,bindent
		//1. 부모보다 큰 bstep은 1씩 증가시킴
		//2. 답변달기 글의 bstep은 부모의 bstep+1
		//3. 답변달기 글의 bindent 부모의 bindent+1
		//4. bgroup은 부모와 같음
		
		//1
		boardMapper.bstepUP(bdto);
		int result = boardMapper.doBReply(bdto);
		System.out.println("BServiceImpl doBReply result "+result);
		
	}

	

}
