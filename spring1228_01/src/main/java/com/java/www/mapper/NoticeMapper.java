package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.CommentDto;
import com.java.www.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	//게시글 전체 가져오기
	List<NoticeDto> selectAll(int page, int startRow, int endRow);

	//조회수 1증가
	void bhitUp(int bno);
	//게시글 1개 가져오기
	NoticeDto selectOne(int bno);
	//이전글, 다음글
	NoticeDto selectOnePrev(int bno);
	NoticeDto selectOneNext(int bno);
	//댓글 가져오기
	List<CommentDto> commentSelectAll(int bno);

	//게시글 등록
	void notice_write(NoticeDto ndto);

	//댓글 등록
	void cInsert(CommentDto cdto);

	//댓글 삭제
	void cDelete(CommentDto cdto);
	
	//게시글 총개수
	int listCount();

}
