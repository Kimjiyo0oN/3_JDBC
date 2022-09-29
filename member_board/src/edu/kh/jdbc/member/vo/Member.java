package edu.kh.jdbc.member.vo;

//VO(Value Object) : 값을 저장하는 용도의 객체
// -JDBC에서는 테이블의 한 행의 조회 결과 또는
//  삽입, 수정을 위한 데이터를 저장하는 용도의 객체
public class Member {
	private int memberNo;
	private String memberID;
	private String memberPw;
	private String memberName;
	private String memberGender;
	private String enrollDate;
	private String secessionFlag;
	
	public Member() {}
	
	public Member(String memberID, String memberPw, String memberName, String memberGender) {
		super();
		this.memberID = memberID;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberGender = memberGender;
	}
	
	/**
	 * @return the memberNo
	 */
	public int getMemberNo() {
		return memberNo;
	}



	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}



	/**
	 * @return the memberID
	 */
	public String getMemberID() {
		return memberID;
	}



	/**
	 * @param memberID the memberID to set
	 */
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}



	/**
	 * @return the memberPw
	 */
	public String getMemberPw() {
		return memberPw;
	}



	/**
	 * @param memberPw the memberPw to set
	 */
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}



	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}



	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}



	/**
	 * @return the memberGender
	 */
	public String getMemberGender() {
		return memberGender;
	}



	/**
	 * @param memberGender the memberGender to set
	 */
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}



	/**
	 * @return the enrollDate
	 */
	public String getEnrollDate() {
		return enrollDate;
	}



	/**
	 * @param enrollDate the enrollDate to set
	 */
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}



	/**
	 * @return the secessionFlag
	 */
	public String getSecessionFlag() {
		return secessionFlag;
	}



	/**
	 * @param secessionFlag the secessionFlag to set
	 */
	public void setSecessionFlag(String secessionFlag) {
		this.secessionFlag = secessionFlag;
	}

	@Override
	public String toString() {
		String gender; 
		if(memberGender.equals("M")) {
			gender ="남자";
		}else {
			gender ="여자";
		}
		return memberName +"님의 회원 정보 [회원 번호=" + memberNo + ", 아이디=" + memberID + ", 이름=" + memberName
				+ ", 성별=" + gender + ", 가입일=" + enrollDate + "]";
	}



	
	
	
}
