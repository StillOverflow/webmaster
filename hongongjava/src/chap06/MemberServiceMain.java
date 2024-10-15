package chap06;

import java.util.Scanner;

public class MemberServiceMain {

	public static void main(String[] args) {
		//객체 생성
		Scanner scan = new Scanner(System.in);
		MemberService service = new MemberService();
		String id;
		boolean login_value;
		while(true) {
			System.out.print("아이디 입력> ");
			id = scan.nextLine();
			System.out.print("비밀번호 입력> ");
			String pw = scan.nextLine();
			login_value = service.login(id, pw);
			if(login_value) {
				System.out.printf("%s 로그인 되었습니다.\n", id);
				break;
			} else {
				System.out.println("아이디 또는 비밀번호가 존재하지 않습니다.");
			}			
		}
		System.out.print("로그아웃 하시겠습니까?(y,n)> ");
		String answer = scan.nextLine();
		if(answer.equals("y")) {
			System.out.print("아이디 입력> ");
			login_value = false;
			service.logout(id);
		}
		scan.close();	
	}

}
