package com.java.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	
	@Override
	public Map<String, Object> selectAll(String gender, int page,int ppr, String category, String searchWord) {
		System.out.println("ser : "+category);
		System.out.println("ser : "+searchWord);
		
		
		int listCount = boardMapper.listCount(gender,category,searchWord);
		int pagePerRow = ppr;
		int numberingBox = 5;
		int firstRow = (page-1)*pagePerRow+1;
		int endRow = firstRow+pagePerRow-1;
		int firstBox = ((page-1)/numberingBox)*numberingBox+1;
		int endBox = firstBox+numberingBox-1;
		int maxBox = (int) Math.ceil((double)listCount/pagePerRow);
		if(endBox>maxBox) {
			endBox=maxBox;
		}
		System.out.println(maxBox);
		List<MemberDto> list = boardMapper.selectAll(gender,firstRow,endRow,category,searchWord);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("firstBox", firstBox);
		map.put("endBox", endBox);
		map.put("maxBox", maxBox);
		map.put("page", page);
		map.put("gender", gender);
		map.put("ppr", ppr);
		map.put("category", category);
		map.put("searchWord", searchWord);
		return map;
	}

	@Override
	public void deleteMember(int[] ymnos) {
		boardMapper.deleteMember(ymnos);
		
	}

}//BServiceImpl
