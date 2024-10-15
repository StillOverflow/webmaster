package chap06;

public class Computer {
	//필드
	//생성자
	//메소드
	//p275 매개 변수를 여러 개 받되, 개수를 모를 경우
	//두 가지 동일한 결과
	int sum1(int[] nums) { //배열이 매개변수로 들어갈 수 있다.
		int sum = 0;
		for(int i = 0; i < nums.length; i++) sum += nums[i];
		return sum; //return type int 있으므로 return 꼭 해야 함. (type void로 적으면 return 적을 시 종료)
	}
	
	int sum2(int ... nums) { //자동으로 배열화되어 매개변수가 들어간다. (배열이어도 되고, 배열 아니어도 됨.)
		int sum = 0;
		for(int i : nums) sum += i;
		return sum;
	}
	
}
