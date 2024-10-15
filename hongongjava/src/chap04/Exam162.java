package chap04;

public class Exam162 {

	public static void main(String[] args) {
		// 주사위 2개를 던져서 합이 5가 되면 종료
		// 5가 아니면 (1,3) 이런 형식으로 주사위 눈 표시
		//몇 번만에 나왔는지 체크

		int count = 0;
		while(true) {
			int dice1 = (int)(Math.random() * 6 + 1);
			int dice2 = (int)(Math.random() * 6 + 1);
			System.out.printf("(%d,%d)\t", dice1, dice2);
			count++;
			if(dice1 + dice2 == 5) {
				System.out.println("종료   [반복 횟수: " + count + "회]");
				break;
			}
		}
		
		//주사위 눈이 1이면 1등 ~ 6이면 6등 출력
		int dice3 = (int)(Math.random() * 6 + 1);
//		System.out.println(dice3 + "등");
		int rank;
		switch(dice3){
			case 1 : rank = 1; break;
			case 2 : rank = 2; break;
			case 3 : rank = 3; break;
			case 4 : rank = 4; break;
			case 5 : rank = 5; break;
			default : rank = 6;
		}
		System.out.printf("주사위 값 : %d\n%d등입니다.", dice3, rank);
		
		//50에서 100까지 수 발생
		//90 이상이면 A, 80 이상이면 B, ....60이상 D, 나머지 F
		//switch문 이용
		int ranNum = (int)(Math.random() * (100 - 50 + 1) + 50);
		char chRank;
		switch(ranNum / 10) {
			case 9 : chRank = 'A'; break;
			case 8 : chRank = 'B'; break;
			case 7 : chRank = 'C'; break;
			case 6 : chRank = 'D'; break;
			default : chRank = 'F';
		}
		System.out.printf("점수: %d, 성적: %s", ranNum, chRank);
	}

}
