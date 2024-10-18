package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		//GET: 수정화면, POST: 수정처리 두 가지 구현
		String bno = req.getParameter("bno");				
		BoardService svc = new BoardServiceImpl();
		String pg = req.getParameter("page");
		String sc = req.getParameter("sc");
		String kw = req.getParameter("keyword");
		
		if(req.getMethod().equals("GET")) {			
			
			BoardVO bvo = svc.searchBoard(Integer.parseInt(bno));
			req.setAttribute("boardvo", bvo);
			req.setAttribute("sc", sc);
			req.setAttribute("keyword", kw);
			req.setAttribute("page", pg);
			req.getRequestDispatcher("WEB-INF/jsp/modifyBoardForm.jsp").forward(req, resp);			
		
		} else if (req.getMethod().equals("POST")) {
			
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			BoardVO bvo = new BoardVO();
			bvo.setBoardNo(Integer.parseInt(bno));
			bvo.setTitle(title);
			bvo.setContent(content);
			
			if(svc.modifyBoard(bvo)) {
				resp.sendRedirect("board.do?bno=" + bno + "&page=" + pg + "&sc=" + sc + "&keyword=" + kw);
			} else {
				bvo = svc.searchBoard(Integer.parseInt(bno));
				
				req.setAttribute("boardvo", bvo);
				req.setAttribute("msg", "수정할 게시글이 없습니다.");
				req.getRequestDispatcher("WEB-INF/jsp/modifyBoardForm.jsp").forward(req, resp);			
			}
		}
		
	
	}

}
