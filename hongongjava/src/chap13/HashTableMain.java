package chap13;
import java.util.*;
public class HashTableMain {

	public static void main(String[] args) {
		//HashMap과 달리 Hashtable은 Vector처럼 동기화된 메소드로 구성되어, 멀티 스레드 환경에서 객체 추가/삭제 등 안정적임.
		Map<String, String> map = new Hashtable<String, String>();
		
		map.put("spring", "12");
		map.put("summer", "21");
		map.put("autumn", "123");
		map.put("winter", "333");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("-------- 정보 등록 --------");
		System.out.print("아이디> ");
		String id = scan.nextLine();
		System.out.print("비밀번호> ");
		String pw = scan.nextLine();
		map.put(id, pw);
		System.out.println("Entry 정보 수 : " +  map.size());
		
		while(true) {
			System.out.println("-------------------------");
			System.out.println("아이디와 비밀번호를 입력해주세요. (종료 : q)");
			System.out.print("아이디> ");
			id = scan.nextLine();
			if(id.equals("q")) break;
			System.out.print("비밀번호> ");
			pw = scan.nextLine();
			
			if(map.containsKey(id)) {
				if(map.get(id).equals(pw)) {
					System.out.println("로그인되었습니다.");
					break;
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			} else {
				System.out.println("입력하신 아이디가 존재하지 않습니다.");
			}
		}
		scan.close();
		
	}

}
