package project;
import java.util.InputMismatchException;
import java.util.Scanner;
public class HotelMain {
	static Scanner scan = new Scanner(System.in);
	static ReservationDao rvdao = ReservationDao.getInstance();
	static ServiceMemberDao smdao = ServiceMemberDao.getInstance();
	static ServiceDao svdao = ServiceDao.getInstance();
	static ServiceOrderDao sodao = ServiceOrderDao.getInstance();
	
	static ServiceMember smem = null;
	
	
	public static void main(String[] args) {		
		boolean run = true;
		while(run) {
			System.out.println("      ★ ° . .　　　　.　☾ °☆　 . * ● ¸ .");
			System.out.println("    ○ :.　  ° .  • ○ °    ★　  .　 *　.");
			System.out.println("      . .●    ╱◥███◣╱◥████◣");
			System.out.println("     °    *  ╱◥◣ ◥███◣║ ∩ ∩║  ¸ °");
			System.out.println("       • :. │╱◥█◣ 田║ ∩ ∩║  *");
			System.out.println("            ││ ∩ │▓▓│∩ ║ ∩ 田║");
			System.out.println("         시월호텔에 오신 것을 환영합니다.");
			System.out.println("1.서비스 로그인 | 2.호텔 예약페이지로 이동 | 3.종료");
			System.out.print("메뉴선택> ");
			int select = scanInt();
			
			switch(select) {
			case 1:
				System.out.print("방 번호 입력> ");
				String roomNum = scan.next();
				System.out.print("비밀번호 입력> ");
				String password = scan.next();
				
				if(rvdao.isReserved(roomNum,null,null,null) & smdao.isJoinable(roomNum)) {
					smdao.add(roomNum, password);
				}
				smem = smdao.login(roomNum, password);
				
				if(smem != null) {
					if(smem.getAuthority().equals("Admin")) {
						management();
						run = false;
						break;
					} else {
						serviceReservation();
						run = false;
						break;
					}
				} else {
					System.out.println("일치하는 정보가 없습니다.");
				}
				break;
				
			case 2:
				roomReservation();
				run = false;
				break;
			
			case 3:
				System.out.println("시월호텔 페이지를 종료합니다.");
				run = false;
				break;
			}
		}
	}
	
	//예약 페이지
	static void roomReservation() {
		System.out.println("================= 예약정보 입력 =================");
		System.out.println("* 날짜 및 시간은 0000-00-00 00:00 형식으로 적어주세요.");
		Reservation rv = new Reservation();
		String ckinDate = null;
		String ckoutDate = null;
		
		while(true) {
			System.out.print("체크인 시간> ");
			ckinDate = scanDate() + " " + scanTime();
			System.out.print("체크아웃 시간> ");
			ckoutDate = scanDate() + " " + scanTime();
			
			String listRoom = rvdao.listRoom(ckinDate, ckoutDate);
			System.out.println("\n\t객실목록");
			System.out.println(listRoom);
			
			int replacedLength = listRoom.replace(String.valueOf("X"), "").length();
			if(listRoom.length() - replacedLength == 9) {
				System.out.println("해당 기간에는 모든 방이 예약되어 있습니다.");
			} else {
				System.out.print("예약하시겠습니까? [다음: y] [재검색: q] ");
				String answer = scan.next();
				if(answer.equals("y")) {
					break;
				}				
			}
		}
		
		while(true) {
			System.out.print("예약하실 방 번호> ");
			String roomNum = scan.next();
			if(rvdao.isReserved(roomNum, ckinDate, ckoutDate, null)) {
				System.out.println("선택하신 방은 이미 예약되어 있습니다.");
			} else {
				rv.setRoomNumber(roomNum);
				rv.setCheckinDate(ckinDate);
				rv.setCheckoutDate(ckoutDate);
				break;
			}
		}
		
		System.out.print("예약자 성함> ");
		rv.setGuestName(scan.next());
		System.out.print("인원> ");
		rv.setGuestNumber(scan.nextInt());
		System.out.print("연락처> ");
		rv.setGuestTel(scan.next());
		
		rvdao.reserve(rv);
		System.out.println("예약이 완료되었습니다.");
		main(null);
	}
	
