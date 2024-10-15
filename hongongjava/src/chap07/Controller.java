package chap07;

public class Controller { //public이 있으면 클래스명과 같은 단일 파일이 있어야 함. (public 빼면 클래스 여러 개 가능-바이트코드 개별적으로 생성됨)

	public MemberService service;

	public void setService(MemberService service){
		this.service = service;
	}
	
	
	public static void main(String[] args) { //객체를 생성하고 있는 클래스는 보이지 않지만 실행main메소드 가지고 있음.
		Controller controller = new Controller();
		
		controller.setService(new MemberService());
		controller.service.login();
		controller.service.download();
		
		controller.setService(new AService());
		controller.service.login();
		controller.service.download();
	}
}


