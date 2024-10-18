package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class RemoveBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String bno = req.getParameter("bno");
		BoardService svc = new BoardServiceImpl();
		BoardVO bvo = svc.searchBoard(Integer.parseInt(bno));
		String pg = req.getParameter("page");
		String sc = req.getParameter("sc");
		String kw = req.getParameter("keyword");
		
		if(req.getMethod().equals("GET")) {
			
			req.setAttribute("boardvo", bvo);
			req.setAttribute("sc", sc);
			req.setAttribute("keyword", kw);
			req.setAttribute("page", pg);
			req.getRequestDispatcher("WEB-INF/jsp/removeBoard.jsp").forward(req, resp);
						
		} else if (req.getMethod().equals("POST")) {
			
			
			if(svc.removeBoard(Integer.parseInt(bno))) {
				resp.sendRedirect("boardList.do?page=" + pg + "&sc=" + sc + "&keyword=" + kw);
			} else {
				req.getRequestDispatcher("WEB-INF/jsp/board.jsp").forward(req, resp);
			}						
		
		}
		
	}

}
