package com.yedam.control.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); //req.getParameter 인코딩방식
		String savePath = req.getServletContext().getRealPath("images");
		System.out.println(savePath);
		int maxSize = 1024 * 1024 * 5;
		
		//Multipart 요청에 대한 처리로 변경
		MultipartRequest mr = new MultipartRequest(
				//요청경로, 저장경로, 파일 최대 크기, 인코딩방식
				req
				,savePath
				,maxSize
				,"utf-8"
				,new DefaultFileRenamePolicy() //똑같은 파일 이름 있으면 이름 바꿔서 계속 업로드
				);
		
		String img = mr.getFilesystemName("img");
		System.out.println(img);
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setWriter(writer);
		bvo.setImg(img);
		
		BoardService svc = new BoardServiceImpl();
		try {
			//정상 등록 시 글목록으로 이동
			svc.registerBoard(bvo);
			resp.sendRedirect("boardList.do"); //파라미터 없이 그냥 이동
		} catch(Exception e) {
			//오류 발생(등록 실패) 시 등록화면으로 다시 이동
			e.printStackTrace();
			req.setAttribute("msg", "오류가 발생했습니다.");
			//req.getRequestDispatcher("WEB-INF/jsp/addBoardForm.jsp").forward(req, resp);
			HttpSession sess = req.getSession();
			String responsibility = String.valueOf(sess.getAttribute("responsibility"));
			System.out.println(responsibility);
			
			if(responsibility.equals("Admin")) {
				req.getRequestDispatcher("admin/addBoardForm.tiles").forward(req, resp);
			} else {
				req.getRequestDispatcher("board/addBoardForm.tiles").forward(req, resp);
			}
		
		}
		
		
	}

}
