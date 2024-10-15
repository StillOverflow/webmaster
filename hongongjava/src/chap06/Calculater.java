package chap06;

public class Calculater {
	//p287 메소드 오버로딩
	//정사각형인 경우 넓이(매개변수 1개)
	double areaRectangle(double width) {
		return width * width;
	}
	
	//직사각형인 경우 넓이(매개변수 2개)
	double areaRectangle(double width, double height) {
		return width * height;
	}
	
}
