package chap13;
import java.util.*;
public class HashMapMain {

	public static void main(String[] args) {
		//Map : 키와 값으로 구성된 entry를 저장하는 컬렉션.
		//HashMap : Map 인터페이스를 구현한 대표적 컬렉션.
		
		//Map 컬렉션 객체 생성
		//Java10 이상 버전에서 var타입 Map 쓸 수도 있기는 함.
		//var 선언 시 내부 타입을 바로 알 수 없어 비선호하지만 코드가 간단해짐.
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//Map 컬렉션에 객체 저장
		map.put("김자바", 30);
		map.put("강자바", 30);
		map.put("김백백", 80);
		map.put("강자바", 35); //키가 같으므로 덮어쓰기 됨. (키 타입 String 외에 사용할 경우 중복값 확인 위해 hashCode, equals 메소드 재정의 필요.)
		System.out.println("총 Entry 수 : " +  map.size());
		
		//객체 찾기
		System.out.println("강자바 키의 값: " + map.get("강자바"));
		
		//객체 처리
		Set<String> keySet = map.keySet(); //map의 key들을 Set에 저장.
		System.out.println("------ while문 사용 ------");
		Iterator<String> keyIterator = keySet.iterator();
		while(keyIterator.hasNext()) {
			String key = keyIterator.next();
			int value = map.get(key);
			System.out.println(key + " " + value);
		}
		System.out.println("------ for문 사용 ------");
		for(String key : keySet) {
			System.out.println(key + " " +map.get(key));
		}
		
		//객체 삭제
		map.remove("강자바");
		System.out.println("총 Entry 수 : " +  map.size());
		System.out.println("------ entrySet ------");
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet(); //map의 Entry(키와 값)들을 Set에 저장.
		Iterator<Map.Entry<String, Integer>> entryIterator = entrySet.iterator();
		while(entryIterator.hasNext()) {
			Map.Entry<String, Integer> entry = entryIterator.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println(key + " " + value);
		}
		
		//객체 전체 삭제(map의 모든 Entry 삭제)
		map.clear();
		System.out.println("총 Entry 수 : " +  map.size());
		
	}

}
