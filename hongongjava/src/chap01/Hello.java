package chap01;

public class Hello {

	public static void main(String[] args) {
		System.out.println("Hello java");
		char cha1 = 'A';
		System.out.println(cha1);
		int int1 = cha1;
		System.out.println(int1); //char는 문자로 보여주지만 사실 코드로 이루어진 정수타입.
		int int2 = '나'; //""는 String 문자열이므로 char타입에 저장 불가. ''에 한 글자만 가능.
		System.out.println(int2);
		String str1 = "나나나";
		System.out.println(str1);
	}

}
