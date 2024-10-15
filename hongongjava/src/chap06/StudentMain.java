package chap06;

public class StudentMain {

	public static void main(String[] args) {
//		//이 클래스에서 쓸 수 있는 객체 생성
//		Student st1 = new Student("240927", "강자바");
//		System.out.println(st1); //객체 참조 변수가 되어 위치 반환.
//		System.out.println(st1.stNo);
//		System.out.println(st1.name);
//		st1.study();
//
//		Student st2 = new Student("200902", "김땡땡");
//		System.out.println(st2);
//		System.out.println(st2.stNo);
//		System.out.println(st2.name);
//		st2.study();
		
		Student[] students = {
				new Student("1", "aaa", 67, 90, 50),
				new Student("2", "bbb", 95, 70, 80),
				new Student("3", "ccc", 95, 90, 100),
				new Student("4", "ddd", 42, 50, 68),
				new Student("5", "eee", 100, 100, 100)				
		};
		
//		Student[] students = {st1, st2, st3, st4, st5};
		
		System.out.println(" 번호 |\t이름 |\t국어 |\t영어 |\t수학 |\t총점 |\t평균 |\t등급");
		for(Student i : students) {
			System.out.printf("%3s\t%s\t%3d\t%3d\t%3d\t%3d\t%3.0f\t%-2s\n", i.stNum, i.name, i.sub_lang, i.sub_eng, i.sub_math, i.sum(), i.avg(), i.rank());			
		}
	}

}
