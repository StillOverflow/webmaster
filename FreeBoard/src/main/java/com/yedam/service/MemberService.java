package com.yedam.service;

import java.util.List;

import com.yedam.vo.MemberVO;

//업무로직을 설정.(해당 인터페이스 메소드 이름으로 반드시 구현해야 함. 혼선 방지)
public interface MemberService {
	boolean addMember(MemberVO member);
	boolean retireMember(String id);
	MemberVO login(String logId, String password);
	
	//회원목록 출력 메소드
	List<MemberVO> memberList();
}
