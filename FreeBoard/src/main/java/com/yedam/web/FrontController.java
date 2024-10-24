package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.control.AddEventControl;
import com.yedam.control.CalendarControl;
import com.yedam.control.CalendarjsonControl;
import com.yedam.control.ChartControl;
import com.yedam.control.CountByWriterControl;
import com.yedam.control.JavascriptControl;
import com.yedam.control.RemoveEventControl;
import com.yedam.control.etcControl;
import com.yedam.control.board.AddBoardControl;
import com.yedam.control.board.AddBoardForm;
import com.yedam.control.board.AddReplyControl;
import com.yedam.control.board.BoardControl;
import com.yedam.control.board.BoardListControl;
import com.yedam.control.board.CountReplyControl;
import com.yedam.control.board.ModifyBoardControl;
import com.yedam.control.board.RemoveBoardControl;
import com.yedam.control.board.RemoveReplyControl;
import com.yedam.control.board.ReplyListControl;
import com.yedam.control.member.LoginControl;
import com.yedam.control.member.LogoutControl;
import com.yedam.control.member.MemberAddControl;
import com.yedam.control.member.MemberAddFormControl;
import com.yedam.control.member.MemberListControl;
import com.yedam.control.member.MemberjsonControl;
import com.yedam.control.member.addMemberjsonControl;
import com.yedam.control.member.removeMemberjsonControl;

//Servlet은 여러 개 있으면 복잡하므로 프로젝트 시 1개만 생성할 것임.
//대표 컨트롤러 => FrontController : MVC모델로 수많은 뷰에서 들어오는 요청들을 하나의 대표 컨트롤러에서 일괄적으로 처리하는 디자인패턴
@WebServlet("*.do") //url에 .do로 끝나는 모든 요청마다 실행 (ex: ~~/abc.do, ~~/rrr.do ...)
public class FrontController extends HttpServlet {
	
	Map<String,Control> map; //HttpServletRequest, Response 인수로 받는 Control 인터페이스 생성하여 입력
	
	private static final long serialVersionUID = 1L;
	
	public FrontController() {
		System.out.println("객체생성");
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init호출");
		//Control 인터페이스 구현할 클래스 = 대표컨트롤러를 거치면 일명 서브컨트롤러들이 최종적으로 실행되도록 함.
		map.put("/memberList.do", new MemberListControl());
		
		//회원등록 1)등록화면 2)등록처리 --반드시 순서대로 실행(.jsp로 바로연결할 수 없음.)
		map.put("/memberAddForm.do", new MemberAddFormControl());
		map.put("/memberAdd.do", new MemberAddControl());
		
		//게시판 관련
		map.put("/boardList.do", new BoardListControl());
		map.put("/board.do", new BoardControl());
		//게시글 등록
		map.put("/addBoardForm.do", new AddBoardForm());
		map.put("/addBoard.do", new AddBoardControl());
		//게시글 수정
		map.put("/modifyBoard.do", new ModifyBoardControl());
		//게시글 삭제
		map.put("/removeBoard.do", new RemoveBoardControl());
		
		//로그인
		map.put("/loginForm.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
		
		map.put("/javascript.do", new JavascriptControl()); //javascript/javascript.jsp 열어주는 컨트롤러
		map.put("/etc.javascript.do", new etcControl()); //etc/javascript.jsp 열어주는 컨트롤러
		map.put("/memberjson.do", new MemberjsonControl());
		//Json데이터를 가져오는 컨트롤러 따로 있어야 함.
		//javascript.jsp에 컨트롤러에서 가져온 데이터 활용, member.js에서 동적 테이블 그리기.
		map.put("/addMemberjson.do", new addMemberjsonControl());
		map.put("/removeMemberjson.do", new removeMemberjsonControl());
		
		//댓글 javasciprt 동적 구현
		map.put("/replyList.do", new ReplyListControl());
		map.put("/removeReply.do", new RemoveReplyControl());
		map.put("/addReply.do", new AddReplyControl());
		//댓글의 전체 개수 가져오는 컨트롤러
		map.put("/countReply.do", new CountReplyControl());
		
		//차트
		map.put("/countByWriter.do", new CountByWriterControl());
		map.put("/chart.do", new ChartControl());
		
		//캘린더(fullCalendar)
		map.put("/calendarjson.do", new CalendarjsonControl());
		map.put("/calendar.do", new CalendarControl());
		map.put("/addEvent.do", new AddEventControl());
		map.put("/removeEvent.do", new RemoveEventControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("service호출");
		//요청페이지가 뭔지?
		
		String uri = req.getRequestURI(); //리소스 고유식별자(/FreeBoard/add.do)
		System.out.println(uri);
		String context = req.getContextPath(); //Context는 프로젝트 이름(FreeBoard)을 말함.
		System.out.println(context);
		String page = uri.substring(context.length()); // substring 통해 /add.do만 얻을 수 있음.
		
		Control control = map.get(page); //page 문자열키(식별자)의 값(무엇을 실행할지)을 control에 가져옴.
		control.exec(req, resp); //
	
	}
	
}
