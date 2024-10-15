package chap06;

public class Singleton {
	//전체 프로그램에서 하나의 객체만 존재하게 하는 코딩 기법
	//생성자 접근 불가, 필드 접근 불가
	//class로 접근해야 함.
	
	//필드
	private static Singleton st = new Singleton();
	String name = "홍길동";
	
	//생성자
	Singleton(){}
	
	//메소드
	static Singleton getInstance() {
		return st;
	}
	
}
