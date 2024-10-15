package chap06;

public class Book {
	//도서 객체 생성 class
	//- 책 제목, 도서 번호, 도서 가격
	//- 외부에서 필드 접근 막고, getter/setter 사용해서 접근하도록 함.
	private String name;
	private int number;
	private int price;
	
	Book(String name, int number, int price){
		this.name = name;
		this.number = number;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
