package chap13;

public class Member {
	public String name;
	public int age;
	
	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) { //재정의(Set에 정의한 Member 중 같은지)
		// TODO Auto-generated method stub
		if(obj instanceof Member) {
			Member member = (Member) obj;
			return member.name.equals(name) && member.age==age;
		} else {
			return false;			
		}
	}
	
	@Override
	public int hashCode() { //해시코드 재정의
		return name.hashCode() + age;
	}
}
