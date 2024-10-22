package com.yedam.control.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		
		String bno = req.getParameter("bno");
		String replyer = req.getParameter("replyer");
		String reply = req.getParameter("reply");
		
		ReplyVO rep = new ReplyVO();
		rep.setBoardNo(Integer.parseInt(bno));
		rep.setReplyer(replyer);
		rep.setReply(reply);
		
		ReplyService svc = new ReplyServiceImpl();
		try {
			svc.registerReply(rep);
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} catch(Exception e) {
			e.printStackTrace();
			resp.getWriter().print("{\"retCode\": \"FAIL\"}");
		}
	}

}
