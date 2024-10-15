package chap13;
import java.util.*;
public class MemberMain {

	public static void main(String[] args) {
		Set<Member> set = new HashSet<>();
		
		set.add(new Member("홍길동", 25));
		set.add(new Member("홍길당", 30));
		set.add(new Member("홍길덕", 33));
		set.add(new Member("황길동", 25));
		set.add(new Member("황길동", 30));
		set.add(new Member("홍길덕", 33)); //Override로 hashCode, equals 재정의 후 안 들어감.
		//new 하면 새로운 객체 참조주소를 가지므로 재정의 없이 set 내부 객체의 데이터까지 비교 못함.
		//클래스에 hashCode, equals 메소드 재정의하면 데이터까지 비교 가능.
		System.out.println(set.size());
		
		//Set 전체출력
		Iterator<Member> iterator = set.iterator(); //set의 타입으로 선언해야 함.
		while(iterator.hasNext()) {
			Member member = iterator.next();
			System.out.println(member.hashCode());
			System.out.println(member.name + " " +member.age);
		}
		
		//equals를 재정의했기에 원래는 다른 객체지만 재정의 후 같아짐.
		Member member1 = new Member("김자바", 20);
		Member member2 = new Member("김자바", 20);
		System.out.printf("member1과 member2는 같은가? %b", member1.equals(member2));
		
	}

}
