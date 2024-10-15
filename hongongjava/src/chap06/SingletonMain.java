package chap06;

public class SingletonMain {

	public static void main(String[] args) {
		//싱글톤 사용 시 객체 생성(new) 불가 => 메소드 호출해서 객체를 받아와야 함.
		Singleton st = Singleton.getInstance();
		System.out.println(st);
		
		Singleton st2 = Singleton.getInstance();
		
		if(st == st2) { //주소가 같음.
			System.out.println("같은 객체");
		} else {
			System.out.println("다른 객체");
		}
		
		System.out.println(st.name);
		st.name = "김자바"; //getInstance로 값을 읽어와서 사용하는 동안은 변경 가능.
		System.out.println(st.name);
		
	}

}