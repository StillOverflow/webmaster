package chap07;

public class Computer extends Calculator {
	//메소드 재정의
	@Override //어노테이션 없어도 되지만 있으면 컴파일러에서 오류 알려줌
	double areaCircle(double r) {
		System.out.println("Computer 객체의 areaCircle() 실행");
		return Math.PI * r * r;
	}
	
	double ac(double r) {
		double result = 0;
		if(r > 10) {
			result = super.areaCircle(r);
		} else {
			result = areaCircle(r);
		}
		return result;
	}
}
