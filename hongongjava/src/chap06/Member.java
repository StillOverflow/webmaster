package chap06;

public class Member {
	//필드
	String name;
	String id;
	String password;
	int age;
	
	//생성자 public Member~~~ 안 쓰면 자동생성
	//명시적으로 모두 초기화
	Member(){
		
	}
	
	Member(String name, String id){
		this.name = name;
		this.id = id;
	}
	//메소드
}
