package com.java.www.service;

import com.java.www.dto.MemberDto;

public interface MService {

	//로그인
	MemberDto login(MemberDto mdto);

	void mInsert(MemberDto mdto);

}
