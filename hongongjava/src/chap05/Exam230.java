package chap05;
import java.util.Calendar;
public class Exam230 {

	public static void main(String[] args) {
		//날짜와 시간 얻기
		Calendar now = Calendar.getInstance();
		System.out.println(now); //Calendar 객체에서 쓸 수 있는 값들 볼 수 있음
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int date = now.get(Calendar.DATE);
		int day = now.get(Calendar.DAY_OF_WEEK);
		String day_str = "";
		switch(day) {
		case 1 : day_str = "일요일"; break;
		case 2 : day_str = "월요일"; break;
		case 3 : day_str = "화요일"; break;
		case 4 : day_str = "수요일"; break;
		case 5 : day_str = "목요일"; break;
		case 6 : day_str = "금요일"; break;
		case 7 : day_str = "토요일"; break;
		}
		System.out.printf("지금은 %d년 %02d월 %02d일 %d요일\n", year, month + 1, date, day);
		System.out.printf("지금은 %d년 %02d월 %02d일 %s\n", year, month + 1, date, day_str);
	}

}
