package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.vo.Member;

public class MemberDAO extends DAO {
	public List<Member> memberList() {
		List<Member> list = new ArrayList<>();
		connect();
		try {
			pstmt = conn.prepareStatement("select * from tbl_member");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setPhone(rs.getString("phone"));
				member.setResponsibility(rs.getString("responsibility"));
				member.setCreationDate(rs.getDate("creation_date"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		//이러한 과정들 단순화하기 위해 Mybatis 설치 필요(사이트에서 시작하기-다운로드)
		//Mybatis : java - sql 자동 mapping 해주며 빠르게 개발할 수 있게 해주는 편리한 Persistence Framework
	}
}
