package chap04;
import java.util.Scanner;
import java.text.DecimalFormat; //text 포맷 설정 클래스
public class Exam173 {

	public static void main(String[] args) {
		//50에서 100까지 3의 배수 합 구하기
		//3의 배수 출력, 마지막에 3의 배수합 출력
		
		System.out.print("50에서 100까지의 3의 배수 : ");
		int sum = 0;
		int count = 0;
		for(int i = 50; i <= 100; i++) {
			if(i % 3 == 0) {
				sum += i;
				count++;
				System.out.print(i + " ");
			}
		}
		System.out.println("\n3의 배수 합 : " + sum);
		System.out.println("3의 배수 개수 : " + count);
		
		//1에서 n까지의 합이 300이 넘는 순간의 n 값 구하기
		//7의 배수의 합이 300이 넘는 순간의 7의 배수 찾기
		//while문 이용
		sum = 0;
		int n = 1;
		while(sum <= 300) {
			sum += n;
			n++;
		}
		System.out.println("합이 300이 넘는 순간의 n : " + n);
		sum = 0;
		n = 0;
		while(sum <= 300) {
			n += 7;
			System.out.printf("%d+%d=", sum, n);
			sum += n;
			System.out.printf("%d\n", sum);
		}
		System.out.println("합이 300이 넘는 순간의 7의 배수 : " + n);
		
		//p175 구구단 : 중첩 for 문
		Scanner scan = new Scanner(System.in);
//		System.out.println("구구단을 출력하려면 아무 글자나 입력하세요. > ");
//		String answer = scan.nextLine();
//		if(answer != "") {
//			for(int i = 1; i <= 9; i++) {
//				System.out.println("\n=== " + i + "단 ===");
//				for(int j = 1; j <= 9; j++) {
//					System.out.printf("%d * %d = %d\n", i, j, i * j);
//				}
//			}			
		//구구단 가로로  출력
		for(int i = 1; i <= 9; i++) {
			System.out.print("\t" + i + "단\t");
		}
		System.out.print("\n");
		for(int j = 1; j <= 9; j++) {
			for(int i = 1; i <= 9; i++) {
//				String mult = String.valueOf(i * j);
//				if(mult.length() == 1) {
//					mult = " " + mult;
//				}
				System.out.printf("    %d * %d = %2d\t", i, j, i * j);
			}
			System.out.print("\n");
		}	
//		}
			
		//p182. 2.
		sum = 0;
		for(int i = 1; i <= 100; i++) {
			if(i % 3 == 0) {
				sum += i;
			}
		}
		System.out.println("1부터 100까지의 3의 배수 총합 : " + sum);
		
		//p183. 3.
		while(true) {
			int dice1 = (int)(Math.random() * 6 + 1);
			int dice2 = (int)(Math.random() * 6 + 1);
			System.out.printf("(%d,%d) ", dice1, dice2);
			if(dice1 + dice2 == 5) {
				System.out.println("주사위의 합이 5에 도달했습니다.");
				break;				
			}
		}
		
		//4.
		sum = 0;
		System.out.println("4x + 5y = 60 의 모든 해 (x,y)");
		for(int x = 1; x <= 10; x++) {
			for(int y = 1; y <= 10; y++) {
				if(x*4 + y*5 == 60) {
					System.out.printf("(%d,%d) \n", x, y);
				}
			}
		}
		
		//5.
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
		
		//6.
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4 - i; j++) {
				System.out.print(" ");
			}
			for(int k = 1; k <= i; k++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
		
		//7.
		DecimalFormat df = new DecimalFormat("#,##0"); //텍스트 포맷 설정
		boolean run = true;
		int balance = 0;
		int money = 0;
		int lastMoney = 0;
		while(run) {
			System.out.println("-------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("-------------------------");
			System.out.print("선택 > ");
			balance = Integer.parseInt(scan.nextLine());
			switch(balance) {
				case 1 : 
					System.out.print("예금액 > "); 
					money = Integer.parseInt(scan.nextLine());
					lastMoney += money;
					System.out.printf("\n%s원 입금되었습니다. 현재 잔고 : %s원\n", df.format(money), df.format(lastMoney)); 
					break;
				case 2 : 
					System.out.print("출금액> "); 
					money = Integer.parseInt(scan.nextLine ());
					if(lastMoney - money >= 0) {
						lastMoney -= money;
						System.out.printf("\n%s원 출금되었습니다. 현재 잔고 : %s원\n", df.format(money), df.format(lastMoney)); 						
					} else {
						System.out.printf("\n잔고가 부족합니다. 현재 잔고 : %s원\n", df.format(lastMoney)); 	
					}
					break;
				case 3 : 
					System.out.printf("\n잔고 > %s원\n", df.format(lastMoney)); 
					break;
				case 4 :
					System.out.println("\n프로그램 종료");
					run = false;
					break;
			}
		}
		scan.close();	
	}

}
