package com.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//페이징방식 적용
		String page = req.getParameter("page");
		page = page == null? "1" : page; //page 파라미터가 없으면 1페이지를 보여줘라.
		
		//검색조건 적용
		String sc = req.getParameter("sc");
		String kw = req.getParameter("keyword");
		
		SearchDTO search = new SearchDTO();
		search.setSearchCondition(sc);
		search.setKeyword(kw);
		search.setPage(Integer.parseInt(page));
		
		//글목록 조회 후 jsp 전달
		BoardService svc = new BoardServiceImpl();
		List<BoardVO> list = svc.boardList(search);
		int totalCnt = svc.getTotalCnt(search);
		
		req.setAttribute("boardList", list);
		req.setAttribute("page", new PageDTO(Integer.parseInt(page), totalCnt));
		req.setAttribute("search", search);
//		req.setAttribute("page", page);
		
		req.getRequestDispatcher("WEB-INF/jsp/boardList.jsp").forward(req, resp);
	}

}
