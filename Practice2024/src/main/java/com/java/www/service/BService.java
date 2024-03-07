package com.java.www.service;

import java.util.List;
import java.util.Map;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

public interface BService {

	//전체 정보 가져오기
	Map<String, Object> selectAll(String gender, int page, int ppr);

}//BService
