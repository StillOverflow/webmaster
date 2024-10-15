package 박소현;
//---BAEKJOON 제출 절취선---
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		//2720
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		int[] C = new int[T];
		for(int i = 0; i < T; i++) {
			C[i] = Integer.parseInt(scan.nextLine());			
		}
		for(int i = 0; i < T; i++) {
			for(int j = 1; j <= 4; j++) {
				int money = Integer.parseInt(scan.nextLine()); //단위: 센트(1달러 = 100센트)
				System.out.println();
				int change = money / 25;
				money %= 25;
				System.out.print(change + " ");
				change = money / 10;
				money %= 10;
				System.out.print(change + " ");
				change = money / 5;
				money %= 5;
				System.out.print(change + " ");
				System.out.print(money);
			}
			System.out.println();
		}
		scan.close();
		
		
		//11005
//		Scanner scan = new Scanner(System.in);
//		int N = scan.nextInt();
//		int B = scan.nextInt();
//		scan.close();
//		String result = "";
//		int quo;
//		for(quo = N; quo >= B; quo /= B) {
//			int remain = quo % B;
//			if(remain >= 10) {
//				int alpa = remain + 55;
//				result += (char)alpa;
//			} else{
//				result += remain;
//			}
//		}
//		if(quo >= 10) {
//			int alpa = quo + 55;
//			result += (char)alpa;
//		} else result += quo;
//		
//		int len = result.length();
//		String result_re = "";
//		for(int i = len-1; i >= 0; i--) {
//			result_re += result.charAt(i);
//		}
//		System.out.println(result_re);
		
		
		//2745
//		Scanner scan = new Scanner(System.in);
//		String N = scan.next();
//		int B = Integer.parseInt(scan.next());
//		int N_len = N.length();
//		int result = 0;
//		for(int i = 0; i < N_len; i++) {
//			char num = N.charAt(i);
//			if(num >= 65) num -= 55;
//			else num-= 48;
//			int b = 1;
//			for(int j = 0; j < N_len-(i+1); j++) {
//				b *= B;
//			}
//			result += (num * b);
//		}
//		System.out.println(result);
		
		
		//2563
//		Scanner scan = new Scanner(System.in);
//		int num = scan.nextInt();
//		scan.nextLine();
//		boolean[][] arr = new boolean[100][100];
//		int di = num * 10 * 10;
//		for(int row = 0; row < num; row++) {
//			int x = scan.nextInt();
//			int y = scan.nextInt();
//			for(int i = y; i < y+10; i++) {
//				for(int j = x; j < x+10; j++) {
//					if(arr[i][j]) {
//						di--;
//					} else {
//						arr[i][j] = true;
//					}
//				}
//			}
//			scan.nextLine();
//		}
//		scan.close();
//		System.out.println(di);
		
//		int over_x = 0;
//		int over_y = 0;
//		int prev_x = arr[0][0];
//		int prev_y = arr[0][1];
//		for(int col = 0; col < 2; col++) {
//			for(int row = 1; row < num; row++) {
//				if(col == 0) {
//					if(prev_x <= arr[row][col] & arr[row][col] <= prev_x + 10) {
//						over_x = prev_x
//					}
//					prev_x = arr[row][col];
//				} else {
//					
//				}
//				
//			}
//		}
		
		//10798
//		Scanner scan = new Scanner(System.in);
//		char[][] arr = new char[5][15];
//		for(int row = 0; row < 5; row++) {
//			String alpa = scan.nextLine();
//			int len = alpa.length();
//			for(int col = 0; col < len; col++) {
//				arr[row][col] = alpa.charAt(col);
//			}
//		}
//		scan.close();
//		for(int col = 0; col < 15; col++) {
//			for(int row = 0; row < 5; row++) {
//				if(arr[row][col] != 0) {
//					System.out.print(arr[row][col]);
//				}
//			}
//		}
		
		//2566
//		Scanner scan = new Scanner(System.in);
//		int row = 0;
//		int col = 0;
//		int max = Integer.MIN_VALUE;
//		for(int i = 1; i <= 9; i++) {
//			for(int j = 1; j <= 9; j++) {
//				int num = scan.nextInt();
//				if(max < num) {
//					max = num;
//					row = i;
//					col = j;
//				}
//			}
//			scan.nextLine();
//		}
//		scan.close();
//		System.out.println(max);
//		System.out.println(row + " " + col);
		
		
		//2738
