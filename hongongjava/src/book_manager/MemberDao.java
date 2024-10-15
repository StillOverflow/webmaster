package book_manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends DAO {
	//테이블 작성(아이디, 비밀번호, 이름, 연락처, 권한)
	//샘플 데이터 작성
	//쿼리(조회, 등록...)
	//자바 프로그램
	
	//DAO는 싱글턴 방식으로 하면 '다중 사용 환경'에서 보안 및 메모리 아낄 수 있음(new 남발 제한)
	private static MemberDao instance = new MemberDao();
	private MemberDao(){}
	static MemberDao getInstance() {
		return instance;
	}
	
	//Connection, connect(), disconnect() 상속받음
	PreparedStatement pstmt;
	ResultSet rs;
	
	//아이디 존재 여부 확인 메소드
	Member checkMember(String id, String pw){
		String sql = "select count(1), member_name, responsibility " + //이름만 넣어도 rs.next()로 확인할 수 있음.
	                 "from tbl_member " +
	                 "where member_id = ? and password = ?" +
	                 "group by member_name, responsibility";
		connect();
		Member member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) { //select count(1)로 여부 확인하게 되면 0 또는 1로 나타날 것.
				int cnt = rs.getInt(1);
				if(cnt > 0) {
					member = new Member();
					member.setMemberName(rs.getString(2));
					member.setResponsibility(rs.getString(3));
					return member;
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return member;
	}
	
	//목록 조회
	List<Member> memberList(){
		String sql = "select member_id," +
	                 "       member_name," +
	                 "       password," +
	                 "       phone," +
	                 "       responsibility," +
	                 "       creation_date " +
	                 "from   tbl_member " +
	                 "order by creation_date";
		List<Member> result = new ArrayList<Member>();
		connect();
		try {
			pstmt = conn.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setPassword(rs.getString("password"));
				member.setPhone(rs.getString("phone"));
				member.setResponsibility(rs.getString("responsibility"));
				member.setCreationDate(rs.getDate("creation_date"));
				result.add(member);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		disconnect();
		return result;
	}
	
	//회원 추가
	void memberAdd(String id, String name, String pw, String phone) {
		String sql = "insert into tbl_member(member_id, member_name, password, phone) " +
	                 "values (?, ?, ?, ?)";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pw);
			pstmt.setString(4, phone);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	//아이디 중복 체크
	boolean isMemberId(String id) {
		String sql = "select member_id " +
                     "from   tbl_member " +
				     "where  member_id = ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		disconnect();
		return false;
	}
	
	//회원 삭제
	void memberDel(String id) {
		String sql = "delete from tbl_member " +
	                 "where member_id = ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	//회원정보 변경
	String memberUp(String id, int part, String value) {
		String sql = "update tbl_member ";
	    String col = null;
	    switch(part) {
	    case 1 : sql += "set member_id = ? "; col = "ID"; break;
	    case 2 : sql += "set password = ? "; col = "PW"; break;
	    case 3 : sql += "set member_name = ? "; col = "NAME"; break;
	    case 4 : sql += "set phone = ? "; col = "PHONE"; break;
	    }
	    sql += "where member_id = ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.setString(2, id);
			int result = pstmt.executeUpdate();
			if(result == 1) return col;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return col;
	}
	
}
