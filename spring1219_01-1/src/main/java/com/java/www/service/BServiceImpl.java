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
	
	@Override
	public Map<String, Object> selectAll(int page, String category, String searchWord) {
		int countAll = boardMapper.countAll(page,category,searchWord);
		int rowPerPage = 10;
		int numberingBox = 10;
		int maxPage = (int)Math.ceil((double)countAll/rowPerPage);
		int startRow = (page-1)*rowPerPage+1;
		int endRow = startRow+rowPerPage-1;
		int startPage = ((page-1)/numberingBox)*numberingBox+1;
		int endPage = startPage+numberingBox-1;
		if (endPage>maxPage) endPage=maxPage;
		
		ArrayList<BoardDto> list = boardMapper.selectAll(startRow,endRow,category,searchWord);
		
		Map<String, Object> map = new HashMap<>();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("maxPage", maxPage);
		map.put("page", page);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		BoardDto prevbdto = boardMapper.selectOnePrev(bno);
		BoardDto nextbdto = boardMapper.selectOneNext(bno);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevdto", prevbdto);
		map.put("nextdto", nextbdto);
		
		return map;
	}

	@Override
	public void bInsert(BoardDto bdto) {
		int result = boardMapper.bInsert(bdto);
		
	}

	@Override
	public void bUpdate(BoardDto bdto) {
		int result = boardMapper.bUpdate(bdto);
		
	}

	@Override
	public void bDelete(int bno) {
		int result = boardMapper.bDelete(bno);
		
	}

	@Override
	public void doBReply(BoardDto bdto) {
		
		//bstep+1
		boardMapper.bstepUp(bdto);
		
		int result = boardMapper.doBReply(bdto);
		
	}

}
