package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	//연결 or 연결 끊기 보유하여 상속해줌
	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver"); //driver
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", //url
					"java",
					"1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
