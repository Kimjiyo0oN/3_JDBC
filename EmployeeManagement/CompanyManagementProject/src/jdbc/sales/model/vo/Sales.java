package jdbc.sales.model.vo;

public class Sales {
	private int targetNo;
	private String targetDate;
	private int targetPrice;
	private int skinCRRatio;
	private int skinTGPrice;
	private int makeUPRatio;
	private int makeUPrice;
	private int bodyCRRatio;
	private int bodyCRPrice;
	private int hairCRRatio;
	private int hairCRPrice;
	private String teamCode;
	private String teamName;
	private String salesDate;
	private int salesAmount;
	private int totalPrice;
	private int totalAmount;
	private float salesPer;
	//private 
	private Product product;
	
	public Sales() {}

	public int getTargetNo() {
		return targetNo;
	}

	public void setTargetNo(int targetNo) {
		this.targetNo = targetNo;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public int getTargetPrice() {
		return targetPrice;
	}

	public void setTargetPrice(int targetPrice) {
		this.targetPrice = targetPrice;
	}

	public int getSkinCRRatio() {
		return skinCRRatio;
	}

	public void setSkinCRRatio(int skinCRRatio) {
		this.skinCRRatio = skinCRRatio;
	}

	public int getSkinTGPrice() {
		return skinTGPrice;
	}

	public void setSkinTGPrice(int skinTGPrice) {
		this.skinTGPrice = skinTGPrice;
	}

	public int getMakeUPRatio() {
		return makeUPRatio;
	}

	public void setMakeUPRatio(int makeUPRatio) {
		this.makeUPRatio = makeUPRatio;
	}

	public int getMakeUPrice() {
		return makeUPrice;
	}

	public void setMakeUPrice(int makeUPrice) {
		this.makeUPrice = makeUPrice;
	}

	public int getBodyCRRatio() {
		return bodyCRRatio;
	}

	public void setBodyCRRatio(int bodyCRRatio) {
		this.bodyCRRatio = bodyCRRatio;
	}

	public int getBodyCRPrice() {
		return bodyCRPrice;
	}

	public void setBodyCRPrice(int bodyCRPrice) {
		this.bodyCRPrice = bodyCRPrice;
	}

	public int getHairCRRatio() {
		return hairCRRatio;
	}

	public void setHairCRRatio(int hairCRRatio) {
		this.hairCRRatio = hairCRRatio;
	}

	public int getHairCRPrice() {
		return hairCRPrice;
	}

	public void setHairCRPrice(int hairCRPrice) {
		this.hairCRPrice = hairCRPrice;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public int getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getSalesPer() {
		return salesPer;
	}

	public void setSalesPer(float salesPer) {
		this.salesPer = salesPer;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
}
