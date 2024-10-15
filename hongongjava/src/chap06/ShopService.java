package chap06;

public class ShopService {
	//p307. 3.
	//싱글톤 기법 사용
	//필드 -> 자신의 객체 저장
	private static ShopService ssv = new ShopService();
	
	//생성자 -> 외부 접근 막음
	ShopService(){}
	
	//메소드 -> 외부로 객체 전달 가능하게 함.
	static ShopService getInstance() {
		return ssv;
	}
}
