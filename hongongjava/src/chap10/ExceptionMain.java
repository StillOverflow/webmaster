package chap10;

public class ExceptionMain {

	public static void main(String[] args) {
		//예외처리
		try {
			Class clazz = Class.forName("java.lang.String2");
			System.out.println("clazz");
		} catch (ClassNotFoundException e) {
			System.out.println("class를 찾지 못했어요.");
		}
		
		try {
			String strNum = "123ㅇ";
			int num = Integer.parseInt(strNum);
			System.out.println("변경 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("변경 못함.");
		}

	}

}
