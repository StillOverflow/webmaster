package project;

import java.sql.*;

public class ProjectDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//DB연결 메소드
	void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.23:1521:xe",
					"java",
					"1234");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	void disconnect() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	String resultPlus(ResultSet rs, int num) {
		String result = "";
		try {
			for(int i = 1; i <= num; i++) {
				result += rs.getString(i) + "\t";
			}
			result += "\n";
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
