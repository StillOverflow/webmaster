package chap01;
import java.util.Scanner;
public class Exam120 {

	public static void main(String[] args) {
		//1. 출력문 연습
		String name = "감자바";
		int age = 25;
		String tel1= "010", tel2 = "123", tel3 = "4567";
		
		System.out.println("이름: " + name);
		System.out.print("나이: " + age + "\n");
		System.out.printf("전화: %s-%s-%s \n", tel1, tel2, tel3 + "\n");
		
		//2, 3. scanner 이용 방법 연습
		//2.
		Scanner scan = new Scanner(System.in);
		System.out.print("첫 번째 수 > ");
		String strNum1 = scan.nextLine();
		
		System.out.print("두 번째 수 > ");
		String strNum2 = scan.nextLine();
		
//		int num1 = Integer.parseInt(strNum1);
//		int num2 = Integer.parseInt(strNum2);
//		int result = num1 + num2;
//		System.out.println(result);
		
		double num1 = Double.parseDouble(strNum1);
		double num2 = Double.parseDouble(strNum2);
		int result = (int)(num1 + num2);
		System.out.println(result + "\n");
		
		//3.
		System.out.println("1. 이름: ");
		String scanName = scan.nextLine();
		System.out.println("2. 주민번호 앞 6자리: ");
		String scanIdent = scan.nextLine();
		while(scanIdent.length() != 6) {
				System.out.println("다시 입력해주세요.");
				scanIdent = scan.nextLine();
		}
		System.out.println("3. 전화번호: ");
		String scanTel = scan.nextLine();
		System.out.printf("1. 이름: %s\n2. 주민번호 앞 6자리: %s\n3. 전화번호: %s\n\n", scanName, scanIdent, scanTel);

		//두 수를 입력 받아서 큰 수 - 작은 수 출력
		System.out.println("숫자1 입력 > ");
		int scNum1 = scan.nextInt();
		System.out.println("숫자2 입력 > ");
		int scNum2 = scan.nextInt();
		if(scNum1 > scNum2) {
			System.out.println(scNum1 + " - " + scNum2 + " = " + (scNum1 - scNum2));
		} else {
			System.out.println(scNum2 + " - " + scNum1 + " = " + (scNum2 - scNum1));
		}
		scan.close();	
	}

}
