package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	ArrayList<BoardDto> selectAll(int startRow, int endRow, String category, String searchWord);
	int countAll(int page,String category, String searchWord);

	BoardDto selectOne(int bno);

	BoardDto selectOnePrev(int bno);

	BoardDto selectOneNext(int bno);

	int bInsert(BoardDto bdto);

	int bUpdate(BoardDto bdto);

	int bDelete(int bno);

	void bstepUp(BoardDto bdto);

	int doBReply(BoardDto bdto);


	

	
}
