package com.yedam.control.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// board.do => 상세조회(bno) => 조회 => board.jsp 출력
		
		String bno = req.getParameter("bno");
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.seeBoard(Integer.parseInt(bno));
		req.setAttribute("boardvo", board);
		req.setAttribute("page", req.getParameter("page"));
		req.setAttribute("sc", req.getParameter("sc"));
		req.setAttribute("keyword", req.getParameter("keyword"));
		
		//req.getRequestDispatcher("WEB-INF/jsp/board.jsp").forward(req, resp);
		HttpSession sess = req.getSession();
		String responsibility = String.valueOf(sess.getAttribute("responsibility"));
		System.out.println(responsibility);
		
		if(responsibility.equals("Admin")) {
			req.getRequestDispatcher("admin/board.tiles").forward(req, resp);
		} else {
			req.getRequestDispatcher("board/board.tiles").forward(req, resp);
		}
		
	}

}
