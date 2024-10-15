package chap13;

public class Board {
	//VectorMain 사용
	String subject;
	String content;
	String writer;
	
	public Board() {
		
	}
	//p603 BoardDao용 생성자
	public Board(String subject, String content){
		this.subject = subject;
		this.content = content;
	}
	
	public Board(String subject, String content, String writer){
		this.subject = subject;
		this.content = content;
		this.writer = writer;
	}
	
	@Override
	public String toString() { //기본적으로 Object 클래스 상속하기 때문에 가지고 있음.
		// TODO Auto-generated method stub
		return subject + content + writer; //재정의하여 사용 가능
	}
	
	//p603 BoardDao용 생성자
	public String getTitle() {
		return subject;
	}
	public String getContent() {
		return content;
	}

}
