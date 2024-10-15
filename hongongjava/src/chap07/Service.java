package chap07;

public abstract class Service { //p386 추상메소드 적용(객체=실체 없음: new 불가)
	public String name;
	
	//추상클래스는 생성자는 있음.
	public void login() {
		System.out.println(name + " 로그인");
	}
	
	public void download() {
		System.out.println(name + " 파일을 다운로드합니다.");
	}
	
//	public abstract void login(); //추상메소드에서는 실행 블록이 없음. Override해야함.
//	
//	public abstract void download();
}