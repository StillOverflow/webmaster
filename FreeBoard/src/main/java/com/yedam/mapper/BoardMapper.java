package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

public interface BoardMapper {
	//전체목록
	List<BoardVO> boardList();
	//목록 페이징
	List<BoardVO> listWithPage(SearchDTO search);
	//전체 글의 갯수
	int selectCount(SearchDTO search);
	
	//등록/수정/삭제
	//boolean도 작동은 되지만, 처리된 건수가 나타나므로 int가 좋음.
	int insertBoard(BoardVO board);
	int updateBoard(BoardVO board);
	int deleteBoard(int boarNo);
	
	//상세조회
	BoardVO selectBoard(int boardNo);
	
	//조회수 증가
	int updateCount(int boardNo);
}
