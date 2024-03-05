package com.java.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	
	@Override
	public List<MemberDto> selectAll(String gender, int page,int ppr) {
		int listCount = boardMapper.listCount(gender);
		int pagePerRow = ppr;
		int numberingBox = 5;
		int firstRow = (page-1)*pagePerRow+1;
		int endRow = firstRow+pagePerRow-1;
		int firstBox = (page-1)/numberingBox+1;
		int endBox = firstBox+numberingBox-1;
		int maxBox = (int) Math.ceil((double)listCount/pagePerRow);
		System.out.println(maxBox);
		List<MemberDto> list = boardMapper.selectAll(gender,firstRow,endRow);
		return list;
	}

}//BServiceImpl
