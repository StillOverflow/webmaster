package javadb;
import java.sql.*;
public class UpdateMain {

	public static void main(String[] args) {
		//연결하기
		Connection conn = null;
		
		try {
			//jdbc 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//연결
			conn = DriverManager.getConnection(
					"jdbc:Oracle:thin:@localhost:1521:xe",
					"java",
					"1234"
					);
			System.out.println("연결 성공");
			
			//데이터 수정
			String sql = "" +
						"update boards " +
						"set btitle = ?, bcontent = ?, bwriter = ? " +
						"where bno = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno", "btitle", "bwriter"});
			pstmt.setString(1, "추운 날이네요");
			pstmt.setString(2, "날이 추운데 감기 조심하세요.");
			pstmt.setString(3, "겨울날씨");
			pstmt.setInt(4, 4); //테이블 생성 시 bno의 데이터타입을 NUMBER로 지정했으므로 int.
			
			int rows = pstmt.executeUpdate();
			System.out.println("수정된 갯수 : " + rows);
//			if(rows == 1) { //UPDATE에서 안 될 수 있음
//				ResultSet rs = pstmt.getGeneratedKeys();
//				if(rs.next()) {
//					int bno = rs.getInt("bno");
//					String btitle = rs.getString(1);
//					String bwriter = rs.getString(3);
//					System.out.println(bno + btitle + bwriter);
//				}				
//			} else {
//				System.out.println("수정 실패");
//			}
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
					System.out.println("연결 종료");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