	//게스트 로그인 시
	static void serviceReservation() {
		String memberId = smem.getMemberId();
		Reservation rv = rvdao.getReservation(memberId);
		String roomNum = rv.getRoomNumber();
		String name = smem.getMemberName();
		
		boolean run = true;
		while(run) {
			System.out.printf("\n%s호 %s님, 환영합니다.\n", roomNum, name);
			System.out.println("======= 주문 가능한 서비스목록 =======");
			System.out.println("번호\t품목\t\t이용요금");
			System.out.println(svdao.printAll());
			System.out.println("1.주문서작성 | 2.주문내역확인 | 3.종료");
			System.out.print("메뉴선택> ");
			int select = scanInt();
			
			switch(select) {
			case 1:
				System.out.println("====== 주문서 작성 ======");
				ServiceOrder so = new ServiceOrder();
				int svId = 0;
				while(true) {
					System.out.print("번호선택> ");
					svId = scanInt();
					if(svdao.isExist(svId) == null) {
						System.out.println("서비스번호를 다시 선택해주세요.");
					} else {
						so.setServiceId(svId);
						break;
					}
				}
				System.out.println("번호\t품목\t\t이용요금");
				String service = svdao.search(1, String.valueOf(svId));
				System.out.println(service);
				
				System.out.print("주문수량> ");
				int quantity = scanInt();
				so.setQuantity(quantity);
				
				//쿠폰 보유 시 자동 사용 (기본 welcome 10% 쿠폰 있음)
				String[] str_service = service.split("\t");
				int price = Integer.parseInt(str_service[2]) * quantity;
				String coupon = smem.getCurrentCoupon();
				
				boolean useCoupon = false;
				if(coupon != null) {
					useCoupon = true;
					int discount = smem.getCouponDiscountPercent();
					price *= (1.0 - discount / 100.0);
					System.out.printf("%s %d 쿠폰이 자동으로 적용됩니다.\n", coupon, discount);
				}
				System.out.printf("청구될 요금은 %d원입니다. 주문하시겠습니까? [확인: y] ", price);
				if(!scan.next().equals("y")) break;
				
				System.out.printf("주문자 성함> %s [수정: y] ", name);
				if(scan.next().equals("y")) {
					System.out.print("주문자 성함> ");
					so.setName(scan.next());
				}
				System.out.printf("연락처> %s [수정: y] ", rv.getGuestTel());
				if(scan.next().equals("y")) {
					System.out.print("연락처> ");
					so.setOrderTel(scan.next());
				}
				
				String ckinDate = rv.getCheckinDate();
				System.out.printf("요청시간> %s [수정: y] ", ckinDate);
				if(scan.next().equals("y")) {
					while(true) {
						System.out.print("요청시간> ");
						String date = scanDate() + " " + scanTime();
						
						if(rvdao.isReserved(roomNum, date, date, null)) {
							so.setRequiredDate(date);	
							break;
						} else {
							System.out.println("예약하신 시간 안에서만 입력 가능합니다.");
							System.out.printf("(%s ~ %s)\n", ckinDate, rv.getCheckoutDate());
						}
					}
				}
				System.out.print("요청사항> ");
				so.setMemo(scan.next());
				sodao.order(rv, smem, so);
				
				//주문완료 시 쿠폰 사용처리
				if(useCoupon & smem.getCurrentCoupon() == null) {
					smdao.setCoupon(memberId, null, 0);
				}
				System.out.println("주문이 완료되었습니다.");
				break;
			
			case 2:
				System.out.println("================================================================ 주 문 내 역 ================================================================");
				System.out.println("주문번호\t주문일자\t\t\t주문자명\t방번호\t연락처\t\t번호\t품목\t\t수량\t요금"
				                 + "\t적용쿠폰\t\t요청시간\t\t\t요청사항\t처리여부\t처리일자");
				System.out.println(sodao.search(4, roomNum, rv));
				break;
			
			case 3:
				System.out.println("룸서비스 페이지를 종료합니다.");
				run = false;
				break;
			}
		}
	}
	
