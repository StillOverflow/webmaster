package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;

public class AddBoardForm implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("WEB-INF/jsp/addBoardForm.jsp").forward(req, resp);
		HttpSession sess = req.getSession(false);
		String responsibility = String.valueOf(sess.getAttribute("responsibility"));
		System.out.println(responsibility);
		
		if(responsibility.equals("Admin")) {
			req.getRequestDispatcher("admin/addBoardForm.tiles").forward(req, resp);
		} else {
			req.getRequestDispatcher("board/addBoardForm.tiles").forward(req, resp);
		}
	}

}
