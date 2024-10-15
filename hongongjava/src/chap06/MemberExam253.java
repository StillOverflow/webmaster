package chap06;

public class MemberExam253 {

	public static void main(String[] args) {
		Member member1 = new Member();
		System.out.printf("이름: %s, 나이: %d\n", member1.name, member1.age);
		
		member1.name = "최하얀";
		member1.age = 23;

		System.out.printf("이름: %s, 나이: %d\n", member1.name, member1.age);
		
		Member member2 = new Member("홍길동", "hong");
		System.out.printf("이름: %s, id: %s", member2.name, member2.id);
	}

}
