package chap05;

public class Exam195 {

	public static void main(String[] args) {
		//참조타입의 번지 비교(==)
		//데이터(문자열) 비교(equals())
		
		String str1 = "문자열";
		String str2 = "문자열";
		
		if(str1 == str2) {
			System.out.println("str1과 str2는 참조가 같습니다.");
		} else {
			System.out.println("str1과 str2는 참조가 같지 않습니다.");
		}
		
		if(str1.equals(str2)) {
			System.out.println("str1의 값과 str2의 값은 같습니다.");
		}
		
		String str3 = new String("문자열");
		String str4 = new String("문자열");
		
		if(str3 == str4) {
			System.out.println("str3과 str4는 참조가 같습니다.");
		} else {
			System.out.println("str3과 str4는 참조가 같지 않습니다.");
		}

		if(str3.equals(str4) & str3.equals(str1)) {
			System.out.println("str3의 값과 str4의 값은 같습니다.\nstr3의 값과 str1의 값도 같습니다.");
		}
		
		
		
		
	} //end main

}
