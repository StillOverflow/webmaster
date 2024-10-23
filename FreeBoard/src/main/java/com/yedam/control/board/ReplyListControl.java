package com.yedam.control.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class ReplyListControl implements Control{

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		
		int bno = Integer.parseInt(req.getParameter("bno"));
		int page = Integer.parseInt(req.getParameter("page"));
		
		ReplyService svc = new ReplyServiceImpl();
		List<ReplyVO> list = svc.replyList(bno, page);
		
		//List타입으로 데이터를 생성하면 직접 for문으로 String json문자열 만들어도 되지만,
		//Gson 라이브러리 활용하면 json문자열로 편리하게 만들어줌.
		Gson gson = new GsonBuilder().create();
		System.out.println("Gson: " + gson + gson.toString());
		String json = gson.toJson(list); //자바 객체를 json문자열로 변경.
		
		resp.getWriter().print(json);
	
	}
	
}
