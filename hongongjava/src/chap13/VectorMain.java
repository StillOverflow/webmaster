package chap13;
import java.util.*;
public class VectorMain {

	public static void main(String[] args) {
		//List 컬렉션
		//List 인터페이스의 Vector 클래스 다루는 법
		//Vector : ArrayList와 유사하지만 멀티 스레드 환경에서 안전함(동시에 실행하지 않고, 잠궜다가 메소드 스레드 끝나고 다음 스레드 실행)
		List<Board> list = new Vector<Board>();
		
		list.add(new Board("제목1", "내용1", "글쓴이1"));
		list.add(new Board("제목2", "내용2", "글쓴이2"));
		list.add(new Board("제목3", "내용3", "글쓴이3"));
		list.add(new Board("제목4", "내용4", "글쓴이4"));
		list.add(new Board("제목5", "내용5", "글쓴이5"));
		
		for(int i = 0; i < list.size(); i++) {
			Board board = list.get(i); //주소만 가져옴.
			System.out.printf("%d번 게시글 : %s\t%s\t%s\n",i , board.subject, board.content, board.writer);
		}
		
		System.out.println("------------------------------");
		list.remove(2);
		list.remove(1);
		int i = 0;
		for(Board board : list) { //내부 클래스의 필드나 메소드가 필요한 경우 향상된 for문이 나을 수 있음.
			System.out.printf("%d번 게시글 : %s\t%s\t%s\n",i , board.subject, board.content, board.writer);
			i++;
		}
		
		//해시코드
		Board bd = new Board("제목", "내용", "글쓴이");
		System.out.println(list.get(1).toString());
		System.out.println(bd);
		System.out.println(bd.hashCode());
		System.out.println(bd.toString()); //기본 Object의 toString()메소드 재정의 시 출력 가능
		
	}

}
