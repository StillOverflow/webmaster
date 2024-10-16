package com.yedam.dao;

import java.util.List;

import com.yedam.vo.Member;

public interface MemberMapper {
	public List<Member> members();
	public boolean insertMember(Member member);
	public int updateMember(Member Member);
	public int deleteMember(String memberId);
	public Member selectMember(String memberId); //단건 조회 시
	//@Param("memberId") String memberId....이런식으로 Member 클래스 없이 넣을 수는 있음.
}
