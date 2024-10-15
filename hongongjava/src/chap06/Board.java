package chap06;

public class Board {
	//p267
	//필드
	String title;
	String content;
	String writer;
	String date;
	int hitcount;
	
	//생성자-명시작으로 일단 초기화 후 매개변수 받도록 함.
	Board(){
		
	}
	
	Board(String title, String con){
		this(title, con, "로그인한 회원아이디", "현재 컴퓨터 날짜", 0);
	}
	
	Board(String title, String con, String writer){
		this(title, con, writer, "현재 컴퓨터 날짜", 0);
	}
	
	Board(String title, String con, String writer, String date){
		this(title, con, writer, date, 0);
	}
	
	//공통 실행 코드
	Board(String title, String con, String writer, String date, int hit){
		this.title = title;
		this.content = con;
		this.writer = writer;
		this.date = date;
		this.hitcount = hit;
	}
	
	//메소드
}
