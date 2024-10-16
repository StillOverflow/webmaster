package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class MemberAddControl implements Control{
	
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddControl");
		String id = req.getParameter("mid");
		String nm = req.getParameter("mname");
		String pw = req.getParameter("passwd");
		String pn = req.getParameter("phone");
		System.out.println(id + nm + pw + pn);
		
		MemberVO mvo = new MemberVO();
		mvo.setMemberId(id);
		mvo.setMemberName(nm);
		mvo.setPassword(pw);
		mvo.setPhone(pn);
		
		MemberService svc = new MemberServiceImpl();
		try {
			svc.addMember(mvo);
			//목록페이지로 이동
			//forward(req, resp)와 비슷하지만, sendRedirect()는 값을 넘겨주진 못함.
			resp.sendRedirect("memberList.do");
		} catch (Exception e) {
			//등록화면으로 이동
			e.printStackTrace();
			resp.sendRedirect("memberAddForm.do");
		}
	}
}
