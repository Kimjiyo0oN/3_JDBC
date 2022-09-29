package jdbc.sales.model.vo;

public class Order {
	private String orderNo;
	private int orderAmount;
	private String orderDate;
	private String orderCode;
	private String orderTeam;
	
	private Product product;
	
	public Order() {}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderTeam() {
		return orderTeam;
	}

	public void setOrderTeam(String orderTeam) {
		this.orderTeam = orderTeam;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
