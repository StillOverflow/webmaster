package project;

public class ServiceMember {
	//add 메소드 시 입력해야할 값: password
    private String memberId; //Reservation reserveId
    private String memberName; //Reservation guestName
	private String roomNumber; //Reservation roomNumber
    private String password;
    private String currentCoupon;
    private int couponDiscountPercent;
    private String authority;
    private String availability;
    
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
    public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCurrentCoupon() {
		return currentCoupon;
	}
	public void setCurrentCoupon(String currentCoupon) {
		this.currentCoupon = currentCoupon;
	}
	public int getCouponDiscountPercent() {
		return couponDiscountPercent;
	}
	public void setCouponDiscountPercent(int couponDiscountPercent) {
		this.couponDiscountPercent = couponDiscountPercent;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
}
