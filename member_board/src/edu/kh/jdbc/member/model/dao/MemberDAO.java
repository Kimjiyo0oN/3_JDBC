package edu.kh.jdbc.member.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;

public class MemberDAO {
	
	//필드(== 멤버  변수)
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MemberDAO() { //기본 생성자
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** 회원 목록 조회 DAO
	 * @param conn
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn) throws Exception{
		//결과 저장용 변수 선언
		List<Member> memberList = new ArrayList<>();
		try {
			//SQL 얻어오기
			String sql = prop.getProperty("selectAll"); 
			
			//Statement 객체 생성
			stmt = conn.createStatement();
			// SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);
			
			//반복문(While)을 이용해서 조회 결과의 각 행에 접근 후 
			//컬럼 값을 얻어와 Member 객체에 저장 후 List에 추가
//			<entry key = "selectAll">SELECT MEMBER_NO,MEMBER_ID, MEMBER_NM, MEMBER_GENDER
//					FROM "MEMBER"
//					WHERE SECESSION_FL ='N'
//					ORDER BY MEMBER_NO DESC;</entry>
			while (rs.next()) {
				Member member = new Member();
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberID(rs.getString("MEMBER_ID"));
				member.setMemberName(rs.getString("MEMBER_NM"));
				member.setMemberGender(rs.getString("MEMBER_GENDER"));
				memberList.add(member);
			}
			
		}finally {
			//JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		//조회 결과를 옮겨 담은 List 반환 
		
		return memberList;
	}

	/**회원 정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member member) throws Exception{
		
		int result = 0;
		
		try {
//			<entry key = "updateMember">UPDATE "MEMBER"
//					SET MEMBER_NM = ?,
//					MEMBER_GENDER =?
//					WHERE MEMBER_NO = ?</entry>
			String sql = prop.getProperty("updateMember");
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1,member.getMemberName());
			pstmt.setString(2,member.getMemberGender());
			pstmt.setInt(3, member.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param currentPw
	 * @param newPw1
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Connection conn, String currentPw, String newPw1, int memberNo) throws Exception{
		
		int result = 0;
		
		try {
//			<entry key = "updatePw">UPDATE "MEMBER" SET
//			MEMBER_PW = ?
//			WHERE MEMBER_NO = ? 
//			AND MEMBER_PW = ?</entry>
			String sql = prop.getProperty("updatePw");
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1,newPw1);
			pstmt.setInt(2,memberNo);
			pstmt.setString(3,  currentPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param memberPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, String memberPw, int memberNo) throws Exception{
		int result =0;
		try {
//			<entry key = "secession">UPDATE "MEMBER" SET
//					SECESSION_FL = 'Y'
//					WHERE MEMBER_NO = ?
//					AND MEMBER_PW = ?</entry>
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,memberNo);
			pstmt.setString(2,memberPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
