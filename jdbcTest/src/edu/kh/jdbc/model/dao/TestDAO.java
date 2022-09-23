package edu.kh.jdbc.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.model.vo.TestVO;

public class TestDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//
	private Properties prop;
	
	public TestDAO() {
		
		// TestDAO 객체 생성시
		// test-query.xml 파일의 내용을 읽어와
		// Properties 객체에 저장 
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("test-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	<entry key = "insert">INSERT INTO TB_TEST VALUES(?,?,?)</entry>
//	<entry key = "update">UPDATE TB_TEST SET TEST_TITLE = ?,TEST_CONTENT=? WHERE TEST_NO = ?</entry>
	public int insert(Connection conn, TestVO testVO) throws SQLException{
		int result =0;
		
		try {
			// 2.SQL 작성(test-query.xml에 작성된 SQL 얻어오기)
			//   ->prop이 저장하고 있음!!
			
			String sql = prop.getProperty("insert");
			
			//3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ->
			
			//4. ?(위치홀더)에 알맞은 값 세팅
			pstmt.setInt(1, testVO.getTestNo());
			pstmt.setString(2, testVO.getTestTitle());
			pstmt.setString(3, testVO.getTestContent());
			
			//5.SQL(INSERT) 수행 후 결과 반환 받기
			//pstmt.executeQuery(); //-> SELECT 수행, ResultSet 반환
			result = pstmt.executeUpdate(); //-> DML의 수행, 반영된 행의 개수(int) 반환
			
		} finally { //무조건 실행 되기 때문에 result = pstmt.executeUpdate();에서 
			//오류가 발생 했을 때 close 하지 않고 
			//throw 되는 현상을 막기 위해 try finally을 해준다
			
			//6. 사용한 JDBC 객체 자원 반환(close())
			close(pstmt);
		}
		//7. SQL 수행 결과 반환
		return result;
	}
//	<entry key = "update">UPDATE TB_TEST SET TEST_TITLE = ?,TEST_CONTENT=? WHERE TEST_NO = ?</entry>
	public int upate(Connection conn, TestVO testVO) throws SQLException{
		int result =0;
		
		try {
			// 2.SQL 작성(test-query.xml에 작성된 SQL 얻어오기)
			//   ->prop이 저장하고 있음!!
			
			String sql = prop.getProperty("update");
			
			//3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ->
			
			//4. ?(위치홀더)에 알맞은 값 세팅
			pstmt.setString(1, testVO.getTestTitle());
			pstmt.setString(2, testVO.getTestContent());
			pstmt.setInt(3, testVO.getTestNo());
			
			//5.SQL(INSERT) 수행 후 결과 반환 받기
			//pstmt.executeQuery(); //-> SELECT 수행, ResultSet 반환
			result = pstmt.executeUpdate(); //-> DML의 수행, 반영된 행의 개수(int) 반환
			
		} finally { //무조건 실행 되기 때문에 result = pstmt.executeUpdate();에서 
			//오류가 발생 했을 때 close 하지 않고 
			//throw 되는 현상을 막기 위해 try finally을 해준다
			
			//6. 사용한 JDBC 객체 자원 반환(close())
			close(pstmt);
		}
		//7. SQL 수행 결과 반환
		return result;
	}

}