	//관리자 메인
	static void management() {
		System.out.println("\n               관리자 메뉴에 접속하셨습니다.");
		System.out.println("1.예약관리 | 2.서비스관리 | 3.주문관리 | 4.접속회원관리 | 5.종료");
		System.out.print("메뉴선택> ");
		int select = scanInt();
		
		switch(select) {
		case 1: roomManagement(); break;
		case 2: serviceManagement(); break;
		case 3: orderManagement(); break;
		case 4: memberManagement(); break;
		case 5:
			System.out.println("룸서비스 페이지를 종료합니다.");
			break;
		}
	}
	
	static void roomManagement() {
		boolean run = true;
		while(run) {
			System.out.println("\n        **시월호텔 예약관리 페이지입니다.**");
			System.out.println("1.예약목록 조회 | 2.예약수정 | 3.예약삭제 | 4.메인으로");
			System.out.print("메뉴선택> ");
			int select = scanInt();
			
			if(select < 4) {
				System.out.println("========================================== 예 약 목 록 ==========================================");
				System.out.println("예약번호\t예약자명\t방번호\t인원\t연락처\t\t체크인날짜\t\t\t체크아웃날짜\t\t서비스이용");
				System.out.println(rvdao.printAll());
			}
			
			switch(select) {
			case 1: 
				System.out.print("조건으로 검색하시겠습니까? [검색: y] ");
				if(scan.next().equals("y")) {
					System.out.println("1:예약번호 | 2:방번호 | 3:예약자명 | 4:인원 | 5:연락처 | 6:날짜 | 7:서비스이용여부");
					System.out.print("조건 선택> ");
					int condition = scanInt();
					if(condition > 7) {
						System.out.println("잘못된 번호를 선택하셨습니다.");
						break;
					}
					
					System.out.print("검색할 값> ");
					String value = null;
					if(condition == 6) {
						value = scanDate();
					} else if(condition == 4) {
						value = String.valueOf(scanInt());
					} else {
						value = scan.next();						
					}
					
					System.out.println("* 검색결과");
					System.out.println("예약번호\t예약자명\t방번호\t인원\t연락처\t\t체크인날짜\t\t\t체크아웃날짜\t\t서비스이용");
					System.out.println(rvdao.search(condition, value));
				}
				break;
					
			case 2: 
				String id = null;
				Reservation rv = null;
				System.out.print("수정할 예약번호> ");
				id = scan.next();
				rv = rvdao.getReservation(id);
				if(rv == null) {
					System.out.println("존재하지 않는 번호입니다.");
					break;
				}
				
				System.out.println("=== 수정사항 입력 ===");
				String ckinDate = rv.getCheckinDate();
				String ckoutDate = rv.getCheckoutDate();
				Reservation update = new Reservation();
				
				//예약번호 primary key
				System.out.printf("예약번호> %s [수정: y] ", rv.getReserveId());
				if(scan.next().equals("y")) {
					while(true) {
						System.out.print("수정 입력> ");
						String rvNum = scan.next();
						if(rvdao.getReservation(rvNum) == null) {
							update.setReserveId(rvNum);
							break;
						} else {
							System.out.println("이미 존재하는 번호입니다. 다시 입력해주세요.");
						}
					}
				}
				
				//방번호 수정 시 예약여부 재확인
				String roomNum = rv.getRoomNumber();
				System.out.printf("방번호> %s [수정: y] ", roomNum);
				if(scan.next().equals("y")) {
					while(true) {
						System.out.print("수정 입력> ");
						roomNum = scan.next();
						if(rvdao.isReserved(roomNum, ckinDate, ckoutDate, null)) {
							System.out.println("선택하신 방은 이미 예약되어 있습니다.");
						} else {
							update.setRoomNumber(roomNum);
							break;
						}
					}					
				}
				
				System.out.printf("예약자명> %s [수정: y] ", rv.getGuestName());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					update.setGuestName(scan.next());
				}
				System.out.printf("인원> %d [수정: y] ", rv.getGuestNumber());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					update.setGuestNumber(scanInt());
				}
				System.out.printf("연락처> %s [수정: y] ", rv.getGuestTel());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					update.setGuestTel(scan.next());
				}
				
