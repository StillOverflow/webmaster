package chap06;
import java.util.Scanner;
public class BookMain {

	public static void main(String[] args) {
		//도서 관리 Main : 메뉴 1. 도서 수 입력 / 2. 도서 입력 / 3. 도서 목록 조회 / 4. 도서 분석 / 5. 종료
		//도서 수만큼의 배열 생성하여 도서 객체 저장하고 메뉴 입력 시 배열 적용
		Scanner scan = new Scanner(System.in);
		boolean run = true;
		boolean run_1 = false;
		boolean run_2 = false;
		int number = 0;
		Book[] books = null; //클래스 객체의 경우 null(연결주소 없음)로 초기화 해놓으면 밑에서 new 생성 가능
		while(run) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1. 도서 수 입력 | 2. 도서 입력 | 3. 도서 목록 조회 | 4. 도서 분석 | 5. 종료");
			System.out.println("-----------------------------------------------------------------");
			int select = scan.nextInt();
			switch(select) {
			case 1 :
				System.out.print("도서 수 입력> ");
				number = scan.nextInt();
				books = new Book[number];
				run_1 = true;
				break;
			case 2 :
				if(run_1 == true) {
					for(int i = 0; i < number; i++) {
						System.out.println(i+1 + "번째 도서 입력");
						System.out.print("도서 번호> ");
						int id = scan.nextInt();
						scan.nextLine();
						System.out.print("도서 이름> ");
						String name = scan.nextLine();
						System.out.print("도서 가격> ");
						int price = scan.nextInt();
						books[i] = new Book(name, id, price);
					}
					run_2 = true;					
				} else {
					System.out.println("도서 수를 먼저 입력해주세요.");
				}
				break;
			case 3 :
				if(run_2 == true) {
					for(Book i : books) {
						System.out.printf("%d\t%s\t%d원\n", i.getNumber(), i.getName(), i.getPrice());
					}							
				} else {
					System.out.println("도서를 입력해주세요.");
				}
				break;
			case 4 :
				if(run_2 == true) {
					int sum = 0;
					for(int i = 0; i < number; i++) {
						sum += books[i].getPrice();
					}							
					System.out.printf("도서 가격 합계 : %d원\n도서 평균 가격 : %f원\n", sum, (double)sum/number);
				} else {
					System.out.println("도서를 입력해주세요.");
				}
				break;
			case 5 :
				run = false;
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요.");
			}
		}
		scan.close();
	}

}
