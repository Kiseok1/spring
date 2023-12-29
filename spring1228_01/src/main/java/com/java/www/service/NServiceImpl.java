package com.java.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.CommentDto;
import com.java.www.dto.NoticeDto;
import com.java.www.mapper.NoticeMapper;

@Service
public class NServiceImpl implements NService {

	@Autowired
	NoticeMapper noticeMapper;

	@Override //게시글 전체 가져오기
	public Map<String, Object> selectAll(int page) {
		//게시글 총개수
		int listCount = noticeMapper.listCount();
		System.out.println(listCount);	
		int rowPerPage = 8;
		int numberingBox = 5;
		int startPage = ((page-1)/numberingBox)*numberingBox+1;
		int endPage = startPage+numberingBox-1;
		int maxPage = (int)Math.ceil( (double)listCount/rowPerPage );
		if(endPage>maxPage) endPage=maxPage;
		int startRow = (page-1)*rowPerPage+1;
		int endRow = startRow+rowPerPage-1;
		//int endRow = page*rowPerPage;
		
		List<NoticeDto> list = noticeMapper.selectAll(page,startRow,endRow);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("maxPage", maxPage);
		map.put("page", page);
		
		return map;
	}

	@Override //게시글 클릭
	public Map<String, Object> selectOne(int bno) {
		Map<String, Object> map = new HashMap<>();
		//조회수 1증가
		noticeMapper.bhitUp(bno);
		//게시글 1개 가져오기
		NoticeDto ndto = noticeMapper.selectOne(bno);
		//이전글,다음글
		NoticeDto prevDto = noticeMapper.selectOnePrev(bno);
		NoticeDto nextDto = noticeMapper.selectOneNext(bno);
		//댓글 가져오기
		List<CommentDto> list = noticeMapper.commentSelectAll(bno);
		
		map.put("ndto", ndto);
		map.put("prevDto", prevDto);
		map.put("nextDto", nextDto);
		map.put("list", list);
		return map;
	}

	@Override //게시글 등록
	public void notice_write(NoticeDto ndto) {
		noticeMapper.notice_write(ndto);
		
	}

	@Override //댓글 등록
	public CommentDto cInsert(CommentDto cdto) {
		noticeMapper.cInsert(cdto);
		return cdto;
	}

	@Override //댓글 삭제
	public String cDelete(CommentDto cdto) {
		noticeMapper.cDelete(cdto);
		String result = "삭제완료";
		return result;
	}
}
