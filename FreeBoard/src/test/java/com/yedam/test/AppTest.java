package com.yedam.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class AppTest {

	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		BoardVO bvo = new BoardVO();
//		bvo.setTitle("샘플 제목입니다.");
		bvo.setContent("내용인가요?");
//		bvo.setWriter("user01");
		bvo.setBoardNo(8);
		
//		if(mapper.insertBoard(bvo) == 1) {
//			sqlSession.commit();
//		}
		
//		if(mapper.updateBoard(bvo) == 1) {
//			sqlSession.commit();
//		}
		
//		if(mapper.deleteBoard(8) == 1) {
//			sqlSession.commit();
//		}
		
		if(mapper.selectBoard(4) == null) {
			System.out.println("조회된 내용이 없습니다.");
		} else {
			System.out.println("조회에 성공했습니다.");
		}
		
		List<BoardVO> list = mapper.boardList();
		for(BoardVO bvo2 : list) {
			System.out.println(bvo2.toString());
		}
		
		System.out.println("페이징 방식 구현");
		SearchDTO search = new SearchDTO();
		search.setPage(20);
		
		list = mapper.listWithPage(search);
		for(BoardVO bvo2 : list) {
			System.out.println(bvo2.toString());
		}
		
		List<Map<String, Object>> bList = mapper.countByWriter();
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(bList);
		System.out.println(json);
		
//		MemberService memMapper = new MemberServiceImpl();
//		System.out.println(memMapper.login("user01", "55555"));
//		
//		ReplyService repsvc = new ReplyServiceImpl();
//		
//		ReplyVO reply = new ReplyVO();
//		reply.setReply("댓글입니다.");
//		reply.setReplyer("user01");
//		reply.setBoardNo(392);
//		
//		repsvc.registerReply(reply);
//		
//		List<ReplyVO> list = repsvc.replyList(392);
//		for(ReplyVO rep : list) {
//			System.out.println(rep.toString());
//		}
//		
//		System.out.println(repsvc.selectReply(1));

	}

}
