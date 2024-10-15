package project;

import java.sql.SQLException;

public class ReservationDao extends ProjectDao {
	
	//DAO에 싱글톤 객체 형성
	private static ReservationDao rvdao = new ReservationDao();
	private ReservationDao(){};
	static ReservationDao getInstance() {
		return rvdao;
	}
	
	//추가 메소드
	void reserve(Reservation rv) {
		String sql = "INSERT INTO reservations "
	               + "            (reserve_id, room_number, guest_name, guest_number, guest_tel, "
				   + "             checkin_date, checkout_date) "
				   + "VALUES ('rv' || reserve_seq.nextval, ?, ?, ?, ?, "
				   + "        TO_DATE(?, 'YYYY-MM-DD HH24:MI'), TO_DATE(?, 'YYYY-MM-DD HH24:MI'))";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rv.getRoomNumber());
			pstmt.setString(2, rv.getGuestName());
			pstmt.setInt(3, rv.getGuestNumber());
			pstmt.setString(4, rv.getGuestTel());
			pstmt.setString(5, rv.getCheckinDate());
			pstmt.setString(6, rv.getCheckoutDate());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	//수정 메소드
	String update(String id, Reservation rv) {
		String strPart = "";
		String sql = "UPDATE reservations SET ";
		
		//입력된 값에 따라 쿼리 추가
		if(rv.getGuestName() != null) {
			sql += " guest_name = '" + rv.getRoomNumber() + "',"; 
			strPart += "예약자명/";
		}
		if(rv.getGuestNumber() != 0) {
			sql += " guest_number = '" + rv.getGuestNumber() + "',"; 
			strPart += "예약인원/";
		}
		if(rv.getGuestTel() != null) {
			sql += " guest_tel = '" + rv.getGuestTel() + "',"; 
			strPart += "연락처/";
		}
		if(rv.getCheckinDate() != null) {
			sql += " checkin_date = TO_DATE('" + rv.getCheckinDate() + "', 'YYYY-MM-DD HH24:MI'),"; 
			strPart += "체크인 날짜/";
		}
		if(rv.getCheckoutDate() != null) {
			sql += " checkout_date = TO_DATE('" + rv.getCheckoutDate() + "', 'YYYY-MM-DD HH24:MI'),"; 
			strPart += "체크아웃 날짜/";
		}
			
		connect();
		try {		
			//동기화
			//reservations <=> service_members 아이디와 방번호는 서로 같음.
			String value_id = rv.getReserveId();
			String value_room = rv.getRoomNumber();
			
			if(value_id != null) {
				String sql_syncId = "UPDATE service_members SET member_id = ? WHERE member_id = ?";
				pstmt = conn.prepareStatement(sql_syncId);
				pstmt.setString(1, value_id);
				pstmt.setString(2, id);
				pstmt.execute();
				
				sql += " reserve_id = '" + value_id + "',"; 
				strPart += "예약번호/";
			}
			
			if(value_room != null) {
				String sql_syncRoom = "UPDATE service_members SET room_number = ? WHERE member_id = ?";
				pstmt = conn.prepareStatement(sql_syncRoom);
				pstmt.setString(1, value_room);
				pstmt.setString(2, id);
				pstmt.execute();
				
				sql += " room_number = '" + value_room + "',"; 
				strPart += "방번호/";
			}
			
			sql = sql.substring(0, sql.length()-1);	//끝자리 , 제거
			strPart = strPart.substring(0, strPart.length()-1);	//끝자리 / 제거
			sql += " WHERE reserve_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
		String sql = "DELETE reservations "
				   + "WHERE  reserve_id = ?";
		
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
		String sql = "SELECT reserve_id, guest_name, room_number, guest_number, guest_tel, "
				   + "       TO_CHAR(checkin_date, 'YYYY-MM-DD HH24:MI'), "
				   + "       TO_CHAR(checkout_date, 'YYYY-MM-DD HH24:MI'), "
				   + "       service_yn "
				   + "FROM   reservations "
				   + "ORDER  BY checkin_date, reserve_id";
		
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
		String sql = "SELECT reserve_id, guest_name, room_number, guest_number, guest_tel, "
				   + "       TO_CHAR(checkin_date, 'YYYY-MM-DD HH24:MI'), "
				   + "       TO_CHAR(checkout_date, 'YYYY-MM-DD HH24:MI'), "
				   + "       service_yn "
				   + "FROM   reservations "
				   + "WHERE ";
		
		switch(part) {
		case 1: sql += "reserve_id LIKE ? "; break;
		case 2: sql += "room_number LIKE ? "; break;
		case 3: sql += "guest_name LIKE ? "; break;
		case 4: sql += "guest_number LIKE ? "; break;
		case 5: sql += "guest_tel LIKE ? "; break;
		case 6: 
			    sql += "checkin_date <= TO_DATE(? || ' 23:59', 'YYYY-MM-DD HH24:MI') "
					 + "AND checkout_date >= TO_DATE(? || ' 00:00', 'YYYY-MM-DD HH24:MI') "; 
			    break;		
		case 7: sql += "service_yn LIKE ? "; break;
		}
		
		sql += "ORDER  BY checkin_date, reserve_id";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			if(part == 6) {
				pstmt.setString(1, value);
				pstmt.setString(2, value);
			} else {
				value = "%" + value + "%";
				pstmt.setString(1, value);
			}
			
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
	
	//예약여부 확인 메소드
	boolean isReserved(String roomNumber, String ckinDate, String ckoutDate, String id) {
		String sql_inspect = "SELECT guest_name "
				           + "FROM   reservations "
				           + "WHERE  room_number = ? ";
		
		boolean insertDate = ckinDate != null & ckoutDate != null; //체크인/아웃 날짜 입력했으면 true 반환
		
		if(insertDate) {  //날짜 입력했으면 입력한 조건으로 검색
			sql_inspect += "  AND  checkout_date >= TO_DATE(?, 'YYYY-MM-DD HH24:MI') "
					     + "  AND  checkin_date <= TO_DATE(?, 'YYYY-MM-DD HH24:MI')";
		} else {
			sql_inspect += "  AND  checkout_date >= TO_DATE("
					     + "                        (SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') "
					     + "                         FROM   DUAL), 'YYYY-MM-DD HH24:MI') "
					     + "  AND  checkin_date <= TO_DATE("
				         + "                        (SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') "
					     + "                         FROM   DUAL), 'YYYY-MM-DD HH24:MI') ";
		}
		
		//해당 예약번호는 제외하고 확인 가능
		if(id != null) {
			sql_inspect += "AND reserve_id != ?";
		}
		
		connect();
		try {			
			pstmt = conn.prepareStatement(sql_inspect);
			pstmt.setString(1, roomNumber);
			
			if(insertDate) {
				pstmt.setString(2, ckinDate);
				pstmt.setString(3, ckoutDate);
			}
			if(insertDate & id != null) {
				pstmt.setString(4, id);
			}
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return false;
	}
	
	//예약가능한 방 표시 메소드
	String listRoom(String ckinDate, String ckoutDate) {
		String result = "";
		String sql_inspect = "SELECT guest_name "
				           + "FROM   reservations "
				           + "WHERE  room_number = ? "
				           + "  AND  checkout_date >= TO_DATE(?, 'YYYY-MM-DD HH24:MI') "
						   + "  AND  checkin_date  <= TO_DATE(?, 'YYYY-MM-DD HH24:MI')";
		
		connect();
		try {			
			pstmt = conn.prepareStatement(sql_inspect);
			
			for(int i = 3; i >= 1; i--) {
				for(int j = 1; j <= 3; j++) {
					String room = i + "0" + j;
					pstmt.setString(1, room);
					pstmt.setString(2, ckinDate);
					pstmt.setString(3, ckoutDate);
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						room += "(X)";
					} else {
						room += "(O)";
					}
					result += room + " ";
				}
				result += "\n";
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
	
	//primary key (예약번호) 확인 + 객체 반환 메소드
	Reservation getReservation(String id) {
		Reservation reserve = null;
		String sql_r = "SELECT reserve_id, room_number, guest_name, guest_tel, guest_number, "
				     + "       TO_CHAR(checkin_date, 'YYYY-MM-DD HH24:MI'), "
				     + "       TO_CHAR(checkout_date, 'YYYY-MM-DD HH24:MI'), "
				     + "       service_yn "
		    	     + "FROM   reservations "
				     + "WHERE  reserve_id = ?";
	
		connect();
		try {			
			pstmt = conn.prepareStatement(sql_r);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reserve = new Reservation();
				reserve.setReserveId(rs.getString(1));
				reserve.setRoomNumber(rs.getString(2));
				reserve.setGuestName(rs.getString(3));
				reserve.setGuestTel(rs.getString(4));
				reserve.setGuestNumber(rs.getInt(5));
				reserve.setCheckinDate(rs.getString(6));
				reserve.setCheckoutDate(rs.getString(7));
				reserve.setServiceYn(rs.getString(8));
			}
			rs.close();
			pstmt.close();
			disconnect();
			return reserve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return reserve;
	}
	
}
