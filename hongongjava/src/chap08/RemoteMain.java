package chap08;

public class RemoteMain {

	public static void main(String[] args) {
		RemoteControl rc;
		rc = new Tv();
		rc.turnOn();
		rc.setVolume(3);
		rc.turnOff();
		
		rc = new Audio();
		rc.turnOn();
		rc.setVolume(300);
		rc.turnOff();

	}

}
