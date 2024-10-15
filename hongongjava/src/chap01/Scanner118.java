package chap01;
import java.util.Scanner; //같은 패키지 안에 없으면 맨 처음에 import 해야함.

public class Scanner118 {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		String strin;
		
		//문자열
		System.out.print("문자열 입력 > ");
		strin = key.nextLine(); //Line은 무조건 문자열로 반환.
		System.out.println("입력 받은 문자열 : " + strin);
		
		//숫자
		System.out.print("숫자 입력 > ");
		int valInt = key.nextInt();
		System.out.println("입력 받은 숫자 : " + valInt);
		strin = key.nextLine(); //빈 줄로 구분
		
		System.out.print("문자열 재입력 > ");
		strin = key.nextLine();
		System.out.println("재입력 받은 문자열 : " + strin);
		if(strin == "q") { //기본타입으로 바로 만들면 주소 같음. String str1 = "q"; 이런식으로 하면 ==비교 가능
			System.out.println("q 입력됨.");
		} else {
			System.out.println("같지 않음.");
		}
		if(key.equals("q")) { //클래스 타입 객체의 경우, 값 비교하려면 equals 메소드 써야함.(입력받는 것은 값이 아닌 주소이므로)
			System.out.println("q 입력됨.");
		} else {
			System.out.println("같지 않음.");
		}
		
		System.out.print("숫자 재입력 > ");
		valInt = Integer.parseInt(key.nextLine());
		System.out.println("재입력 받은 숫자 + 100 : " + (valInt + 100));
		
		key.close();
	}

}
