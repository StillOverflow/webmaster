package project;

import java.sql.SQLException;

public class ServiceOrderDao extends ProjectDao {
	
	//DAO에 싱글톤 객체 형성
	private static ServiceOrderDao sodao = new ServiceOrderDao();
	private ServiceOrderDao(){};
	static ServiceOrderDao getInstance() {
		return sodao;
	}
	
	//추가 메소드 (Reservation, ServiceMember 객체 정보가 필요함.)
	void order(Reservation rv, ServiceMember smem, ServiceOrder so) {
		String sql_s = "SELECT service_name, service_price "
	    	         + "FROM   services "
			         + "WHERE  service_id = ?";
		
		String sql = "INSERT INTO orders (order_id, order_date, "
				+ "                       name, order_room, order_tel, "
				   + "                    service_id, service_name, quantity, service_price, "
				   + "                    used_coupon, required_date, memo) "
				   + "VALUES ('od'|| order_seq.nextval, TO_DATE((SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') "
				   + "                                           FROM   DUAL), 'YYYY-MM-DD HH24:MI'), "
				   + "        ?, ?, ?, "
				   + "        ?, ?, ?, ?, "
				   + "        ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ?)";
				
		connect();
		try {						
			int s_id = so.getServiceId();
			int quan = so.getQuantity();
			
			pstmt = conn.prepareStatement(sql_s); //서비스명과 가격 정보 가져오기
			pstmt.setInt(1, s_id);
			rs = pstmt.executeQuery();
			rs.next();
			String s_name = rs.getString(1);
			int s_price = rs.getInt(2);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, rv.getRoomNumber());
			pstmt.setInt(4, s_id);
			pstmt.setString(5, s_name);
			pstmt.setInt(6, quan);
			pstmt.setString(8, smem.getCurrentCoupon() + smem.getCouponDiscountPercent());
			pstmt.setString(10, so.getMemo());	
			
			//7번째 변수: 가격 계산 (쿠폰 있을 시 적용)
			int oPrice = s_price * quan;
			if(smem.getCurrentCoupon() != null) {
				oPrice *= (1.0 - smem.getCouponDiscountPercent() / 100.0);
				smem.setCurrentCoupon(null);
			}
			pstmt.setInt(7, oPrice);
			
			//null인 경우 디폴트값 적용 항목 : 주문자명, 연락처, 요청시간
			if(so.getName() == null) { 
				pstmt.setString(1, smem.getMemberName());
			} else {
				pstmt.setString(1, so.getName());
			}
			if(so.getOrderTel() == null) {
				pstmt.setString(3, rv.getGuestTel());
			} else {
				pstmt.setString(3, so.getOrderTel());
			}
			if(so.getRequiredDate() == null) {
				pstmt.setString(9, rv.getCheckinDate());
			} else {
				pstmt.setString(9, so.getRequiredDate());
			}
			
			pstmt.execute();
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	//수정 메소드
	String update(String id, ServiceOrder so) {
		String strPart = "";
		String sql = "UPDATE orders SET ";
		
		//입력된 값에 따라 쿼리 추가
		if(so.getOrderId() != null) {
			sql += " order_id = '" + so.getOrderId() + "',"; 
			strPart += "주문번호/";
		}
		if(so.getOrderDate() != null) {
			sql += " order_date = TO_DATE('" + so.getOrderDate() + "', 'YYYY-MM-DD HH24:MI'),"; 
			strPart += "주문일자/";
		}
		if(so.getName() != null) {
			sql += " name = '" + so.getName() + "',"; 
			strPart += "주문자명/";
		}
		if(so.getOrderTel() != null) {
			sql += " order_tel = '" + so.getOrderTel() + "',"; 
			strPart += "연락처/";
		}
		if(so.getRequiredDate() != null) {
			sql += " required_date = TO_DATE('" + so.getRequiredDate() + "', 'YYYY-MM-DD HH24:MI'),"; 
			strPart += "요청시간/";
		}
		if(so.getMemo() != null) {
			sql += " memo = '" + so.getMemo() + "',"; 
			strPart += "비고/";
		}
		
		connect();
		try {		
			//수량 변경 시 가격 자동 계산
			int quan = so.getQuantity();
			if(quan != 0) {
				String sql_setPrice = "UPDATE orders "
						            + "SET    service_price = ? * (SELECT s.service_price "
					             	+ "                            FROM   services s JOIN orders o ON s.service_id = o.service_id "
					              	+ "                            WHERE  o.order_id = ?) "
						            + "WHERE  order_id = ?";
				pstmt = conn.prepareStatement(sql_setPrice);
				pstmt.setInt(1, quan);
				pstmt.setString(2, id);
				pstmt.setString(3, id);
				pstmt.execute();
				
				sql += " quantity = '" + so.getQuantity() + "',"; 
				strPart += "주문수량 및 요금/";
			}
			
			//처리여부 변경 시 completed_date = sysdate 자동 입력
			String complete = so.getCompletedYn();
			if(complete != null) {
				if(complete.equals("y")) {
					String sql_setDate = "UPDATE orders "
							           + "SET    completed_date = TO_DATE( "
							           + "                                (SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') "
							           + "                                FROM DUAL), 'YYYY-MM-DD HH24:MI') "
							           + "WHERE  order_id = ?";
					pstmt = conn.prepareStatement(sql_setDate);
					pstmt.setString(1, id);
					pstmt.execute();
				}
				
				sql += " completed_yn = '" + so.getCompletedYn() + "',"; 
				strPart += "처리여부/";
			}
			
			sql = sql.substring(0, sql.length()-1);	//끝자리 , 제거
			strPart = strPart.substring(0, strPart.length()-1);	//끝자리 / 제거
			sql += " WHERE order_id = ?";
			
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
		String sql = "DELETE orders "
				   + "WHERE  order_id = ?";
		
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
		String sql = "SELECT order_id, TO_CHAR(order_date, 'YYYY-MM-DD HH24:MI'), name, order_room, order_tel, "
				   + "       service_id, service_name, quantity, service_price, "
				   + "       used_coupon, TO_CHAR(required_date, 'YYYY-MM-DD HH24:MI'), memo, "
				   + "       completed_yn, TO_CHAR(completed_date, 'YYYY-MM-DD HH24:MI') "
				   + "FROM   orders "
				   + "ORDER  BY completed_yn, required_date, order_id";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result += resultPlus(rs, 14);
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
	String search(int part, String value, Reservation rv) {
		String result = "";
		String sql = "SELECT order_id, TO_CHAR(order_date, 'YYYY-MM-DD HH24:MI'), name, order_room, order_tel, "
				   + "       service_id, service_name, quantity, service_price, "
				   + "       used_coupon, TO_CHAR(required_date, 'YYYY-MM-DD HH24:MI'), memo, "
				   + "       completed_yn, TO_CHAR(completed_date, 'YYYY-MM-DD HH24:MI') "
				   + "FROM   orders "
				   + "WHERE  ";
		
		switch(part) {
		case 1: sql += "order_id LIKE ? "; break;
		case 2: sql += "order_date BETWEEN TO_DATE(?, 'YYYY-MM-DD') "
		             + "           AND TO_DATE(?, 'YYYY-MM-DD') + 0.999999 "; break;
		case 3: sql += "name LIKE ? "; break;
		case 4: sql += "order_room LIKE ? "; break;
		case 5: sql += "order_tel LIKE ? "; break;
		case 6: sql += "service_name LIKE ? "; break;
		case 7: sql += "used_coupon LIKE ? "; break;
		case 8: sql += "required_date BETWEEN TO_DATE(?, 'YYYY-MM-DD') "
			         + "                  AND TO_DATE(?, 'YYYY-MM-DD') + 0.999999 "; break;
		case 9: sql += "completed_yn LIKE ? "; break;
		case 10: sql += "completed_date BETWEEN TO_DATE(?, 'YYYY-MM-DD') "
		              + "                  AND TO_DATE(?, 'YYYY-MM-DD') + 0.999999 "; break;
		}
		
		//유저 인터페이스에서 사용할 경우
		if(rv != null) {
			sql += "AND order_room = '" + rv.getRoomNumber() + "' "
			     + "AND required_date BETWEEN TO_DATE('" + rv.getCheckinDate() + "', 'YYYY-MM-DD HH24:MI') "
			     + "                      AND TO_DATE('" + rv.getCheckoutDate() + "', 'YYYY-MM-DD HH24:MI') ";
		}
		
		sql += "ORDER  BY completed_yn, required_date, order_id";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			if(part != 2 & part != 8 & part != 10) {
				value = "%" + value + "%";
				pstmt.setString(1, value);
			} else {
				pstmt.setString(1, value);
				pstmt.setString(2, value);
			}
			
			rs = pstmt.executeQuery();
			int count = 0;
			int sum = 0;
			while(rs.next()) {
				result += resultPlus(rs, 14);
				sum += rs.getInt(9);
				count++;
			}
			result += "전체>\t총 " + count + "건 " + sum + "원";
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
	
	//primary key (주문번호) 확인 및 객체 반환 메소드
	ServiceOrder isExist(String id) {
		ServiceOrder so = null;
		String sql = "SELECT order_id, TO_CHAR(order_date, 'YYYY-MM-DD HH24:MI'), name, order_tel, "
				   + "       quantity, TO_CHAR(required_date, 'YYYY-MM-DD HH24:MI'), memo, completed_yn "
				   + "FROM   orders "
				   + "WHERE  order_id = ?";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				so = new ServiceOrder();
				so.setOrderId(rs.getString(1));
				so.setOrderDate(rs.getString(2));
				so.setName(rs.getString(3));
				so.setOrderTel(rs.getString(4));
				so.setQuantity(rs.getInt(5));
				so.setRequiredDate(rs.getString(6));
				so.setMemo(rs.getString(7));
				so.setCompletedYn(rs.getString(8));
			}
			rs.close();
			pstmt.close();
			disconnect();
			return so;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return so;
	}
	
}
