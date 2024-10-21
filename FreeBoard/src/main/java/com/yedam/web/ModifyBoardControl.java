package com.yedam.web;

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

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		
		//GET: 수정화면, POST: 수정처리 두 가지 구현
		BoardService svc = new BoardServiceImpl();
		HttpSession sess = req.getSession();
		String responsibility = String.valueOf(sess.getAttribute("responsibility"));
		String bno = req.getParameter("bno");				
		String pg = req.getParameter("page");
		String sc = req.getParameter("sc");
		String kw = req.getParameter("keyword");
		
		if(req.getMethod().equals("GET")) {			
			
			BoardVO bvo = svc.searchBoard(Integer.parseInt(bno));
			req.setAttribute("boardvo", bvo);
			req.setAttribute("sc", sc);
			req.setAttribute("keyword", kw);
			req.setAttribute("page", pg);
//			req.getRequestDispatcher("WEB-INF/jsp/modifyBoardForm.jsp").forward(req, resp);			
			
			if(responsibility.equals("Admin")) {
				req.getRequestDispatcher("admin/modifyBoardForm.tiles").forward(req, resp);
			} else {
				req.getRequestDispatcher("board/modifyBoardForm.tiles").forward(req, resp);
			}
			
		} else if (req.getMethod().equals("POST")) {
			
			MultipartRequest mr = new MultipartRequest(
					//요청경로, 저장경로, 파일 최대 크기, 인코딩방식
					req
					,req.getServletContext().getRealPath("images")
					,1024 * 1024 * 5
					,"utf-8"
					,new DefaultFileRenamePolicy() //똑같은 파일 이름 있으면 이름 바꿔서 계속 업로드
					);
			
			bno = mr.getParameter("bno");
			pg = mr.getParameter("page");
			sc = mr.getParameter("sc");
			kw = mr.getParameter("keyword");
			
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			String img = mr.getFilesystemName("img");
			
			BoardVO bvo = new BoardVO();
			bvo.setBoardNo(Integer.parseInt(bno));
			bvo.setTitle(title);
			bvo.setContent(content);
			bvo.setImg(img);
			
			if(svc.modifyBoard(bvo)) {
				resp.sendRedirect("board.do?bno=" + bno + "&page=" + pg + "&sc=" + sc + "&keyword=" + kw);
			} else {
				bvo = svc.searchBoard(Integer.parseInt(bno));
				
				req.setAttribute("boardvo", bvo);
				req.setAttribute("msg", "수정할 게시글이 없습니다.");
//				req.getRequestDispatcher("WEB-INF/jsp/modifyBoardForm.jsp").forward(req, resp);			
				
				if(responsibility.equals("Admin")) {
					req.getRequestDispatcher("admin/modifyBoardForm.tiles").forward(req, resp);
				} else {
					req.getRequestDispatcher("board/modifyBoardForm.tiles").forward(req, resp);
				}
			}
		}
		
	
	}

}
