package chap05;

import java.util.Arrays;

public class Exam202 {

	public static void main(String[] args) {
		pr1();
		pr2();
	}
	
	public static void pr1() {
		//배열을 생성
		//배열을 이용하는 방법
		//평균 소수점 2번째 자리까지 출력, 반복횟수는 배열의 길이로 활용
		int[] scores = {83, 90, 87};
		System.out.println("scores[0] : " + scores[0]);
		System.out.println("scores[1] : " + scores[1]);
		System.out.println("scores[2] : " + scores[2]);
		
		int sum = 0;
		for(int i = 0; i < scores.length; i++) {
			sum += scores[i];
		}
		
		System.out.printf("scores 총합 : %d\n", sum);
		System.out.printf("scores 평균 : %5.2f\n", (double)sum / scores.length);		
	}
		
	public static void pr2() {
		//배열에 1에서 100까지의 임의의 수 10개를 저장
		//배열의 합과 평균(소수 첫째자리까지), 최대값, 최소값을 구하세요. (생성과 연산 따로)
		int nums[];
		nums = new int[10];
		System.out.printf("생성된 배열 : ");
		for(int i = 0; i < nums.length; i++) {
			int ranNum = (int)(Math.random() * 100 + 1);
			nums[i] = ranNum;
			System.out.printf("%d ", ranNum);
		}
		System.out.println("\n" + Arrays.toString(nums));
		
		int sum = 0;
		int max = 0;
		int min = nums[0];
		for(int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if(max < nums[i]) max = nums[i];
			if(min > nums[i]) min = nums[i];
		}
		System.out.printf("배열의 합 : %s\n", sum);
		System.out.printf("배열의 평균 : %5.2f\n", (double)sum / nums.length);
		System.out.printf("배열의 최대값 : %d\n", max);
		System.out.printf("배열의 최소값 : %d\n", min);
	}


}
