package chap03;
import java.util.Scanner;
public class Assign146 {

	public static void main(String[] args) {
		//각종 연산자
		int result = 0;
		result += 10;
		System.out.println(result);
		result -= 5;
		System.out.println(result);
		result *= 3;
		System.out.println(result);
		result /= 5;
		System.out.println(result);
		result %= 3;
		System.out.println(result);
		
		int su1 = 1;
		int su2 = 2;
		System.out.println(su1 & su2); //2진수 계산으로 봤을 때, 01과 10 계산 => 00 (true/false 식과 같음)
		
		su1 = 2;
		su2 = 2;
		System.out.println(su1 & su2); //둘 다 1:true인 경우 1:true (10 10 = 10)
		System.out.println(su1 | su2); //1:true가 어느 쪽이라도 있으면 1:true (10 01 = 11)
		System.out.println(su1 ^ su2); //배타적 논리합(서로 다르면 1:true)
		
		//p150. 4.
		int pencils = 534;
		int students = 30;
		//학생 1 명이 나눠 가지는 연필 개수
		int pencilsPerStudent = pencils / students;
		System.out.println(pencilsPerStudent);
		//남은 연필 개수
		int pencilsLeft = pencils % students;
		System.out.println(pencilsLeft);
		
		//p151. 9.
		Scanner scanner = new Scanner(System.in);
		System.out.print("첫 번째 수: ");
		double num1 = scanner.nextDouble();
		System.out.print("\n두 번째 수: ");
		double num2 = scanner.nextDouble();
		if(num2 == 0.0) {
			System.out.println("결과:무한대");
		}
		System.out.println("결과:" + (num1 / num2));
		scanner.close();	
		
	}

}
