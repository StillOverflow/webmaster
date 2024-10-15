package chap06;

public class Printer {
	//p291. 4.
	//static 붙이면 new 할당 없이도 사용 가능(고정메모리)
	void println(int con) {
		System.out.println(con);
	}
	
	void println(boolean con) {
		System.out.println(con);
	}
	
	void println(double con) {
		System.out.println(con);
	}
	
	void println(String con) {
		System.out.println(con);
	}
}
