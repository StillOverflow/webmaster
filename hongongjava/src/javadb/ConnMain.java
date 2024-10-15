package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnMain {

	public static void main(String[] args) {
		//oracle(sql)과 database 연결
		//jdbc 등록
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver"); //try-catch문 필요
			//db 연결
			conn = DriverManager.getConnection( //try-catch문 필요
					"jdbc:oracle:thin:@localhost:1521:xe", //경로url 삽입
					"java",
					"1234"
					);
			 System.out.println("연결 성공");
			 //db 연결 이후 사용 가능
		} catch (ClassNotFoundException e) {
			//catch문 안에 "연결 끊어짐" 넣으면 오류내용을 알 수 없으므로, 꼭 오류내용 출력(printStackTrace) 해야 함.
			e.printStackTrace(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null ) {
				try {
					conn.close(); //db 다 쓴 뒤에 꼭 연결 끊어줘야 함.
					System.out.println("연결 끊어짐");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
