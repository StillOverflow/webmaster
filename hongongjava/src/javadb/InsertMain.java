package javadb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class InsertMain {

	public static void main(String[] args) {
		//데이터 추가하기
		Connection conn = null;
		try {
			//jdbc(db 접속 가능 자바 API) 활용
			//드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//연결
			conn = DriverManager.getConnection(
					"jdbc:Oracle:thin:@localhost:1521:xe",
					"java",
					"1234"
					);
			System.out.println("연결 성공");
			
			//데이터 추가
//			String sql = "delete boards where bno = 2";
			String sql = "" // <<공백은 안 넣어도 상관 없음(가독성 위해 자리 맞추는 것)
						+ "insert into boards (bno, btitle, bcontent, bwriter, bdate) "
						+ "values(seq_bno.nextval, ?, ?, ?, sysdate)";
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno", "btitle"}); //인터페이스 타입
			pstmt.setString(1, "제목1"); //각 ?자리의 인덱스에 데이터 생성
			pstmt.setString(2, "내용1");
			pstmt.setString(3, "글쓴이1");
			System.out.println();
			
			//데이터 추가 실행 후 결과 조사
			//들어간 데이터는 Oracle 테이블에서 확인할 수 있음.
			int rows = pstmt.executeUpdate(); //DML/DDL 결과 조사 (DML(select문)은 (boolean)execute, 나머지는 모두 excuteUpdate)
			if(rows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys(); //ResultSet으로 prepareStatement의 키(지정한 배열) 가져옴.
				if(rs.next()) {
					int bno = rs.getInt(1); //column 번호로 배열의 각 키의 값 가져올 수 있음.
					String btitle = rs.getString(2);
					System.out.println("저장된 번호 : " + bno);
					System.out.println("저장된 제목 : " + btitle);
				}
				System.out.println("추가 성공");
				rs.close();
			} else {
				System.out.println("추가 실패");
			}
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
					System.out.println("연결 끊어짐");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
