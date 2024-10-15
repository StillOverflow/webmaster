package book_manager;

import java.sql.*;

public class BookDao extends DAO {
	//메소드
	//1. 연결 설정 메소드 (void)
	//2. 연결 끊기 메소드 (void)
	//3. insert, select(조건에 따라), delete 메소드 등
	
	//DAO는 싱글톤 방식으로 하면 '다중 사용 환경'에서 보안 및 메모리 아낄 수 있음(new 남발 제한)
	private static BookDao instance = new BookDao();
	private BookDao() {}
	static BookDao getInstance() {
		return instance;
	}

	//연결 or 연결 끊기
	Connection conn = null;
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
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
	
	//데이터 조작
	//DAO이기 때문에 결과는 메인에서만 확인
	public int insert(String title, String writer, int price) {
		connect();
		String sql = "insert into books(bnum, title, writer, price) " +
	                 "values (seq_book.nextval, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setInt(3, price);
			int rows = pstmt.executeUpdate();
			pstmt.close();
			disconnect();
			System.out.println("도서가 등록되었습니다.");
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return 0;
	}
	
	public int update(String bnum, int part, String value) {
		connect();
		String part_str= "";
		String sql = "update books ";
		switch(part) {
		case 1 : sql += "set title = ? "; part_str = "제목"; break;
		case 2 : sql += "set writer = ? "; part_str = "저자"; break;
		case 3 : sql += "set price = ? "; part_str = "가격"; break;
		case 4 : sql += "set bnum = ? "; part_str = "번호"; break;
		}
		sql += "where bnum = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.setString(2, bnum);
			int rows = pstmt.executeUpdate();
			pstmt.close();
			disconnect();
			System.out.printf("%s번 도서의 %s 정보가 변경되었습니다.\n", bnum, part_str);
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return 0;
	}
	
	public int delete(String bnum) {
		connect();
		String sql = "delete books " +
			         "where bnum = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bnum);
			int rows = pstmt.executeUpdate();
			pstmt.close();
			disconnect();
			System.out.println(bnum + "번 도서가 삭제되었습니다.");
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return 0;
	}
	
	//select(전체 조회) 메소드
	public void selectAll() {
		connect();
//		String result = "";
		String sql = "select title, writer, price, bnum from books " +
	                 "order by bnum";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setWriter(rs.getString(2));
				book.setPrice(rs.getInt(3));
				book.setBnum(rs.getString(4));
				System.out.println(book.toString());
//				result += book.toString() + "\n";
			}
			pstmt.close();
			disconnect();
//			return result;
		} catch (SQLException e) {
			e.printStackTrace();
//			return result;
		}
		disconnect();
	}
	
	//select(선택 조회) 메소드
	public void select(int part, String value) {
		connect();
		String sql = "select title, writer, price, bnum from books ";
		switch(part) {
		case 1 : sql += "where title like ? "; break;
		case 2 : sql += "where writer like ? "; break;
		case 3 : sql += "where bnum like ? "; break;
		}
		sql += "order by bnum";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + value + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setWriter(rs.getString(2));
				book.setPrice(rs.getInt(3));
				book.setBnum(rs.getString(4));
				System.out.println(book.toString());
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	public boolean isThere(String bnum) {
		connect();
		String sql = "select bnum from books " + //끝에 띄우지 않으면 오류남.
					 "where bnum = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bnum);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return false;
	}
	
}
