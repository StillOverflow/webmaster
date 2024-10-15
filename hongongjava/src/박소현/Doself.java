package 박소현;
import java.util.Scanner;
public class Doself {

	public static void main(String[] args) {
		//1.
		System.out.println("1. 윤년 계산 프로그램");
		Scanner scan = new Scanner(System.in);
		System.out.print("##년도 입력 : ");
		int year = scan.nextInt();
		while(year < 0 | year > 3000) {
			System.out.println("연도를 제대로 입력해주세요.");
			System.out.print("##년도 입력 : ");
			year = scan.nextInt();
		}
		if((year % 4 == 0 & year % 100 != 0) | year % 400 == 0) {
			System.out.println(year + "년은 윤년입니다.");
		} else {
			System.out.println(year + "년은 평년입니다.");
		}

		//2.
		System.out.println("\n2. 동전 교환 프로그램");
		System.out.print("##교환할 금액 : ");
		int money = scan.nextInt();
		int lastMoney = money;
		System.out.printf("500원 짜리 : %d개\n", lastMoney / 500);
		lastMoney %= 500;
		System.out.printf("100원 짜리 : %d개\n", lastMoney / 100);
		lastMoney %= 100;
		System.out.printf("50원 짜리 : %d개\n", lastMoney / 50);
		lastMoney %= 50;
		System.out.printf("10원 짜리 : %d개\n", lastMoney / 10);
		lastMoney %= 10;
		System.out.printf("교환 금액 : %d원\n", money - lastMoney);
		System.out.printf("남은 금액 : %d원\n", lastMoney);
		
		//3.
		System.out.println("\n3. 숫자 추측 게임");
		int ranNum = (int)(Math.random() * 100 + 1);
		System.out.print("숫자 입력 : ");
		int guess = scan.nextInt();
		int count = 1;
		while(ranNum != guess) {
			count++;
			if(ranNum < guess) {
				System.out.println("down하세요!!");
			} else {
				System.out.println("up하세요!!");
			}
			System.out.print("숫자 입력 : ");
			guess = scan.nextInt();
		}
		System.out.printf("정답! 축하합니다! %d번만에 맞추셨습니다.\n", count);
		
		//4.
		System.out.println("\n4. 구구단 프로그램");
		for(int i = 2; i <= 9; i++) {
			System.out.printf("  %d단\t", i);
		}
		System.out.println();
		for(int i = 1; i <= 9; i++) {
			for(int j = 2; j <= 9; j++) {
				System.out.printf("%dx%d=%2d\t", j, i, j * i);
			}
			System.out.println();
		}
		
		//5.
		System.out.println("\n5. 섭씨-화씨 온도 변환 프로그램");
//		double temp = 0;
		int select = 0;
		boolean run = true;
		while(run) {
			System.out.println("--------------");
			System.out.println("1. 화씨 => 섭씨\n2. 섭씨 => 화씨\n3. 종료");
			System.out.println("--------------");
			System.out.print("▶ 번호 선택 : ");
			select = scan.nextInt();
			switch(select) {
				case 1 :
					System.out.print("▶ 화씨 온도 입력 : ");
					System.out.printf("섭씨온도 = %f\n", (double)5 / 9 * (scan.nextDouble() - 32));
					break;
				case 2 :
					System.out.print("▶ 섭씨 온도 입력 : ");

					System.out.printf("화씨온도 = %f\n", (double)9 / 5 * scan.nextDouble() + 32);
					break;
				case 3 :
					System.out.println("program end");
					run = false;
					break;	
			}
		}
			
		//6.
		System.out.print("\n6. 가위, 바위, 보 게임 프로그램");
		while(true) {
			int com_rsp = (int)(Math.random() * 3);
			System.out.print("\n## 가위(0) 바위(1) 보(2) 입력 : ");
			int my_rsp = scan.nextInt();
			if(my_rsp >= 3) break;
			if(com_rsp == my_rsp) {
				System.out.printf("사람 %d, 컴 %d 비겼음", my_rsp, com_rsp);
			} else if (com_rsp != 2 & my_rsp == com_rsp + 1) {
				System.out.printf("사람 %d, 컴 %d 사람 승리", my_rsp, com_rsp);
			} else {
				System.out.printf("사람 %d, 컴 %d 컴 승리", my_rsp, com_rsp);
			}
		}
		System.out.println("\ngame over");
		
		//7.
		System.out.println("\n7. 3, 6, 9 게임 프로그램");
		for(int i = 1; i <= 50; i++) {
			String strI = String.valueOf(i);
			String heart = "";
			for(int idx = 0; idx < strI.length(); idx++) {
				if(strI.charAt(idx) % 3 == 0 & strI.charAt(idx) != '0') heart += "♥";
			}
			if(heart != "") strI = heart;
			System.out.printf("%-2s\t", strI);
			if(i % 10 == 0) System.out.println();
		}
		
		System.out.println("\n7-1. 3, 6, 9 게임 프로그램(문자 대체 버전)");
		for(int i = 1; i <= 50; i++) {
			String strI = String.valueOf(i);
			for(int idx = 0; idx < strI.length(); idx++) {
				if(strI.charAt(idx) % 3 == 0 & strI.charAt(idx) != '0') strI = strI.replace(strI.charAt(idx), '♥');
			}
			System.out.printf("%-2s\t", strI);
			if(i % 10 == 0) System.out.println();
		}
		scan.close();	
	}//main end

}
