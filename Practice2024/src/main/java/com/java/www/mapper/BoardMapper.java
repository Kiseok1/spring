package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Mapper
public interface BoardMapper {
	
	//전체 회원 가져오기
	List<MemberDto> selectAll(String gender, int firstRow, int endRow, String category, String searchWord);

	//카운트 가져오기
	int listCount(String gender, String category, String searchWord);

	//회원삭제
	void deleteMember(int[] ymnos);

}//BoardMapper
