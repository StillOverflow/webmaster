package chap13;
import java.util.*;
public class LinkedListMain {

	public static void main(String[] args) {
		//List 컬렉션
		//List 인터페이스의 LinkedList 클래스 다루는 법
		//중간에 잦은 변경이 있을 시, LinkedList가 효율적이지만 검색이나 순차적 사용에는 ArrayList가 효율적.
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new LinkedList<String>();
		
		long startTime;
		long endTime;
		
		startTime = System.nanoTime();
		for(int i = 0; i < 10000; i++) {
			list1.add(0, String.valueOf(i)); //만약 동일 공간에 계속 생성할 경우(순차적 생성)에는 ArrayList가 조금 더 빠름.
		}
		endTime = System.nanoTime();
		System.out.printf("ArrayList 걸린 시간: %dns\n", endTime-startTime);
		
		startTime = System.nanoTime();
		for(int i = 0; i < 10000; i++) {
			list2.add(0, String.valueOf(i));
		}
		endTime = System.nanoTime();
		System.out.printf("LinkedList 걸린 시간: %dns\n", endTime-startTime);
	}

}
