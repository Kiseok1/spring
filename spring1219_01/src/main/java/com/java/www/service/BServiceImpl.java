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
	
	//게시글 전체 가져오기
	@Override
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		return list;
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
