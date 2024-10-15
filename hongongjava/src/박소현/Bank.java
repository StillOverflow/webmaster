package 박소현;
import java.util.Scanner;
import java.util.InputMismatchException; //예외처리용

public class Bank {
	
	//은행 계좌 객체 생성을 위한 Account 클래스
	private static class Account{
		private String ano;
		private String owner;
		private int balance;
		private static final int MIN_BALANCE = 0;
		private static final int MAX_BALANCE = 1000000;
		
		public String getAno() {
			return ano;
		}
		public void setAno(String ano) {
			this.ano = ano;
		}
		public String getOwner() {
			return owner;
		}
		public void setOwner(String owner) {
			this.owner = owner;
		}
		public int getBalance() {
			return balance;
		}
		public boolean setBalance(int balance) {
			int mod = this.balance + balance;
			if(mod >= MIN_BALANCE & mod <= MAX_BALANCE) {
				this.balance += balance;
				return true;
			} else return false;
		}
	}
	
	//계좌번호 존재 여부 확인 메소드
	private static class Exist{
		private static int idx; //존재하는 배열의 인덱스
		
		public static int getIdx() {
			return idx;
		}
		
		public static boolean isExist(String ano, Account[] accs) {
			for(int i = 0; i < 100; i++) {
				if(accs[i] != null) {
					if(ano.equals(accs[i].getAno())) {
						idx = i;
						return true;
					}						
				}
			} return false;
		}
		
	}
	
	//입력값이 숫자가 아닌 경우 예외처리 메소드
	private static int isInt(Scanner scan) {
		int number = 0;
		
		try {
			number = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.print("[오류] 숫자를 입력해주세요.> ");
			scan.nextLine();
			return isInt(scan);
		}
		return number;
	}

	//메인 실행 메소드
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Account[] accs = new Account[100];
		int acc_count = 0;
		boolean run = true;
		
		while(run) {
			System.out.println("-------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("-------------------------------------------");
			System.out.print("선택> ");
			int select = isInt(scan);
			
			switch(select) {
			case 1:
				if(acc_count < 100) {					
					System.out.println("----------");
					System.out.println("  계좌생성");
					System.out.println("----------");
					while(true) {
						System.out.print("계좌번호: ");
						String ano = scan.next();
						if(Exist.isExist(ano, accs) == false) {
							accs[acc_count] = new Account();
							accs[acc_count].setAno(ano);
							break;
						}
						else {
						System.out.println("계좌번호가 중복되므로 다시 입력해주세요.");
						}
					}
					System.out.print("계좌주: ");
					accs[acc_count].setOwner(scan.next());
					System.out.print("초기입금액: ");
					int money = isInt(scan);
					if(accs[acc_count].setBalance(money)) {
						System.out.printf("결과: 계좌가 생성되었습니다. [잔액: %d원]\n", accs[acc_count].getBalance());
					} else {
						System.out.println("결과: 계좌가 생성되었습니다. [잔액: 0원][입금범위 초과]");
					}
					acc_count++;
				} else {
					System.out.println("계좌생성 한도가 초과했습니다. [한도: 100개]");
				}
				break;
				
			case 2:
				System.out.println("----------");
				System.out.println("  계좌목록");
				System.out.println("----------");
				for(int i = 0; i < acc_count; i++) {
					System.out.printf("%s\t%s\t%d원\n", accs[i].getAno(), accs[i].getOwner(), accs[i].getBalance());
				}
				break;
				
			case 3:
				System.out.println("----------");
				System.out.println("   예금");
				System.out.println("----------");
				System.out.print("계좌번호: ");
				String ano = scan.next();
				if(Exist.isExist(ano, accs)) {
					System.out.print("예금액: ");
					int money = isInt(scan);
					int idx = Exist.getIdx();
					if(accs[idx].setBalance(money)) {
						System.out.printf("결과: 예금이 성공했습니다. [잔액: %d원]\n", accs[idx].getBalance());
					} else {
						System.out.println("결과: 계좌 예금한도가 초과되어 실패했습니다. [한도: 100만원]");
					}
				} else {
					System.out.println("[오류] 계좌번호가 존재하지 않습니다.");
				}
				break;
				
			case 4:
				System.out.println("----------");
				System.out.println("   출금");
				System.out.println("----------");
				System.out.print("계좌번호: ");
				ano = scan.next();
				if(Exist.isExist(ano, accs)) {
					System.out.print("출금액: ");
					int money = isInt(scan);
					int idx = Exist.getIdx();
					if(accs[idx].setBalance(-money)) {
						System.out.printf("결과: 출금이 성공했습니다. [잔액: %d원]\n", accs[idx].getBalance());
					} else {
						System.out.printf("결과: 출금한도가 초과되어 실패했습니다. [잔액: %d원]\n", accs[idx].getBalance());
					}
				} else {
					System.out.println("[오류] 계좌번호가 존재하지 않습니다.");
				}
				break;
				
			case 5:
				System.out.println("프로그램 종료");
				scan.close();
				run = false;
				break;
			}
			
		}

	}

}