				//날짜 수정 시 예약여부 재확인
				String re_ckinDate = ckinDate;
				String re_ckoutDate = ckoutDate;
				
				System.out.printf("체크인날짜> %s [수정: y] ", ckinDate);
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					re_ckinDate = scanDate() + " " + scanTime();
				}
				
				System.out.printf("체크아웃날짜> %s [수정: y] ", ckoutDate);
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					re_ckoutDate = scanDate() + " " + scanTime();
				}
				
				//변동사항 있을 시 예약여부 확인하여 수정 가능
				if(!re_ckinDate.equals(ckinDate) | !re_ckoutDate.equals(ckoutDate)) {
					if(rvdao.isReserved(roomNum, re_ckinDate, re_ckoutDate, id)) {
						System.out.println("해당 날짜에는 이미 예약자가 있습니다.");
					} else {
						update.setCheckinDate(re_ckinDate);
						update.setCheckoutDate(re_ckoutDate);
					}
				}
				
				System.out.printf("서비스이용여부> %s [수정: y] ", rv.getServiceYn());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					update.setServiceYn(scan.next());
				}
				
				System.out.printf("%s의 [%s] 정보가 변경되었습니다.\n", id, rvdao.update(id, update));
				break;
				
			case 3: 
				System.out.print("삭제할 예약번호> ");
				id = scan.next();
				if(rvdao.getReservation(id) != null) {
					rvdao.delete(id);
					System.out.println("해당 예약정보가 삭제되었습니다.");
				} else {
					System.out.println("존재하지 않는 번호입니다.");
				}
				break;
				
			case 4:
				management();
				run = false;
				break;
			}
		}
	}
	
	static void serviceManagement() {
		boolean run = true;
		while(run) {
			System.out.println("\n       **시월호텔 서비스관리 페이지입니다.**");
			System.out.println("1.목록조회 | 2.추가 | 3.수정 | 4.삭제 |5.메인으로");
			System.out.print("메뉴선택> ");
			int select = scanInt();
			
			if(select != 2 & select < 5) {
				System.out.println("========= 현재 서비스목록 =========");
				System.out.println("번호\t품목\t\t이용요금");
				System.out.println(svdao.printAll());
			}
			
			switch(select) {
			case 1: 
				System.out.print("서비스명으로 검색하시겠습니까? [검색: y] ");
				if(scan.next().equals("y")) {
					System.out.print("검색할 값> ");
					String value = scan.next();
					
					System.out.println("* 검색결과");
					System.out.println("번호\t품목\t\t이용요금");
					System.out.println(svdao.search(2, value));
				}
				break;
					
			case 2:
				System.out.println("=== 서비스 추가 ==");
				System.out.print("품명> ");
				String sName = scan.next();
				System.out.print("이용요금> ");
				int sPrice = scanInt();
				svdao.add(sName, sPrice);
				System.out.println("추가되었습니다.");
				break;
				
			case 3: 
				Service sv = null;
				int id = 0;
				System.out.print("수정할 번호> ");
				id = scanInt();
				sv = svdao.isExist(id);
				if(sv == null) {
					System.out.println("존재하지 않는 번호입니다.");
					break;
				}
				
				System.out.println("=== 수정사항 입력 ===");
				Service update = new Service();
				
				System.out.printf("번호> %s [수정: y] ", sv.getServiceId());
				if(scan.next().equals("y")) {
					while(true) {
						System.out.print("수정 입력> ");
						int num = scanInt();
						if(svdao.isExist(num) == null) {
							update.setServiceId(num);
							break;
						} else {
							System.out.println("이미 존재하는 번호입니다. 다시 입력해주세요.");
						}
					}
				}
				
				System.out.printf("품명> %s [수정: y] ", sv.getServiceName());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					update.setServiceName(scan.next());
				}
				System.out.printf("이용요금> %d [수정: y] ", sv.getServicePrice());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					update.setServicePrice(scanInt());
				}
				
				System.out.printf("%s %s의 [%s] 정보가 변경되었습니다.\n", id, sv.getServiceName(), svdao.update(id, update));
				break;
				
			case 4: 
				System.out.print("삭제할 번호> ");
				id = scanInt();
				if(svdao.isExist(id) != null) {
					svdao.delete(id);
					System.out.println("해당 품목이 삭제되었습니다.");
				} else {
					System.out.println("존재하지 않는 번호입니다.");
				}
				break;
				
			case 5:
				management();
				run = false;
				break;
			}
		}
	}
	
	static void orderManagement() {
		boolean run = true;
		while(run) {
			System.out.println("\n        **시월호텔 주문관리 페이지입니다.**");
			System.out.println("1.전체내역 | 2.주문처리 | 3.수정 | 4.삭제 | 5.메인으로");
			System.out.print("메뉴선택> ");
			int select = scanInt();
			
			if(select != 2 & select < 5) {
				System.out.println("================================================================ 주 문 내 역 ================================================================");
				System.out.println("주문번호\t주문일자\t\t\t주문자명\t방번호\t연락처\t\t번호\t품목\t\t수량\t요금"
				                 + "\t적용쿠폰\t\t요청시간\t\t\t요청사항\t처리여부\t처리일자");
				System.out.println(sodao.printAll());
			}
			
			switch(select) {
			case 1: 
				System.out.print("조건으로 검색하시겠습니까? [검색: y] ");
				if(scan.next().equals("y")) {
					System.out.println("1:주문번호 | 2:주문일자 | 3:주문자명 | 4:방번호  | 5:연락처\n"
							         + "6:품목    | 7:적용쿠폰 | 8:요청일자 | 9:처리여부 | 10:처리일자");
					System.out.print("조건 선택> ");
					int condition = scanInt();
					if(condition > 10) {
						System.out.println("잘못된 번호를 선택하셨습니다.");
						break;
					}
					
					System.out.print("검색할 값> ");
					String value = null;
					if(condition == 2 | condition == 8 | condition == 10) {
						value = scanDate();
					} else {
						value = scan.next();						
					}
					
					System.out.println("* 검색결과");
					System.out.println("주문번호\t주문일자\t\t\t주문자명\t방번호\t연락처\t\t번호\t품목\t\t수량\t요금"
					                 + "\t적용쿠폰\t\t요청시간\t\t\t요청사항\t처리여부\t처리일자");
					System.out.println(sodao.search(condition, value, null));
				}
				break;
					
			case 2:
				System.out.println("============================================================= 미처리 주문내역 =============================================================");
				System.out.println("주문번호\t주문일자\t\t\t주문자명\t방번호\t연락처\t\t번호\t품목\t\t수량\t요금"
				                 + "\t적용쿠폰\t\t요청시간\t\t\t요청사항\t처리여부\t처리일자");
				System.out.println(sodao.search(9, "n", null));
				
				while(true) {
					System.out.print("처리할 주문번호> ");
					String num = scan.next();
					if(sodao.isExist(num) == null) {
						System.out.println("잘못된 번호를 선택하셨습니다.");
						break;
					} 
					
					ServiceOrder so = new ServiceOrder();
					so.setCompletedYn("y");
					sodao.update(num, so);
					System.out.print("처리되었습니다. [추가입력: y] ");
					if(!scan.next().equals("y")) {
						break;
					}
				}
				break;
				
			case 3: 
				ServiceOrder oldSo = null;
				ServiceOrder newSo = new ServiceOrder();
				String id = null;
				System.out.print("수정할 주문번호> ");
				id = scan.next();
				if(sodao.isExist(id) != null) {
					oldSo = sodao.isExist(id);
				} else {
					System.out.println("존재하지 않는 번호입니다.");
					break;
				}
				
				System.out.println("=== 수정사항 입력 ===");
				System.out.printf("주문번호> %s [수정: y] ", oldSo.getOrderId());
				if(scan.next().equals("y")) {
					while(true) {
						System.out.print("수정 입력> ");
						String num = scan.next();
						if(sodao.isExist(num) == null) {
							newSo.setOrderId(num);
							break;
						} else {
							System.out.println("이미 존재하는 번호입니다. 다시 입력해주세요.");
						}
					}
				}
				
				System.out.printf("주문일자> %s [수정: y] ", oldSo.getOrderDate());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newSo.setOrderDate(scanDate() + " " + scanTime());
				}
				System.out.printf("주문자명> %s [수정: y] ", oldSo.getName());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newSo.setName(scan.next());
				}
				System.out.printf("연락처> %s [수정: y] ", oldSo.getOrderTel());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newSo.setOrderTel(scan.next());
				}
				System.out.printf("수량> %d [수정: y] ", oldSo.getQuantity());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newSo.setQuantity(scanInt());
				}
				System.out.printf("요청시간> %s [수정: y] ", oldSo.getRequiredDate());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newSo.setRequiredDate(scanDate() + " " + scanTime());
				}
				System.out.printf("비고> %s [수정: y] ", oldSo.getMemo());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newSo.setMemo(scan.next());
				}
				System.out.printf("처리여부> %s [수정: y] ", oldSo.getCompletedYn());
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newSo.setCompletedYn(scan.next());
				}
				
				System.out.printf("%s의 [%s] 정보가 변경되었습니다.\n", id, sodao.update(id, newSo));
				break;
				
			case 4: 
				System.out.print("삭제할 주문번호> ");
				id = scan.next();
				if(sodao.isExist(id) != null) {
					sodao.delete(id);
					System.out.println("해당 주문건이 삭제되었습니다.");
				} else {
					System.out.println("존재하지 않는 번호입니다.");
				}
				break;
				
			case 5:
				management();
				run = false;
				break;
			}
		}
	}
	
	static void memberManagement() {
		boolean run = true;
		while(run) {
			System.out.println("\n     **시월호텔 접속회원관리 페이지입니다.**");
			System.out.println("1.전체내역 | 2.수정 | 3.삭제 | 4.쿠폰관리 | 5.메인으로");
			System.out.print("메뉴선택> ");
			int select = scanInt();
			
			if(select < 4) {
				System.out.println("=========================== 전 체 목 록 ===========================");
				System.out.println("회원번호\t회원이름\t방번호\t비밀번호\t보유쿠폰\t할인율\t권한\t가용여부");
				System.out.println(smdao.printAll());
			}
			
			switch(select) {
			case 1: 
				System.out.print("조건으로 검색하시겠습니까? [검색: y] ");
				if(scan.next().equals("y")) {
					System.out.println("1:회원번호 | 2:회원이름 | 3:방번호 | 4:보유쿠폰  | 5:권한 | 6:가용여부");
					System.out.print("조건 선택> ");
					int condition = scanInt();
					if(condition > 6) {
						System.out.println("잘못된 번호를 선택하셨습니다.");
						break;
					}
					
					System.out.print("검색할 값> ");
					String value = scan.next();
					
					System.out.println("* 검색결과");
					System.out.println("회원번호\t회원이름\t방번호\t비밀번호\t보유쿠폰\t할인율\t권한\t가용여부");
					System.out.println(smdao.search(condition, value));
				}
				break;
				
			case 2: 
				ServiceMember newMem = new ServiceMember();
				System.out.print("수정할 회원번호> ");
				String id = scan.next();
				if(!smdao.isExist(id)) {
					System.out.println("존재하지 않는 번호입니다.");
					break;
				}
				
				System.out.println("=== 수정항목 입력 ===");
				System.out.print("회원이름 [수정: y] ");
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newMem.setMemberName(scan.next());
				}
				System.out.print("비밀번호 [수정: y] ");
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newMem.setPassword(scan.next());
				}
				System.out.print("가용여부 (*주의: y인 경우에만 로그인 가능합니다.) [수정: y] ");
				if(scan.next().equals("y")) {
					System.out.print("수정 입력> ");
					newMem.setAvailability(scan.next());
				}
				
				System.out.printf("%s의 [%s] 정보가 변경되었습니다.\n", id, smdao.update(newMem));
				break;
				
			case 3: 
				System.out.print("삭제할 회원번호> ");
				id = scan.next();
				if(smdao.isExist(id)) {
					smdao.delete(id);
					System.out.println("해당 주문건이 삭제되었습니다.");
				} else {
					System.out.println("존재하지 않는 번호입니다.");
				}
				break;
				
			case 4:
				System.out.println("========================= 현재 회원목록 =========================");
				System.out.println("회원번호\t회원이름\t방번호\t비밀번호\t보유쿠폰\t할인율\t권한\t가용여부");
				System.out.println(smdao.search(6, "y"));
				System.out.print("쿠폰을 입력하시겠습니까? [입력: y] ");
				String answer = scan.next();
				if(answer.equals("y")) {
					System.out.print("쿠폰명> ");
					String couponName = scan.next();
					System.out.print("할인율> ");
					int discountP = scanInt();
					System.out.println("[개별입력: y | 일괄적용: a] ");
					answer = scan.next();
					if(answer.equals("a")) {
						smdao.setCoupon(null, couponName, discountP);
						System.out.printf("현재 접속가능한 모든 회원에게 [%s %d]쿠폰이 부여되었습니다.\n", couponName, discountP);
					} else {
						while(true) {
							System.out.print("부여할 회원번호> ");
							id = scan.next();
							if(smdao.isExist(id)) {
								smdao.setCoupon(id, couponName, discountP);
								System.out.printf("%s 회원에게 [%s %d]쿠폰이 부여되었습니다.\n", id, couponName, discountP);
								break;
							} else {
								System.out.println("존재하지 않는 번호입니다.");
							}
						}
					}
				}
				break;
				
			case 5:
				management();
				run = false;
				break;
			}
		}
	}
	
	//숫자만 입력 예외처리 메소드
	static int scanInt() {
		int number = 0;
		try {
			number = scan.nextInt();
			return number;
		} catch (InputMismatchException e) {
			scan.nextLine();
			System.out.print("숫자를 입력해주세요. ");
			return scanInt();
		}
	}
	
	//날짜입력 형식 검사 메소드
	static String scanDate() {
		String date = scan.next();
		boolean collect = false;
		
		if(date.length() == 10) {
			if(date.charAt(4) == '-' & date.charAt(7) == '-') {
				date = date.replace("-", "");
				//입력한 값이 숫자인지 검사
				for(int i = 0; i < 8; i++) {
					if(date.charAt(i) < 48 | date.charAt(i) > 57) {
						collect = false;
						break;
					} else {
						collect = true;
					}
				}
			}
		}
		
		if(collect) {
			return date;
		} else {
			System.out.println("* 날짜형식은 0000-00-00 입니다.");
			System.out.print("재입력> ");
			return scanDate();
		}
	}
	
	//날짜+시간입력 형식 검사 메소드
	static String scanTime() {
		String time = scan.next();
		boolean collect = false;
		
		//시간형식 검사
		if(time.length() == 5) {
			if(time.indexOf(':') == 2) {
				String newTime = time.replace(":", "");
				//입력한 값이 숫자인지 검사
				for(int i = 0; i < 4; i++) {
					if(newTime.charAt(i) < 48 | newTime.charAt(i) > 57) {
						collect = false;
						break;
					} else {
						collect = true;
					}
				}
				//시간 범위인지 검사
				int hour = Integer.parseInt(newTime.substring(0,2));
				int minute = Integer.parseInt(newTime.substring(2));
				if(hour > 24 | minute > 60) {
					collect = false;
				}
			}
		}
		
		if(collect) {
			return time;
		} else {
			System.out.println("* 시간형식은 00:00 입니다.");
			System.out.print("시간 재입력> ");
			return scanTime();
		}
	}
	
}
