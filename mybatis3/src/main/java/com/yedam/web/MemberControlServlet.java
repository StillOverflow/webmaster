package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.dao.MemberMapper;
import com.yedam.vo.Member;

//IoC(Inversion of Control 객체의 역전)
//객체 생성 => init() => service() => destroy() : 서블릿 순서에 맞게 개발해야 함.

//객체 생성 => init => service => destroy
@WebServlet("/member.action") // 파일이 아닌 url주소임
public class MemberControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public MemberControlServlet() {
		System.out.println("MemberControl 객체 생성");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("최초 요청이면 init 실행");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8"); //parameter가 한글일 때 setCharacterEncoding utf-8 넣어줘야 함.
		
		System.out.println("서블릿을 요청할 때마다 실행");
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		MemberMapper dao = sqlSession.getMapper(MemberMapper.class);
		String id = req.getParameter("mid"); // http://localhost/mybatis3/member.action?mid => member.action을 호출하는데 파라미터는 mid다.
		
		// get: 조회화면, post: 삭제처리 구현
		if(req.getMethod().equals("GET")) {
			Member member = dao.selectMember(id);
			
			if (member == null) {
				resp.getWriter().print("조회된 정보가 없습니다.");
				return;
			}
			
			String str = "<h3>회원정보</h3>";
			str += "<form action='member.action' method='post'>";
			str += "<input type='hidden' name='mid' value='" + member.getMemberId() + "'>"; // name = parameter
			str += "<table border='1'>";
			str += "<tr><th>회원아이디</th><td>" + member.getMemberId() + "</td></tr>";
			str += "<tr><th>회원이름</th><td>" + member.getMemberName() + "</td></tr>";
			str += "<tr><th>연락처</th><td>" + member.getPhone() + "</td></tr>";
			str += "<tr><td colspan='2'><input type='submit' value='삭제'></tr></td>";
			str += "</table>";
			str += "</form>";
			str += "<a href='MemberListServlet'>목록으로</a>";
			resp.getWriter().print(str);
			
		} else if(req.getMethod().equals("POST")) {
			if(dao.deleteMember(id) == 1) {
				resp.getWriter().print("<p>삭제완료</p>");
			} else {
				resp.getWriter().print("<p>삭제할 회원이 없습니다.</p>");
			}
			resp.getWriter().print("<a href='MemberListServlet'>목록으로</a>");
		}


	}

	@Override
	public void destroy() {
		System.out.println("서버가 종료될 때 한 번 실행");
	}

}
