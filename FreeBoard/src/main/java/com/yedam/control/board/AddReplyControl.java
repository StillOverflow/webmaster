package com.yedam.control.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(Integer.parseInt(bno));
		rvo.setReplyer(replyer);
		rvo.setReply(reply);
		
		//반환값 선언
		//{"retCode":"OK", "retVal":"rvo"}
		Map<String, Object> result = new HashMap<>();
		
		ReplyService svc = new ReplyServiceImpl();
		Gson gson = new GsonBuilder().create();
		
		try {
			svc.registerReply(rvo);

			result.put("retCode","OK");
			result.put("retVal", rvo);
			System.out.println(result);
			
			String json = gson.toJson(result);
			resp.getWriter().print(json);
			//resp.getWriter().print("{\"retCode\": \"OK\"}");
		} catch(Exception e) {
			e.printStackTrace();
			
			result.put("retCode","FAIL");
			result.put("retVal", rvo);
			System.out.println(result);
			
			String json = gson.toJson(result);
			resp.getWriter().print(json);
			//resp.getWriter().print("{\"retCode\": \"FAIL\"}");
		}
	}

}
