package chap08;

public interface RemoteControl {
	//상수
	public int MAX_VOLUME = 10;
	public int MIN_VOLUME = 0;
	
	//추상메소드
	//인터페이스에는 무조건 추상메소드만 들어갈 수 있음. implements 할 때 새로 다 지정해야 함.
	//implements는 여러 개 동시에 가능(추상클래스가 상속받는 것과 달리)
	//추상클래스와 달리 생성자도 아예 없음. 공통메소드가 있다면 추상클래스가 좋을 수도 있음.
	public void turnOn();
	public void turnOff();
	public void setVolume(int Volume);
}
