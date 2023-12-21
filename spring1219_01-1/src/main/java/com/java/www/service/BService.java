package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {

	Map<String, Object> selectAll(int page, String category, String searchWord);

	Map<String, Object> selectOne(int bno);

	void bInsert(BoardDto bdto);

	void bUpdate(BoardDto bdto);

	void bDelete(int bno);

	void doBReply(BoardDto bdto);

}
