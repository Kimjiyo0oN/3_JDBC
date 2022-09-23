package edu.kh.jdbc.model.vo;
//<?xml version="1.0" encoding="UTF-8"?>
//<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
//<properties>
//	<comment>test-query.xml file</comment>
//	<entry key = "insert">INSERT INTO TB_TEST VALUES(?,?,?)</entry>
//	<entry key = "update">UPDATE TB_TEST SET TEST_TITLE = ?,TEST_CONTENT=? WHERE TEST_NO = ?</entry>
//	<entry key = "select">SELECT * FROM TB_TEST</entry>
//	<entry key = "selectNo">SELECT COUNT(*) FROM TB_TEST WHERE TEST_NO = ?</entry>
//</properties>
//
//<!-- 번호, 제목, 내용을 입력받아 번호가 일치하는 행의 제목, 내용 수정
//
//수정 성공 시->수정되었습니다.
//수정 실패 시->일치하는 번호가 없습니다.
//예외 발생 시->수정 중 예외가 발생하였습니다 -->
public class TestVO {
	private int testNo;
	private String testTitle;
	private String testContent;
	
	public TestVO() {}

	public TestVO(int testNo, String testTitle, String testContent) {
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
