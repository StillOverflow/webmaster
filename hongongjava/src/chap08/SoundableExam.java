package chap08;

public class SoundableExam {
	//p407
	private static void printSound(Soundable soundable) {
		System.out.println(soundable.sound());
	}
	
	public static class Cat implements Soundable{ //해당 클래스에서 바로 쓰려면 static 써야 함.
		public String sound() {
			return "야옹";
		}
	}
	
	public static class Dog implements Soundable{ 
		public String sound() {
			return "멍멍";
		}
	}

	public static void main(String[] args) {
		Cat cat = new Cat();
		printSound(cat);
		printSound(new Dog());

	}

}