//		Scanner scan = new Scanner(System.in);
//		int N = scan.nextInt();
//		int M = scan.nextInt();
//		int[][] arr_A = new int[N][M];
//		int[][] arr_B = new int[N][M];
//		int[][] result = new int[N][M];
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				arr_A[i][j] = scan.nextInt();
//			}
//			scan.nextLine();
//		}
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				arr_B[i][j] = scan.nextInt();
//				result[i][j] = arr_A[i][j] + arr_B[i][j];
//			}
//			scan.nextLine();
//		}
//		scan.close();
//		for(int[] i : result) {
//			for(int j : i) {
//				System.out.printf("%d ", j);
//			}
//			System.out.println();
//		}
		
		//25206
//		Scanner scan = new Scanner(System.in);
//		String[][] arr = new String[20][3];
//		for(int i = 0; i < 20; i++) {
//			String sentence = scan.nextLine();
//			int len = sentence.length();
//			int blank1 = sentence.indexOf(" ");
//			int blank2 = sentence.lastIndexOf(" ");
//			arr[i][0] = sentence.substring(0, blank1);
//			arr[i][1] = sentence.substring(blank1+1, blank2);
//			arr[i][2] = sentence.substring(blank2+1, len);
//		}
//		scan.close();
//		double hak_sum = 0;
//		double sum = 0;
//		for(int i = 0; i < 20; i++) {
//			if(arr[i][2].charAt(0) != 'P') {
//				double p = 0;
//				if(arr[i][2].charAt(0) != 'F') {
//					if(arr[i][2].charAt(1) == '+') p = 0.5;
//					switch(arr[i][2].charAt(0)) {
//					case 'A' : p += 4; break;
//					case 'B' : p += 3; break;
//					case 'C' : p += 2; break;
//					case 'D' : p += 1; break;
//					}
//				}
//				sum += Double.parseDouble(arr[i][1]) * p;
//				hak_sum += Double.parseDouble(arr[i][1]);	
//			}
//		}
//		System.out.printf("%8.6f", sum / hak_sum);
		
		//1316
//		Scanner scan = new Scanner(System.in);
//		int N = Integer.parseInt(scan.nextLine());
//		int count = N;
//		for(int i = 1; i <= N; i++) {
//			boolean[] alpab = new boolean[26];
//			String word = scan.nextLine();
//			int len = word.length();
//			alpab[word.charAt(0) - 97] = true; 
//			for(int j = 1; j < len; j++) {
//				int idx = word.charAt(j) - 97;
//				if(alpab[idx] == false) alpab[idx] = true;
//				else if(word.charAt(j) != word.charAt(j-1)) {
//					count--;
//					break;
//				}
//			}
//		}
//		System.out.println(count);
//		scan.close();
		
//		for(int i = 0; i <= N; i++) {
//			String word = scan.nextLine();
//			int len = word.length();
//			boolean group = true;
//			for(int j = 0; j < len-1; j++) {
//				if(word.charAt(j) != word.charAt(j+1) && word.lastIndexOf(word.charAt(j)) != j) {
//					group = false;
//					System.out.printf("%s, %s, %d, %d, %s \n",word.charAt(j), word.charAt(j+1), word.lastIndexOf(word.charAt(j)),j, word);
//					break;
//				}
//			}
//			if(group) count++;
//			System.out.println(group);
//		}
//		System.out.println(count);
//		scan.close();
		
		
		//2941
//		Scanner scan = new Scanner(System.in);
//		String word = scan.nextLine();
//		scan.close();
//		String[] arr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
//		for(int i = 0; i < 8; i++) {
//			if(word.contains(arr[i])) {
//				word = word.replace(arr[i],"a");
//			}
//		}
//		System.out.println(word.length());
		
//		int count = 0;
//		for(int i = 0; i < 8; i++) {
//			int idx = word.indexOf(arr[i]);
//			if(idx != -1) count++;
//			for(int i = 0; i < len; i++) {
//				
//			}
//		}
//		System.out.println(word.indexOf(arr[0]));
//		System.out.println(count);
		
		
		//1157
