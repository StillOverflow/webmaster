package com.yedam.mapper;

import java.util.List;
import com.yedam.vo.MemberVO;

public interface MemberMapper {
	public List<MemberVO> members();
	public boolean insertMember(MemberVO member);
	public int updateMember(MemberVO Member);
	public int deleteMember(String memberId);
	public MemberVO selectMember(String memberId); //단건 조회 시
	public MemberVO login(String logId, String password);
	//@Param("memberId") String memberId....이런식으로 Member 클래스 없이 넣을 수는 있음.
}
