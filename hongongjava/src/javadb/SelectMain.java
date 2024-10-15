package javadb;
import java.sql.*;
public class SelectMain {

	public static void main(String[] args) {
		//데이터 조회
		//연결
		Connection conn = null;
		
		//jdbc 등록
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:Oracle:thin:@localhost:1521:xe",
					"java",
					"1234"
					);
			System.out.println("연결 성공");
			
			String sql = "" +
						 "select * from boards "
//					     ＋　"where bwriter = ?"
						 ;
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "글쓴이1"); //select문의 where 조건 있을 시 조건별로 검색 가능
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				//빈 Board 클래스 만들어서 그 안에 select로 값 넣을 수 있음.
				Board bd = new Board();
				bd.setBno(rs.getInt(1));
				bd.setBtitle(rs.getString(2));
				bd.setBcontent(rs.getString(3));
				bd.setBwriter(rs.getString(4));
				bd.setBdate(rs.getDate(5));
				
				System.out.println(bd);
			}
			
//			boolean result = pstmt.execute();
//			if(result) {
//				System.out.println("조회 성공");
//			} else {
//				System.out.println("조회 실패");
//			}
			
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
