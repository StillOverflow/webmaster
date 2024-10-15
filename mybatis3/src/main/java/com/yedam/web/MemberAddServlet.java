package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.dao.MemberMapper;
import com.yedam.vo.Member;


@WebServlet("/MemberAddServlet")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print("<h3>여기는 웹브라우저</h3>");
		out.print("<h3>응답정보를 처리하는 객체: response</h3>");
		out.print("<a href='index.html'>첫페이지로 이동</a>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("memberId");
		String pw = request.getParameter("password");
		String name = request.getParameter("memberName");
		String phone = request.getParameter("phone");
		
		Member member = new Member();
		member.setMemberId(id);
		member.setPassword(pw);
		member.setMemberName(name);
		member.setPhone(phone);
		
		SqlSession sqlSession = DataSource.getInstance().openSession();
		MemberMapper dao = sqlSession.getMapper(MemberMapper.class);
		try {
			if(dao.insertMember(member) == true) {
				sqlSession.commit();
				response.getWriter().print("OK");
			}
		} catch (Exception e) {
			response.getWriter().print("NG");
		}
	}

}
