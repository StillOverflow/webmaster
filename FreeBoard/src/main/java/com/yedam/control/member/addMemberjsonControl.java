package com.yedam.control.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class addMemberjsonControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String id = req.getParameter("mid");
		String name = req.getParameter("mname");
		String pw = req.getParameter("passwd");
		String phone = req.getParameter("phone");
		
		MemberVO member = new MemberVO();
		
		member.setMemberId(id);
		member.setMemberName(name);
		member.setPassword(pw);
		member.setPhone(phone);
		
		MemberService svc = new MemberServiceImpl();
		try {
			svc.addMember(member);
			resp.getWriter().print("{\"retCode\":\"OK\"}");
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().print("{\"retCode\":\"FAIL\"}");
		}
		
	}

}
