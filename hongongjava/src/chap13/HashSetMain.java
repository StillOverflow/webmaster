package chap13;
import java.util.*;
public class HashSetMain {

	public static void main(String[] args) {
		//Set 컬렉션(인터페이스)를 구현한 HashSet
		//순서 상관X, 중복값 허용X, null값은 하나만 허용
		Set<String> set = new HashSet<>(); //HashSet의 <E> 타입 파라미터 생략 시, 왼쪽 Set 따라감.
		
		set.add("Java");
		set.add("JDBC");
		set.add("Servlet/JSP");
		set.add("Java");
		set.add("iBATIS");
		
		int size = set.size();
		System.out.println(size);
		
		Iterator<String> iterator = set.iterator(); //반복자(Iterator 객체) 얻는 메소드 : 전체 객체 대상으로 한 번씩 순회하여 저장.
		while(iterator.hasNext()) { //가져올 객체가 있으면 true or false 로 리턴
			String ele = iterator.next();
			System.out.println(ele);
		}
		
		System.out.println("-------------------------");
		
		set.remove("JDBC");
		set.remove("iBATIS");
		
//		iterator = set.iterator();
		for(String ele : set) { //for문으로 출력하면 간단하지만, 추가/삭제 등 사이즈가 변경되면 안 됨.
			System.out.println(ele);
		}
		
		System.out.println("-------------------------");
		
		set.clear();
		if(set.isEmpty()) {System.out.println("비어있음.");}
		
		
	}

}
