package jdbc.sales.model.vo;

public class Stock {
	private int stockNO;
	private String stockSt;
	private String stockAmount;
	private String stockSTDate;
	private String confirmYN;
	private String teamNM;
	private String teamCode;
	private String targetTeamCode;
	
	private Product product;
	
	public Stock() {}

	public int getStockNO() {
		return stockNO;
	}
	
	public void setStockNO(int stockNO) {
		this.stockNO = stockNO;
	}

	public String getStockSt() {
		return stockSt;
	}

	public void setStockSt(String stockSt) {
		this.stockSt = stockSt;
	}

	public String getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(String stockAmount) {
		this.stockAmount = stockAmount;
	}

	public String getStockSTDate() {
		return stockSTDate;
	}

	public void setStockSTDate(String stockSTDate) {
		this.stockSTDate = stockSTDate;
	}

	public String getConfirmYN() {
		return confirmYN;
	}

	public void setConfirmYN(String confirmYN) {
		this.confirmYN = confirmYN;
	}

	public String getTeamNM() {
		return teamNM;
	}

	public void setTeamNM(String teamNM) {
		this.teamNM = teamNM;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTargetTeamCode() {
		return targetTeamCode;
	}

	public void setTargetTeamCode(String targetTeamCode) {
		this.targetTeamCode = targetTeamCode;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
}
