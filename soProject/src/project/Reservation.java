package project;

public class Reservation {
	//add 메소드 시 입력해야할 값: reserveId, serviceYn 제외 6개 모두 입력
    private String reserveId; //디폴트 시퀀스 적용
    private String roomNumber;
    private String guestName;
    private int guestNumber;
    private String guestTel;
    private String checkinDate; //입력양식: '2024-00-00 12:00'
    private String checkoutDate; //입력양식: '2024-00-00 12:00'
    private String serviceYn; //디폴트 'n' => 서비스 이용 시 'y' 변경 필요
    
	public String getReserveId() {
		return reserveId;
	}
	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public int getGuestNumber() {
		return guestNumber;
	}
	public void setGuestNumber(int guestNumber) {
		this.guestNumber = guestNumber;
	}
	public String getGuestTel() {
		return guestTel;
	}
	public void setGuestTel(String guestTel) {
		this.guestTel = guestTel;
	}
	public String getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}
	public String getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public String getServiceYn() {
		return serviceYn;
	}
	public void setServiceYn(String serviceYn) {
		this.serviceYn = serviceYn;
	}
    
}
