package chap02;

public class Escape88 {

	public static void main(String[] args) { //void: 리턴값 없음
			System.out.println("번호\t이름\t직업");
			System.out.print("행 단위 출력\n");
			System.out.print("행 단위 출력\n");
			System.out.println("우리는 \"개발자\"입니다.");
			System.out.println("봄\\여름\\가을\\겨울");
			
			//p91 논리 타입 리터럴 저장 변수: boolean
			//예제에 시작합니다 출력되도록 변경
			boolean stop = true;
			if(!stop) {
				System.out.println("중지합니다.");
			} else {
				System.out.println("시작합니다.");
			}
			
			//p103 타입 변환
			byte byteVal1 = 10;
			byte byteVal2 = 20;
//			byte result = byteVal1 + byteVal2;
			int result = byteVal1 + byteVal2;
			System.out.println(result);
			
			char charVal1 = 'A';
			char charVal2 = 1;
			result = charVal1 + charVal2;
			System.out.println(result);
			System.out.println((char)result);
			
			int intVal1 = 10;
			int intVal2 = intVal1 / 4;
			System.out.println(intVal2);
			double doubResult1 = intVal1 / 4.0;
			System.out.println(doubResult1);
			double doubResult2 = (double)intVal1 / 4;
			System.out.println(doubResult2);
			
			//문자 타입 변환
			String str1 = "1000";
			int value = Integer.parseInt(str1);
			System.out.println(value + 2);
			System.out.println(String.valueOf(value) + 2);
			
			//p106 문자열에서 숫자, 숫자에서 문자열 변환
			//parse~ 이용해 문자열에서 기본타입 숫자로 강제전환
			intVal1 = Integer.parseInt("10");
			double doubleVal1 = Double.parseDouble("3.14");
			boolean boolVal1 = Boolean.parseBoolean("true");
			
			System.out.println("value1: " + (intVal1 + 100));
			System.out.println("value2: " + (doubleVal1 + 100));
			System.out.println("value3: " + boolVal1 + "\n");
			System.out.println("str: " + String.valueOf(boolVal1) + " boolean");
			
			//p109. 5번
			char c1 = 'a';
			int c2 = c1 + 1;
			System.out.println((char)c2);
			System.out.println(c2);
			
			//p109. 6번
			int x = 5;
			int y = 2;
			double xyResult = x / (double)y;
			System.out.println(xyResult);
			
			//p110. 9번
			long v1 = 2L;
			float v2 = 1.8f;
			double v3 = 2.5;
			String v4 = "3.9";
			int v_result = (int)(v1 + v2 + v3 + (int)Double.parseDouble(v4));
			System.out.println(v_result);
			
			//printf: 지정한 형식대로 반환. index 자동 생성되지만 지정해도 됨.
			System.out.printf("%1$s, %2$s", v1, v2);
	} //end main

}//end class
