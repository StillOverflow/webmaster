package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.dao.MemberMapper;
import com.yedam.vo.Member;

@WebServlet("/MemberListServlet")
//IoC(객체의 역전)
//객체 생성 => init() => service() => destroy() : 서블릿 순서에 맞게 개발해야 함.
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=utf-8");
		
		SqlSession sqlSession = DataSource.getInstance().openSession();
		MemberMapper dao = sqlSession.getMapper(MemberMapper.class);
		
		List<Member> members = dao.members();
		PrintWriter writer = response.getWriter();
		
		writer.print("<table border='1'>");
		writer.print("<thead><tr><th>아이디</th><th>이름</th><th>비밀번호</th><th>연락처</th><th>권한</th><th>생성일자</th></tr></thead>");
		writer.print("<tbody>");
		for(Member member : members) {
			writer.print("<tr><td><a href='member.action?mid=" + member.getMemberId() + "'>"
					      + member.getMemberId() + "</a></td><td>"
					      + member.getMemberName() + "</td><td>"
					      + member.getPassword() + "</td><td>"
					      + member.getPhone() + "</td><td>"
					      + member.getResponsibility() + "</td><td>"
					      + member.getCreationDate() + "</td></tr>");
		}
		writer.print("</tbody>");
		writer.print("</table>");
		
//		writer.print("<ul>");
//		String result = "";
//		for(Member member : members) {
//			result += "<li>";
//			result += member.toString();
//			result += "</li>";
//		}
//		writer.print(result);
//		writer.print("</ul>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
