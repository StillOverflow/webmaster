package chap06;

public class Student {
	//필드(속성)
//	String stNo;
//	String name;
//	
//	//생성자(객체 생성) => 객체 생성 시 (Student 쓸 때)초기화
//	//메소드와 달리 return타입이 없으므로 기능이 딱히 없는 함수라 볼 수 있음.
//	//class 이름과 생성자 이름은 꼭 같아야 함.
////	Student(String stNo, String name){
////		this.stNo = stNo;
////		this.name = name;
////	}
//	Student(String no, String n){
//		stNo = no;
//		name = n;
//	} //이름 달라 식별 가능하면 this 안 넣어도 됨.
//	
//	//메소드(기능)
//	void study() {
//		System.out.println("공부합니다.");
//	}
	
	//수업시간 문제 풀이(240927)
	String stNum;
	String name;
	int sub_lang;
	int sub_eng;
	int sub_math;
	
	Student(String stNum, String name, int sub_lang, int sub_eng, int sub_math){
		this.stNum = stNum;
		this.name = name;
		this.sub_lang = sub_lang;
		this.sub_eng = sub_eng;
		this.sub_math = sub_math;
	}
	
	int sum() {
		return sub_lang + sub_eng + sub_math;
	}
	
	double avg() {
		return sum() / 3.0;
	}
	
	String rank() {
		switch((int)avg() / 10) {
		case 10 : return "A+";
		case 9 : return "A"; 
		case 8 : return "B+";
		case 7 : return "B";
		case 6 : return "C";
		default : return "D";
		}
	}
}
