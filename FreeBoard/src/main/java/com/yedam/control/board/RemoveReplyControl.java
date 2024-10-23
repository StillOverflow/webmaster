package com.yedam.control.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class RemoveReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json;charset=utf-8");
		
		int rno = Integer.parseInt(req.getParameter("rno"));
		
		ReplyService svc = new ReplyServiceImpl();
		try {
			svc.removeReply(rno);
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} catch(Exception e) {
			e.printStackTrace();
			resp.getWriter().print("{\"retCode\": \"FAIL\"}");
		}
		
	}

}
