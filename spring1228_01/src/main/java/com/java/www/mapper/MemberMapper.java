package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {

	//로그인
	MemberDto login(MemberDto mdto);

	//회원가입
	void mInsert(MemberDto mdto);

}
