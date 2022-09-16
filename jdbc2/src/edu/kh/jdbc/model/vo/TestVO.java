package edu.kh.jdbc.model.vo;

public class TestVO {
	private int testNo;
	private String testTitle;
	private String testContent;
	
	public TestVO() {}
	
	public TestVO(int testNo,String testTitle,String testContent) {
		super();
		this.testNo = testNo;
		this.testTitle = testTitle;
		this.testContent = testContent;
	}
	
	/**
	 * @return the testNo
	 */
	public int getTestNo() {
		return testNo;
	}
	/**
	 * @param testNo the testNo to set
	 */
	public void setTestNo(int testNo) {
		this.testNo = testNo;
	}
	/**
	 * @return the testTitle
	 */
	public String getTestTitle() {
		return testTitle;
	}
	/**
	 * @param testTitle the testTitle to set
	 */
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	/**
	 * @return the testContent
	 */
	public String getTestContent() {
		return testContent;
	}

	/**
	 * @param testContent the testContent to set
	 */
	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}


	@Override
	public String toString() {
		return "TestVO [testNo=" + testNo + ", testTitle=" + testTitle + ", testContent=" + testContent + "]";
	}
	
	
}
