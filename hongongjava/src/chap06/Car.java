package chap06;

public class Car {
	//필드
	String company = "현대자동차";
	String model = "그랜저";
	String color = "검정";
	private int maxSpeed = 350;
	private int speed;
	private int gas = 0;
	private boolean stop; //p326
	
	//생성자(필수/입력 안 하면 public car~~ 자동생성)
	//메소드
	//p279
//	void setGas(int gas) {
//		this.gas += gas;
//	}
//	
//	int getSpeed() {
//		return speed; //바로 호출해도 되지만, 우회하여 정보 보호효과
//	}
	
	boolean isLeftGas() {
		if(gas == 0) {
			System.out.println("gas가 없습니다.");
			return false;
		} else {
			System.out.println("gas 잔량 : " + gas);
			return true;
		}
	}
	//Getter, Setter (값 변경 혹은 읽어낼 때 무결성 유지하기 위해 조건 걸기)
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		if(speed < 0) {
			this.speed = 0;
			return;
		}
		this.speed = speed;
	}

	public int getGas() {
		return gas;
	}

	public void setGas(int gas) {
		this.gas = gas;
	}
	
	public boolean isStop() {
		return stop;
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
		this.speed = 0;
	}

	void run() {
		while(true) {
			if(gas > 0) {
				System.out.println("달립니다. (gas 잔량 : " + gas + ")");
				gas--;
			} else {
				System.out.println("멈췄습니다. (gas 잔량 : " + gas + ")");
				return;
			}
		}
	}
	
	void keyTurnOn() {
		System.out.println("키를 돌립니다.");
	}
	
	void run2() {
		for(int i = 10; i < 50; i += 10) {
			speed = i;
			System.out.println("달립니다. (시속 : " + speed + "km/h)");
		}
	}
	
}
