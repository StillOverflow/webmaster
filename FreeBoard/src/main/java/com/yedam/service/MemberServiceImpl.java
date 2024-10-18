/**
 * 
 */
package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
	
	@Override
	public boolean addMember(MemberVO member) {
		return mapper.insertMember(member) == true;
	}

	@Override
	public boolean retireMember(String id) {
		return mapper.deleteMember(id) == 1;
	}
	
	@Override
	public MemberVO login(String logId, String password) {
		return mapper.login(logId, password);
	}
	
	@Override
	public List<MemberVO> memberList() {
		return mapper.members(); //mapper의 List<Member> 반환타입 메소드 실행
	}

}
