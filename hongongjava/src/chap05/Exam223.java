package chap05;
import java.util.Scanner;
public class Exam223 {

	public static void main(String[] args) {
		//p223. 4.
		int maximum = 0;
		int[] array = {1, 5, 3, 8, 2};
		for(int i = 0; i < array.length; i++) {
			if(maximum < array[i]) maximum = array[i];
		}
		System.out.printf("max: %d\n\n", maximum);
		
		//5.
		int[][] array2 = {
				{95, 86},
				{83, 92, 96},
				{78, 83, 93, 87, 88}
		};
		int sumary = 0;
		
//		for(int i = 0; i < array2.length; i++) {
//			for(int j = 0; j < array2[i].length; j++) {
//				sumary += array2[i][j];
//			}
//		}
		for(int[] i : array2) {
			for(int j : i) {
				sumary += j;
			}
		}
		System.out.println("sum: " + sumary);
		System.out.println("avg: " + (double)sumary / (array2[0].length + array2[1].length + array2[2].length));
		
		//6.
//		boolean run = true;
		int studentNum = 0;
		int[] scores = null;
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n----------------------------------------------");
			System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
			System.out.println("----------------------------------------------");
			System.out.print("선택> ");
			int selectNo = scan.nextInt();
			
			if(selectNo == 1) {
				System.out.print("학생수> ");
				studentNum = scan.nextInt();
				scores = new int[studentNum];
			} else if(selectNo == 2) {
				for(int i = 0; i < studentNum; i++) {
					System.out.printf("scores[%d]> ", i);
					scores[i] = scan.nextInt();
				}
			} else if(selectNo == 3) {
				for(int i = 0; i < studentNum; i++) {
					System.out.printf("scores[%d]> %d\n", i, scores[i]);
				}
			} else if(selectNo == 4) {
				int max = 0;
				int sum = 0;
				for(int i = 0; i < studentNum; i++) {
					if(max < scores[i]) max = scores[i];
				}
				System.out.printf("최고 점수: %d", max);
				for(int i = 0; i < studentNum; i++) {
					sum += scores[i];
				}
				System.out.printf("\n평균 점수: %f", (double)sum / studentNum);
			} else {
				System.out.println("프로그램 종료");
				break;
			  }
		}
		scan.close();	
	}

}
