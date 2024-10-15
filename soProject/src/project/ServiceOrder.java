package project;

public class ServiceOrder {
	//add 메소드 시 필수 입력: service_id, quantity
	//           선택 입력(null 가능): name, orderTel, requiredDate, memo
    private String orderId; //디폴트 시퀀스 적용
    private String orderDate; //to_CHAR() sysdate
    private String name; //디폴트 ServiceMember memberName 변경가능
    private String orderRoom; //Reservation or ServiceMember roomNumber
	private String orderTel; //디폴트 Reservation guestTel 변경가능
    private int serviceId; 
	private String serviceName; //Services serviceName
    private int quantity;
    private int servicePrice; //계산 필요
    private String usedCoupon; //Reservataion currentCoupon 
	private String requiredDate; //디폴트 Reservataion checkinDate 변경가능
    private String memo;
    private String completedYn; //디폴트 'n'
    private String completedDate; //to_CHAR() sysdate
    
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderRoom() {
		return orderRoom;
	}
	public void setOrderRoom(String orderRoom) {
		this.orderRoom = orderRoom;
	}
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderTel() {
		return orderTel;
	}
	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(int servicePrice) {
		this.servicePrice = servicePrice;
	}
    public String getUsedCoupon() {
		return usedCoupon;
	}
	public void setUsedCoupon(String usedCoupon) {
		this.usedCoupon = usedCoupon;
	}
	public String getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCompletedYn() {
		return completedYn;
	}
	public void setCompletedYn(String completedYn) {
		this.completedYn = completedYn;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
    
}
