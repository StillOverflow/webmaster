package book_manager;

public class Book {
	//필드는 외부 접근 막아야 함.(private / getter / setter)
	private String bnum;
	private String title;
	private String writer;
	private int price;
	
	public String getBnum() {
		return bnum;
	}
	public void setBnum(String bnum) {
		this.bnum = bnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title + "\t" + writer + "\t" + price + "\t" + bnum;
	}
	
}
