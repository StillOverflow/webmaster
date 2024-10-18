package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); //req.getParameter 인코딩방식
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setWriter(writer);
		
		BoardService svc = new BoardServiceImpl();
		try {
			//정상 등록 시 글목록으로 이동
			svc.registerBoard(bvo);
			resp.sendRedirect("boardList.do"); //파라미터 없이 그냥 이동
		} catch(Exception e) {
			//오류 발생(등록 실패) 시 등록화면으로 다시 이동
			e.printStackTrace();
			req.setAttribute("msg", "오류가 발생했습니다.");
			req.getRequestDispatcher("WEB-INF/jsp/addBoardForm.jsp").forward(req, resp);
		}
		
		
	}

}
