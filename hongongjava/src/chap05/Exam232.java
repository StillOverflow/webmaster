package chap05;
import java.util.Scanner;
public class Exam232 {

	public static void main(String[] args) {
		//p232. 2. LoginResult 열거 타입 변수 활용
		Scanner scan = new Scanner(System.in);
		String id = "user";
		String pw = "password";
		
		LoginResult result = LoginResult.FAIL_ID;
		while(result != LoginResult.SUCCESS) {
			System.out.print("아이디를 입력하세요.> ");
			String input_id = scan.nextLine();
			System.out.print("비밀번호를 입력하세요.> ");
			String input_pw = scan.nextLine();
			boolean id_c = input_id.equals(id);
			boolean pw_c = input_pw.equals(pw);
			if(id_c & pw_c) result = LoginResult.SUCCESS;
			if(id_c == false) {
				result = LoginResult.FAIL_ID;
				System.out.print("아이디 오류\n");
			}
			if(pw_c == false) {
				result = LoginResult.FAIL_PASSWORD;
				System.out.print("비밀번호 오류\n");
			}
		}
		System.out.println("로그인 성공");
		scan.close();	
	}

}
