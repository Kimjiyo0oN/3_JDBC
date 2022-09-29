package edu.kh.jdbc.board.model.vo;

// 댓글 1개에 대한 값을 저장하는 VO
public class Comment {
	private int commentNo;          //댓글 번호 
	private String commentContent;  //댓글 내용
	private int memberNo;           //작성자 회원 번호 
	private String memberName;      //작성자 회원 이름
	private String createDate;      //작성일
	private int boardNo;            //작성된 게시글 번호(등록 시 이용)

	public Comment() {}

	/**
	 * @return the commentNo
	 */
	public int getCommentNo() {
		return commentNo;
	}

	/**
	 * @param commentNo the commentNo to set
	 */
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	/**
	 * @return the commentContent
	 */
	public String getCommentContent() {
		return commentContent;
	}

	/**
	 * @param commentContent the commentContent to set
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the boardNo
	 */
	public int getBoardNo() {
		return boardNo;
	}

	/**
	 * @param boardNo the boardNo to set
	 */
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	
}
