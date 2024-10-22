package com.yedam.control.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sess = req.getSession();
//		sess.removeAttribute("logId");
		
		if(req.getMethod().equals("GET")) {
			
//			req.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(req, resp);
			req.getRequestDispatcher("WEB-INF/jsp/logForm.jsp").forward(req, resp);
			
		} else if (req.getMethod().equals("POST")) {
			
			MemberService svc = new MemberServiceImpl();
			String id = req.getParameter("logId");
			String pw = req.getParameter("password");
			
			MemberVO member = svc.login(id, pw);
					
			//로그인 실패
			if(member == null) {
				req.setAttribute("msg", "아이디와 비밀번호를 확인하세요.");
//				req.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(req, resp);
				req.getRequestDispatcher("WEB-INF/jsp/logForm.jsp").forward(req, resp);
				return;	
			}
			
			//로그인 성공
			sess = req.getSession();
			sess.setAttribute("logId", id); //로그인 성공 시 id데이터를 세션에 저장
			sess.setAttribute("responsibility", member.getResponsibility());
			
			if(member.getResponsibility().equals("Admin")) {
				resp.sendRedirect("memberList.do");
			} else {
				resp.sendRedirect("boardList.do");
			}
			
		}
	
	}

}
