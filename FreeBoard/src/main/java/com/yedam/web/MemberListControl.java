package com.yedam.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class MemberListControl implements Control{
	
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("MemberListControl");
		MemberService svc = new MemberServiceImpl();
		List<MemberVO> list = svc.memberList();
		
		req.setAttribute("memberList", list); //memberList.jsp에서 getAttribute로 가져올 값.
		
		try {
			//memberList.do 요청재지정 (memberList.jsp로 자원이나 요청을 보냄)
			//req.getRequestDispatcher("WEB-INF/jsp/memberList.jsp").forward(req, resp);
			req.getRequestDispatcher("admin/memberList.tiles").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
