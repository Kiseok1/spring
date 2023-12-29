package com.java.www.service;

import java.util.List;
import java.util.Map;

import com.java.www.dto.CommentDto;
import com.java.www.dto.NoticeDto;

public interface NService {

	//게시글 전체 가져오기
	Map<String, Object> selectAll(int page);

	//게시글 클릭
	Map<String, Object> selectOne(int bno);

	//게시글 등록
	void notice_write(NoticeDto ndto);

	//댓글 등록
	CommentDto cInsert(CommentDto cdto);

	//댓글 삭제
	String cDelete(CommentDto cdto);

}
