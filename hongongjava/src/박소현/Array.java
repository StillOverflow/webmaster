package 박소현;

import java.util.Arrays;
import java.util.Scanner;

public class Array {

	public static void main(String[] args) {
		// 2024.09.26 과제
		//1.
		System.out.println("1. 배열에 저장 된 값들의 합, 최대값, 최소값 구하기");
		//배열 생성
		int[] ranArr = new int[10];
		int sum = 0;
		int maxNum = Integer.MIN_VALUE;
		int minNum = Integer.MAX_VALUE;
		for(int i = 0; i < ranArr.length; i++) {
			int ranNum = (int)(Math.random() * 100 + 1);
			ranArr[i] = ranNum;
		}
		//배열 합계, 최대값, 최소값
		for(int i : ranArr) {
			sum += i;
			maxNum = Math.max(maxNum, i);
			minNum = Math.min(minNum, i);
		}
		System.out.println(Arrays.toString(ranArr));
		System.out.printf("배열 값의 합계: %d, 최대값: %d, 최소값: %d\n", sum, maxNum, minNum);
		
		
		//2.
		System.out.println("\n2. 2차원 배열 출력");
		int[][] Arr = {
				{1, 2, 3},
				{1, 2},
				{1},
				{1, 2, 3}
		};
		for(int[] i : Arr) {
			for(int j : i) {
				System.out.printf("%d ", j);
			}
			System.out.println();
		}
		
		
		//3.
		System.out.println("\n3. 현재 관객의 수 출력");
		int[][] seats = new int[3][10];		
		int stat = 0;
		for(int row = 0; row < seats.length; row++) {
			for(int col = 0; col < seats[row].length; col++) {
				int seat_value = (int)(Math.random() * 2);
				seats[row][col] = seat_value;
				if(seat_value == 1) stat++;
			}
		}
		for(int[] row : seats) {
			for(int col : row) {
				System.out.printf("%d ", col);
			}
			System.out.println();
		}
		System.out.printf("현재 관객 수는 %02d명\n", stat);
		
		
		//4.
		System.out.println("\n4. 학생 별 평균 계산하기");
		Scanner scan = new Scanner(System.in);
		int[][] st_scores = new int[3][5];
		for(int student = 0; student < 3; student++) {
			for(int sc = 0; sc < 5; sc++) {
				System.out.printf("%d번 학생의 %d번째 성적> ", student+1, sc+1);
				st_scores[student][sc] = scan.nextInt();
			}
			System.out.println();
		}
		for(int[] student : st_scores) {
			for(int sc : student) {
				System.out.printf("%4d ", sc);
			}
			System.out.println();
		}
		for(int student = 0; student < 3; student++) {
			int st_sum = 0;
			for(int sc : st_scores[student]) {
				st_sum += sc;
			}
			System.out.printf("%d번 학생 평균 = %d\n", student+1, st_sum / st_scores[student].length);		
		}
		
		
		//5.
		System.out.println("\n5. 카드를 랜덤하게 선택하여 화면에 출력");
		String[][] card = {
				{"Clubs", "Diamonds", "Hearts", "Spades"},
				{"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}
		};
		for(int i = 1; i <= 5; i++) {
			int ranNum1 = (int)(Math.random() * card[0].length);
			int ranNum2 = (int)(Math.random() * card[1].length);
			System.out.printf("%s의 %s\n", card[0][ranNum1], card[1][ranNum2]);
		}
		
		
		//6.
		System.out.println("\n6. 2차원 배열 만들기");
		int[][] ranArr2 = new int[3][5];
		int insert = 1;
		while(insert <= 5) {
			int ranR = (int)(Math.random() * ranArr2.length);
			int ranC = (int)(Math.random() * ranArr2[0].length);
			if(ranArr2[ranR][ranC] != 1) {
				ranArr2[ranR][ranC] = 1;
				insert++;
			}			
		}
		for(int[] row : ranArr2) {
			for(int col : row) {
				System.out.printf("%d ", col);
			}
			System.out.println();
		}
		scan.close();
	}

}
