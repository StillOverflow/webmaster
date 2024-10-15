package chap06;

public class CarExample {

	public static void main(String[] args) {
		//객체 생성
		Car myCar = new Car();
		
		//필드 사용
		System.out.println("------변경 전------");
		System.out.println("제작회사: " + myCar.company);
		System.out.println("모델명: " + myCar.model);
		System.out.println("색깔: " + myCar.color);
		System.out.println("최고속도: " + myCar.getMaxSpeed());
		System.out.println("현재속도: " + myCar.getSpeed());
		
		//필드값 변경
		myCar.setSpeed(60); //필드값에 직접 접근 못 하게 Getter, Setter 적용
		myCar.color = "흰색";
		System.out.println("------변경 후------");
		System.out.println("제작회사: " + myCar.company);
		System.out.println("모델명: " + myCar.model);
		System.out.println("색깔: " + myCar.color);
		System.out.println("최고속도: " + myCar.getMaxSpeed());
		System.out.println("현재속도: " + myCar.getSpeed());
		System.out.println();
		
		//메소드 선언
		myCar.setGas(5);
		myCar.isLeftGas();
		myCar.setGas(2);
		boolean gasState = myCar.isLeftGas();
		if(gasState) {
			System.out.println("출발합니다.");
			myCar.run();
		} else {
			System.out.println("gas가 없어 달릴 수 없습니다.");
		}
		
		//p284
		myCar.keyTurnOn();
		myCar.run2();
		int speed = myCar.getSpeed();
		System.out.println("현재 속도 : " + speed);
		
		//p327 Getter Setter 메소드 적용
		myCar.setSpeed(-50);
		System.out.println(myCar.getSpeed());
		myCar.setSpeed(60);
		System.out.println(myCar.getSpeed());
		
		if(!myCar.isStop()) { //자동차 브레이크(멈춤)
			myCar.setStop(true);
		}
		System.out.println(myCar.getSpeed());
	}

}
