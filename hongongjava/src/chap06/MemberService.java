package chap06;

public class MemberService {
	//p290. 3.
	//필드
	//생성자
	//메소드
	boolean login(String id, String pw) {
		if(id.equals("hong") & pw.equals("12345")) return true;
		else return false;
	}
	
	void logout(String id) {
		System.out.printf("%s 로그아웃 되었습니다.\n", id);
	}

}
