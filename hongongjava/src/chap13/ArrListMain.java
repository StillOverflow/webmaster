package chap13;
//Ctrl + Shift + O 누르면 import문 자동 생성

import java.util.List;
import java.util.ArrayList;

public class ArrListMain {

	public static void main(String[] args) {
		//p582
		//List 컬렉션
		//List 인터페이스의 ArrayList 클래스 다루는 법
		//(Vector : ArrayList와 유사하지만 멀티 스레드 환경에서 안전함(동시에 실행하지 않고, 잠궜다가 메소드 스레드 끝나고 다음 스레드 실행))
		List<String> list = new ArrayList<String>();
		
		//객체 추가
		list.add("Java");
		list.add("JDBC");
		list.add(1, "Sevlet/JSP");
		list.add("Database");
		list.add(3, "iBATIS");
		
		//리스트 객체 가져와서 출력하기
		System.out.println(list.size()); //저장된 총 객체 수
		System.out.println(list.get(1));
		System.out.println("===list 전체 출력===");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(i + "번: " + list.get(i));
		}
		
		//객체 삭제
		list.remove(1);
		System.out.println("===list 전체 출력 (향상된 for문)===");
		int i = 0;
		for(String str : list) {
			System.out.println(i + "번: " + str);
			i++;
		}
		

	}

}
