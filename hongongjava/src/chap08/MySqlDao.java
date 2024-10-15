package chap08;

public class MySqlDao implements DataAccessObject {
	String name = "MySql DB";
	
	public void select() {
		System.out.println(name + "에서 검색");
	}
	public void insert() {
		System.out.println(name + "에 삽입");
	}
	public void update() {
		System.out.println(name + "에 수정");
	}
	public void delete() {
		System.out.println(name + "에서 삭제");
	}
}