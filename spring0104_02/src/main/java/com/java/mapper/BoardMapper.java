package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//게시글 전체 가져오기
	List<BoardDto> selectAll();

	//게시글 1개 저장
	void bwrite(BoardDto bdto);

}
