package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

public interface BoardService {
	List<BoardVO> boardList(SearchDTO search);
	int getTotalCnt(SearchDTO search);
	boolean registerBoard(BoardVO board);
	boolean removeBoard(int boardNo);
	boolean modifyBoard(BoardVO board);
	BoardVO searchBoard(int boardNo);
	BoardVO seeBoard(int boardNo);
}
