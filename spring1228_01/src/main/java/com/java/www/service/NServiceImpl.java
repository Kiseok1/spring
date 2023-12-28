package com.java.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.CommentDto;
import com.java.www.dto.NoticeDto;
import com.java.www.mapper.NoticeMapper;

@Service
public class NServiceImpl implements NService {

	@Autowired
	NoticeMapper noticeMapper;

	@Override //게시글 전체 가져오기
	public List<NoticeDto> selectAll() {
		List<NoticeDto> list = noticeMapper.selectAll();
		return list;
	}

	@Override //게시글 클릭
	public Map<String, Object> selectOne(int bno) {
		Map<String, Object> map = new HashMap<>();
		//조회수 1증가
		noticeMapper.bhitUp(bno);
		//게시글 1개 가져오기
		NoticeDto ndto = noticeMapper.selectOne(bno);
		//이전글,다음글
		NoticeDto prevDto = noticeMapper.selectOnePrev(bno);
		NoticeDto nextDto = noticeMapper.selectOneNext(bno);
		//댓글 가져오기
		List<CommentDto> list = noticeMapper.commentSelectAll(bno);
		
		map.put("ndto", ndto);
		map.put("prevDto", prevDto);
		map.put("nextDto", nextDto);
		map.put("list", list);
		return map;
	}

	@Override //게시글 등록
	public void notice_write(NoticeDto ndto) {
		noticeMapper.notice_write(ndto);
		
	}

	@Override //댓글 등록
	public CommentDto cInsert(CommentDto cdto) {
		noticeMapper.cInsert(cdto);
		return cdto;
	}
}
