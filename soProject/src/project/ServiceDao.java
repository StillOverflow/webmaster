package project;

import java.sql.SQLException;

public class ServiceDao extends ProjectDao {
	
	//DAO에 싱글톤 객체 형성
	private static ServiceDao svdao = new ServiceDao();
	private ServiceDao(){};
	static ServiceDao getInstance() {
		return svdao;
	}
	
	//추가 메소드
	void add(String sName, int sPrice) {
		String sql = "INSERT INTO services (service_id, service_name, service_price) "
				   + "VALUES (service_seq.nextval, ?, ?) ";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			pstmt.setInt(2, sPrice);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	//수정 메소드
	String update(int id, Service serv) {
		String strPart = "";
		String sql = "UPDATE services SET ";
		
		connect();
		try {
			//입력된 값에 따라 쿼리 추가 및 동기화
			//services <=> orders 서비스명과 번호는 서로 같음.
			int value_id = serv.getServiceId();
			String value_name = serv.getServiceName();
			
			if(value_name != null) {
				String sql_syncName = "UPDATE orders "
				                    + "SET    service_name = ? "
				                    + "WHERE  service_id = ?";
				pstmt = conn.prepareStatement(sql_syncName);
				pstmt.setString(1, value_name);
				pstmt.setInt(2, id);
				pstmt.execute();
				
				sql += " service_name = '" + value_name + "',"; 
				strPart += "서비스명/";
			}
			
			if(value_id != 0) {
				String sql_syncId = "UPDATE orders "
						          + "SET    service_id = ? "
						          + "WHERE  service_id = ?";
				pstmt = conn.prepareStatement(sql_syncId);
				pstmt.setInt(1, value_id);
				pstmt.setInt(2, id);
				pstmt.execute();
				
				sql += " service_id = '" + value_id + "',"; 
				strPart += "번호/";
			}
			
			if(serv.getServicePrice() != 0) {
				sql += " service_price = '" + serv.getServicePrice() + "',"; 
				strPart += "요금/";
			}
			
			sql = sql.substring(0, sql.length()-1);	//끝자리 , 제거
			strPart = strPart.substring(0, strPart.length()-1);	//끝자리 / 제거
			sql += " WHERE service_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
	void delete(int id) {
		String sql = "DELETE services "
				   + "WHERE  service_id = ?";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
		String sql = "SELECT service_id, service_name, service_price "
				   + "FROM   services "
				   + "ORDER  BY service_id";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result += resultPlus(rs, 3);
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
	
	//검색 메소드(서비스명)
	String search(int part, String value) {
		String result = "";
		String sql = "SELECT service_id, service_name, service_price "
				   + "FROM   services "
				   + "WHERE  ";
		
		switch(part) {
		case 1:
			sql += "service_id = ? "; break;
		case 2:
			sql += "service_name LIKE ? "
			     + "ORDER  BY service_id"; break;
		}
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			if(part != 1) {
				value = "%" + value + "%";				
			}
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result += resultPlus(rs, 3);
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
	
	//primary key 확인 + 객체 반환 메소드
	Service isExist(int id) {
		Service sv = null;
		String sql = "SELECT service_id, service_name, service_price "
				   + "FROM   services "
				   + "WHERE  service_id = ?";
		
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sv = new Service();
				sv.setServiceId(rs.getInt(1));
				sv.setServiceName(rs.getString(2));
				sv.setServicePrice(rs.getInt(3));
			}
			rs.close();
			pstmt.close();
			disconnect();
			return sv;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return sv;
	}
	
	//일괄 할인율 적용: (-10)(%) 형식으로 입력
//	void discountAll(int Percentage) {
//		String sql = "UPDATE services "
//				   + "SET    service_price = service_price * (1 + ? / 100)";
//		
//		connect();
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, Percentage);
//			pstmt.execute();
//			
//			pstmt.close();
//			disconnect();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		disconnect();
//	}

	
}
