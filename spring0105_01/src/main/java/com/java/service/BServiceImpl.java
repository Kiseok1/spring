package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.mapper.BoardMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	@Autowired HttpSession session;
	
	@Override //게시글 전체 가져오기
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	@Override //service 연결 - 글쓰기저장
	public void bwrite(BoardDto bdto) {
		boardMapper.bwrite(bdto);
		
	}

	@Override //게시글 1개 가져오기
	public BoardDto selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		return bdto;
	}

	
	@Override //로그인
	public MemberDto login(MemberDto mdto) {
		MemberDto memdto = boardMapper.login(mdto);
		return memdto;
	}

	@Override
	public void bInsert(BoardDto bdto) {
		boardMapper.bInsert(bdto);
	}

}
