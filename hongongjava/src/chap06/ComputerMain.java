package chap06;

public class ComputerMain {

	public static void main(String[] args) {
		Computer my_com = new Computer();
		int[] sum1_nums = {1, 2, 3};
		System.out.printf("sum1(매개변수는 배열) 의 결과: %d\n", my_com.sum1(sum1_nums));
		System.out.printf("sum2(매개변수 자동 배열화) 의 결과: %d\n", my_com.sum2(1,2,3));
		System.out.printf("sum2에 배열을 넣은 결과: %d", my_com.sum2(sum1_nums));

	}

}
