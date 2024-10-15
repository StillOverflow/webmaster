package chap07;

public class CalcMain {

	public static void main(String[] args) {
		Computer com = new Computer();
		
		double result = com.areaCircle(10);
		System.out.println("자식 메소드 : " + result);
		
		Calculator cal = new Calculator();
		result = cal.areaCircle(10);
		System.out.println("부모 메소드 : " + result);
		
		result = com.ac(11);
		System.out.println("자식의 새로 만든 메소드 : " + result);

	}

}
