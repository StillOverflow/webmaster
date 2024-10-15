package book_manager;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class BookMain {
	
	static MemberDao mdao = MemberDao.getInstance(); //싱글톤 방식의 객체 적용 시
	static Scanner scan = new Scanner(System.in); //scan이 static이어야 각 메소드마다 새로 호출하지 않아도 사용 가능.
	
	public static void main(String[] args) {
		//메인메뉴
		//권한이 User => 도서관리 처리.
		//권한이 Admin => 회원관리 처리.
		while(true) {
			System.out.print("아이디: ");
			String id = scan.nextLine();
			System.out.print("비밀번호: ");
			String pw = scan.nextLine();
			Member member = mdao.checkMember(id, pw);
			if(member != null) {
				String name = member.getMemberName();
				String resp = member.getResponsibility();
				if(resp.equals("Admin")) {
					System.out.println(name + "님 환영합니다!");
					memberManage();
					break;
				}
				System.out.println(name + "님 환영합니다!");
				bookManage();
				break;
			}
			System.out.println("회원정보가 일치하지 않습니다.");
		}
		
		scan.close();

	}
	static void memberManage() {
		System.out.println("관리자 메뉴입니다.");
		boolean run = true;
		while(run) {
			System.out.println("-------------------------------------------------");
			System.out.println("1.회원등록 | 2.회원목록 | 3.회원삭제 | 4.회원정보수정 | 5.종료");
			System.out.println("-------------------------------------------------");
			System.out.print("선택: ");
			int select = isInt(scan);
			switch(select) {
			case 1 :
				System.out.print("회원ID: ");
				String id = scan.next();
				if(mdao.isMemberId(id)) {
					System.out.println("이미 존재하는 아이디입니다.");
					break;
				}
				System.out.print("비밀번호: ");
				String pw = scan.next();
				System.out.print("이름: ");
				String name = scan.next();
				System.out.print("연락처: ");
				String phone = scan.next();
				mdao.memberAdd(id, name, pw, phone);
				System.out.println("성공적으로 등록되었습니다.");
				break;
			case 2 :
				List<Member> list = mdao.memberList();
				for(Member member : list) {
					System.out.println(member.toString());
				}
				break;
			case 3 :
				System.out.print("삭제할 회원ID: ");
				id = scan.next();
				System.out.print("비밀번호: ");
				pw = scan.next();
				Member member = mdao.checkMember(id, pw);
				if(member != null) {
					String resp = member.getResponsibility();
					if(resp.equals("Admin")) {
						System.out.println("Admin 계정은 삭제할 수 없습니다.");
					} else {
						mdao.memberDel(id);
						System.out.println(id + " 회원이 삭제되었습니다.");
					}
				} else {
					System.out.println("회원정보가 일치하지 않습니다.");					
				}
				break;
			case 4 :
				System.out.print("수정할 ID: ");
				id = scan.next();
				if(!mdao.isMemberId(id)) {
					System.out.println("존재하지 않는 회원입니다.");
					break;
				}
				int part = 0;
				System.out.print("[1.아이디/2.비밀번호/3.이름/4.연락처] 중 선택: ");
				while(true) {
					part = isInt(scan);
					if(part >= 1 & part <= 4) break;
					System.out.println("숫자를 다시 입력해주세요.");
					scan.nextLine();
				}
				System.out.print("변경할 값 입력: ");
				String value = scan.next();
				if(part == 1) {
					while(true) {
						if(mdao.isMemberId(value)) {
							System.out.print("이미 존재하는 아이디입니다. 재입력: ");
							value = scan.next();
						} else break;
					}
				}
				System.out.println(id + " 회원의 " + mdao.memberUp(id, part, value) + " 정보가 변경되었습니다.");
				break;
			case 5 :
				System.out.println("회원 관리 시스템을 종료합니다.");
				run = false;
				break;
			}
			
		}
	}
	
	static void bookManage() {
		//1.도서등록 | 2. 도서검색 | 3.도서전체조회 | 4.도서삭제 | 5.도서정보변경| 6.종료
		BookDao dao = BookDao.getInstance();
		boolean run = true;
		while(run) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("1.도서등록 | 2. 도서검색 | 3.도서전체조회 | 4.도서삭제 | 5.도서정보변경| 6.종료");
			System.out.println("--------------------------------------------------------------");
			System.out.print("선택: ");
			int select = isInt(scan);
			switch(select) {
			case 1 :
				System.out.print("제목: ");
				String title = scan.next();
				System.out.print("저자: ");
				String writer = scan.next();
				System.out.print("가격: ");
				int price = scan.nextInt();
				dao.insert(title, writer, price);
				break;
			case 2 :
				System.out.println("검색 조건을 입력해주세요.");
				int part = 0;
				while(true) {
					System.out.print("[1.제목/2.저자/3.번호] 중 선택: ");
					part = isInt(scan);
					if(part >= 1 & part <= 3) break;
					System.out.println("숫자를 다시 입력해주세요.");
					scan.nextLine();
				}
				System.out.print("검색할 값 입력: ");
				String value = scan.next();
				System.out.println("제목\t저자\t가격\t번호");					
				dao.select(part, value);
				break;
			case 3 :
				System.out.println("제목\t저자\t가격\t번호");
				dao.selectAll();
				break;
			case 4 :
				System.out.print("삭제할 도서 번호: ");
				String bnum = scan.next();
				if(dao.isThere(bnum)) {
					dao.delete(bnum);	
				} else {
					System.out.println("해당 도서가 존재하지 않습니다.");
				}
				break;
			case 5 :
				System.out.print("변경할 도서 번호: ");
				bnum = scan.next();
				if(!dao.isThere(bnum)) {
					System.out.println("해당 도서가 존재하지 않습니다.");
					break;
				}
				System.out.println("제목\t저자\t가격\t번호");
				dao.select(3, bnum);
				System.out.println("변경할 정보를 입력해주세요.");
				part = 0;
				while(true) {
					System.out.print("[1.제목/2.저자/3.가격/4.번호] 중 선택: ");
					part = isInt(scan);
					if(part >= 1 & part <= 4) break;
					System.out.println("숫자를 다시 입력해주세요.");
					scan.nextLine();
				}
				System.out.print("변경할 값 입력: ");
				value = scan.next();
				if(part == 4) {
					while(true) {
						if(dao.isThere(value)) {
							System.out.print("이미 존재하는 번호입니다. 재입력: ");
							value = scan.next();
						} else break;
					}
				}
				dao.update(bnum, part, value);
				System.out.println("제목\t저자\t가격\t번호");
				dao.select(3, bnum);
				break;
			case 6 :
				System.out.println("도서 관리 시스템을 종료합니다.");
				run = false;
				break;
			}
		}
	}
	
	//숫자만 입력 예외처리 메소드
	static int isInt(Scanner scan) {
		int number = 0;
		try {
			number = scan.nextInt();
			return number;
		} catch (InputMismatchException e) {
			scan.nextLine();
			System.out.print("숫자를 입력해주세요. ");
			return isInt(scan);
		}
	}

}
