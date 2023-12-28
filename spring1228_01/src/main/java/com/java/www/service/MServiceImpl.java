package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemberMapper;

@Service
public class MServiceImpl implements MService {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto mdto) {
		MemberDto memDto = memberMapper.login(mdto);
		return memDto;
	}

	@Override //회원가입
	public void mInsert(MemberDto mdto) {
		memberMapper.mInsert(mdto);
		
	}

}
