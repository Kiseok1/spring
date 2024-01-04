package com.java.www.service;

import com.java.www.dto.Member2Dto;
import com.java.www.dto.MemberDto;

public interface MService {

	//로그인 확인
	int login(MemberDto mdto);

	//id확인
	Member2Dto idSearch(String name, String email);

	//비번 찾기 - 아이디,이메일 검색
	String pwsearch(String id, String email);

	//아이지 중복체크
	Member2Dto idCheck(String id);

}