//		Scanner scan = new Scanner(System.in);
//		String word = scan.nextLine();
//		scan.close();
//		int max_count = 0;
//		char max_al = 0;
//		int w_len = word.length();
//		int[] char_arr = new int[26];
//		for(int i = 0; i < w_len; i++) {
//			int w_char = word.charAt(i);
//			if(97 <= w_char & w_char <= 122) char_arr[w_char - 97]++;
//			else if(65 <= w_char & w_char <= 90) char_arr[w_char - 65]++;
//		}
//		for(int i = 0; i < 26; i++) {
//			if(max_count < char_arr[i]) {
//				max_count = char_arr[i];
//				max_al = (char)(i + 65);
//			} else if (max_count == char_arr[i]) {
//				max_al = '?';
//			}
//		}
//		System.out.println(max_al);
		
//		for(int i = 0; i < w_len; i++) {
//			char alpabet = word.charAt(i);
//			int count = 1;
//			if(alpabet >= 97) alpabet -= 32;
//			for(int j = i + 1; j < w_len; j++) {
//				char next_alpabet = word.charAt(j);
//				if(next_alpabet >= 97) next_alpabet -= 32;
//				if(next_alpabet == alpabet) count++;
//			}
//			if(max_count < count) {
//				max_count = count;
//				max_al = alpabet;
//			} else if(max_count == count) {
//				max_al = '?';
//			}
//		}
//		System.out.println(max_al);
		
		
		//10988
//		Scanner scan = new Scanner(System.in);
//		String word = scan.nextLine();
//		int result = 1;
//		for(int i = 0; i < word.length()/2; i++) {
//			if(word.charAt(i) != word.charAt(word.length()-1-i)) {
//				result = 0;
//			}
//		}
//		System.out.println(result);
		
		//2444
//		Scanner scan = new Scanner(System.in);
//		int star_line = scan.nextInt();
//		for(int i = 1; i <= star_line; i++) {
//			for(int j = 1; j <= star_line - i; j++) {
//				System.out.print(" ");
//			}
//			for(int k = 1; k < i * 2; k++) {
//				System.out.print("*");
//			}
//			System.out.println();
//		}
//		for(int i = star_line - 1; i >= 1; i--) {
//			for(int j = 1; j <= star_line - i; j++) {
//				System.out.print(" ");
//			}
//			for(int k = 1; k < i * 2; k++) {
//				System.out.print("*");
//			}
//			System.out.println();
//		}
		
		//3003
//		Scanner scan = new Scanner(System.in);
//		//킹1 퀸1 룩2 비숍2 나이트2 폰8의 개수 입력(n n n n n n 형식)
//		int k = scan.nextInt();
//		int q = scan.nextInt();
//		int l = scan.nextInt();
//		int b = scan.nextInt();
//		int n = scan.nextInt();
//		int p = scan.nextInt();
//		System.out.print(1-k + " ");
//		System.out.print(1-q + " ");
//		System.out.print(2-l + " ");
//		System.out.print(2-b + " ");
//		System.out.print(2-n + " ");
//		System.out.print(8-p);
//		
//		String piece = scan.nextLine();
//		int[] piece_arr = new int[6];
//		int idx = 0;
//		for(int i = 0; i < piece_arr.length; i++) {
//			piece_arr[i] = (int)piece.charAt(idx) - 48;
//			idx += 2;
//		}
//		System.out.printf("%d %d %d %d %d %d", 1-piece_arr[0], 1-piece_arr[1], 2-piece_arr[2], 2-piece_arr[3], 2-piece_arr[4], 8-piece_arr[5]);
		
		
		//25083
//		System.out.println("         ,r'\"7");
//		System.out.println("r`-_   ,'  ,/");
//		System.out.println(" \\. \". L_r'");
//		System.out.println("   `~\\/");
//		System.out.println("      |");
//		System.out.println("      |");
		
		
		//11382
//		Scanner scan = new Scanner(System.in);
//		long A = scan.nextLong();
//		long B = scan.nextLong();
//		long C = scan.nextLong();
//		System.out.println(A + B + C);

	}

}
