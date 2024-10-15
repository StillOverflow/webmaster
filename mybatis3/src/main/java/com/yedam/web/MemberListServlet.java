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
		
		writer.print("<table>");
		writer.print("<th>아이디</th><th>이름</th><th>비밀번호</th><th>연락처</th><th>권한</th><th>생성일자</th>");
		for(Member member : members) {
			writer.print("<tr>");
			writer.print("<td>");
			writer.print(member.getMemberId());			
			writer.print("</td>");
			writer.print("<td>");
			writer.print(member.getMemberName());			
			writer.print("</td>");
			writer.print("<td>");
			writer.print(member.getPassword());			
			writer.print("</td>");
			writer.print("<td>");
			writer.print(member.getPhone());			
			writer.print("</td>");
			writer.print("<td>");
			writer.print(member.getResponsibility());			
			writer.print("</td>");
			writer.print("<td>");
			writer.print(member.getCreationDate());			
			writer.print("</td>");
			writer.print("</tr>");
		}
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
