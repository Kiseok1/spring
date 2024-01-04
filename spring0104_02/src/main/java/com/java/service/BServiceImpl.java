package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	
	@Override //게시글 전체 가져오기
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	@Override //게시글 1개 저장
	public void bwrite(BoardDto bdto) {
		boardMapper.bwrite(bdto);
		
	}

}
