package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.Member2Dto;
import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {

	//로그인 확인
	Member2Dto login(MemberDto mdto);

	//아이디 찾기
	Member2Dto idSearch(String name, String email);

	//비번 찾기 - 아이디,이메일 검색
	Member2Dto pwsearch(String id, String email);

	//패스워드 변경 - update
	void update_pw(String id, String pwcode);

	//아이디 중복체크
	Member2Dto idCheck(String id);

	
}
