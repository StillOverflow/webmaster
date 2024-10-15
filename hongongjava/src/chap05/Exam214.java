package chap05;
import java.util.Arrays;
public class Exam214 {

	public static void main(String[] args) {
		//2차원(다차원) 배열
		int[][] scores = {
				{10, 20, 30},
				{40, 50, 60},
				{20, 40},
				{30, 50}
		};
		
		System.out.println(scores[1][1]);
		
		for(int i = 0; i < scores.length; i++) {
			for(int j = 0; j < scores[i].length; j++) {
				System.out.print(scores[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		//2*3 배열을 선언하고 1에서 10까지 무작위 수 입력하기
		int[][] ranNumArr;
		ranNumArr = new int[2][]; //행의 수
		ranNumArr[0] = new int[5]; //각 행의 열의 수
		ranNumArr[1] = new int[7];
		
		for(int row = 0; row < ranNumArr.length; row++) {
//			for(int col = 0; col < ranNumArr[row].length; col++) {
			for(int col : ranNumArr[row]) { //향상된 for문(js의 idx of Array~와 유사함)
				int ranNum = (int)(Math.random() * 10 + 1);
				ranNumArr[row][col] = ranNum;
				System.out.printf("%-2d ",ranNumArr[row][col]);
			}
			System.out.println();
		}
		
		//향상된 for문
		int[] su = {10, 20, 30, 40, 50};
		//배열의 합, 최대값, 최소값
		int sum = 0;
		int maximum = Integer.MIN_VALUE; //int의 허용 가능한 값 중에 최소값/최대값(안정성 향상)
		int minimum = Integer.MAX_VALUE;
		for(int idx : su) {
			sum += idx;
			maximum = Math.max(idx, maximum); //Math.max()또는 Math.min()함수: 둘 중 비교하여 자동 도출
			minimum = Math.min(idx, minimum);
		}
		System.out.println(Arrays.toString(su));
		System.out.printf("su[]배열의 sum = %d, Max = %d, Min = %d", sum, maximum, minimum);
		
	}

}
