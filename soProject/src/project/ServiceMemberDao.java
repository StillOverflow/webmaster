package project;

import java.sql.SQLException;

public class ServiceMemberDao extends ProjectDao {
	
	//DAO에 싱글톤 객체 형성
	private static ServiceMemberDao smdao = new ServiceMemberDao();
	private ServiceMemberDao(){};
	static ServiceMemberDao getInstance() {
		return smdao;
	}
	
	//추가 메소드
	//(ReservationDao.isReserved() == true & isJoinable() == true 일 때 실행해야 함)
	void add(String roomNumber, String password) {
		String sql_getvalue = "SELECT reserve_id, guest_name "
				            + "FROM   reservations "
					        + "WHERE  checkin_date <= TO_DATE("
					        + "                       (SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') "
					        + "                        FROM DUAL), 'YYYY-MM-DD HH24:MI') "
					        + "  AND  checkout_date >= TO_DATE("
					        + "                       (SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') "
					        + "                        FROM DUAL), 'YYYY-MM-DD HH24:MI') "
				            + "  AND  room_number = ?";
		
		String sql_serviceY = "UPDATE reservations "
				            + "SET    service_yn = 'y' "
	                        + "WHERE  reserve_id = ?";
	
		String sql = "INSERT INTO service_members (member_id, member_name, room_number, password) "
				   + "VALUES (?, ?, ?, ?)";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql_getvalue);
			pstmt.setString(1, roomNumber);
			rs = pstmt.executeQuery();
			rs.next();
			String reserveId = rs.getString(1); 
			String name = rs.getString(2); 
			
			pstmt = conn.prepareStatement(sql_serviceY);
			pstmt.setString(1, reserveId);
			pstmt.execute();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reserveId);
			pstmt.setString(2, name);
			pstmt.setString(3, roomNumber);
			pstmt.setString(4, password);
			pstmt.execute();
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	//수정 메소드
	String update(ServiceMember smem) {
		String strPart = "";
		String sql = "UPDATE service_members SET ";
		
		//입력된 값에 따라 쿼리 추가
		if(smem.getMemberName() != null) {
			sql += " member_name = '" + smem.getMemberName() + "',"; 
			strPart += "회원이름/";
		}
		if(smem.getPassword() != null) {
			sql += " password = '" + smem.getPassword() + "',"; 
			strPart += "비밀번호/";
		}
		if(smem.getAvailability() != null) {
			sql += " availability = '" + smem.getAvailability() + "',"; 
			strPart += "가용여부/";
		}
		
		sql = sql.substring(0, sql.length()-1);	//끝자리 , 제거
		strPart = strPart.substring(0, strPart.length()-1);	//끝자리 / 제거
		sql += " WHERE member_id = ?";
			
		connect();
		try {		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, smem.getMemberId());
			pstmt.execute();
			
			pstmt.close();
			disconnect();
			return strPart;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return strPart;
	}
	
	//삭제 메소드
	void delete(String id) {
		String sql = "DELETE service_members "
				   + "WHERE  member_id = ?";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	//전체조회 메소드
	String printAll() {
		String result = "";
		String sql = "SELECT member_id, member_name, room_number, password, "
				   + "       current_coupon, coupon_discount_percent, authority, availability "
				   + "FROM   service_members "
				   + "ORDER  BY member_id";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result += resultPlus(rs, 8);
			}
			rs.close();
			pstmt.close();
			disconnect();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return result;
	}
	
	//검색 메소드
	String search(int part, String value) {
		String result = "";
		String sql = "SELECT member_id, member_name, room_number, password, "
				   + "       current_coupon, coupon_discount_percent, authority, availability "
				   + "FROM   service_members "
				   + "WHERE ";
		
		switch(part) {
		case 1: sql += "member_id LIKE ? "; break;
		case 2: sql += "member_name LIKE ? "; break;
		case 3: sql += "room_number LIKE ? "; break;
		case 4: sql += "current_coupon LIKE ? "; break;
		case 5: sql += "authority LIKE ? "; break;
		case 6: sql += "availability LIKE ? "; break;
		}
		sql += "ORDER  BY member_id";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			value = "%" + value + "%";
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result += resultPlus(rs, 8);
			}
			rs.close();
			pstmt.close();
			disconnect();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return result;
	}
	
	//존재하는 id (primary key) 확인 메소드
	boolean isExist(String id) {
		String sql = "SELECT member_id FROM service_members "
				   + "WHERE  member_id = ?";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.close();
				pstmt.close();
				disconnect();
				return true;
			}
			rs.close();
			pstmt.close();
			disconnect();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return false;
	}
	
	//방번호로 등록가능 여부 확인 메소드
	boolean isJoinable(String roomNumber) {
		String sql = "SELECT s.member_id "
				   + "FROM   service_members s JOIN reservations r ON s.member_id = r.reserve_id "
				   + "WHERE  r.room_number = ?";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.close();
				pstmt.close();
				disconnect();
				return false;
			}
			rs.close();
			pstmt.close();
			disconnect();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return false;
	}
	
	//쿠폰입력 메소드
	void setCoupon(String id, String couponName, int discountP) {
		String sql = "UPDATE service_members "
		           + "SET    current_coupon = ?, "
		           + "       coupon_discount_percent = ? ";
		
		//번호 입력 안 할 시 일괄 적용
		if(id == null) {
			sql += "WHERE  availability = 'y'";
		} else {
			sql += "WHERE  member_id = ?";
		}
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, couponName);
			pstmt.setInt(2, discountP);
			if(id != null) {
				pstmt.setString(3, id);				
			}
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	//로그인 메소드(가용여부 y인 경우만 로그인 가능)
	ServiceMember login(String roomNumber, String password) {
		ServiceMember svmem = null;
		String sql = "SELECT member_id, member_name, authority, current_coupon, coupon_discount_percent "
				   + "FROM   service_members "
	               + "WHERE  room_number = ? "
	               + "  AND  password = ? "
				   + "  AND  availability = 'y'";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNumber);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				svmem = new ServiceMember();
				svmem.setMemberId(rs.getString(1));
				svmem.setMemberName(rs.getString(2));
				svmem.setAuthority(rs.getString(3));
				svmem.setCurrentCoupon(rs.getString(4));
				svmem.setCouponDiscountPercent(rs.getInt(5));
			}
			rs.close();
			pstmt.close();
			disconnect();
			return svmem;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return svmem;
	}
	
}
