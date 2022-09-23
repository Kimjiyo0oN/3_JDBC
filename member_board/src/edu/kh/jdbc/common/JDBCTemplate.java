package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/* DB 연결(Connection 생성), 자동 커밋 off
	 * 트랜잭션 제어, JDBC 객체 자원 반환(close)
	 * 
	 * 이러한 JDBC에서 반복 사용되는 코드를 모아둔 클래스 
	 * 
	 * *static 공유 메모리에 저장
	 * 
	 * *모든 필드, 메소드가 static *
	 * -> 어디서든지 클래스명, 필드명 / 클래스명.메서드명
	 *    호출가능 (별도 객체 생성X)
	 * 
	 * */
	
	private static Connection conn = null;
	//-> Static 메서드에서 필드를 사용하기 위해서는 
	//   필드도 static 필드이여야한다
	
	
	/**DB 연결 정보를 담고 있는 Connection 객체 생성 및 반환 메서드 
	 * @return conn
	 */
	public static Connection getConnection() {
		try {
			//현재 커넥션 객체가 없을 경우 -> 새 커넥션 객체를 생성 
			if(conn == null || conn.isClosed()) {
				// conn.isClosed() : 커넥션이 close 상태이면 true 반환 
				
				Properties prop = new Properties();
				// Map<String, String> 형태의 객체, XML 입출력 특화
				
				// driver.xml 파일 읽어오기
				prop.loadFromXML(new FileInputStream("driver.xml"));
				//->XML 파일에 작성된 내용이 Properties 객체에 모두 저장됨
				
				// XML에서 읽어온 값을 모두 String 변수에 저장 
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				
				//커넥션 생성
				Class.forName(driver); //oracle.jdbc.driver.OracleDriver
				
				// DriverManager를 이용해 Connection 객체 생성
				conn = DriverManager.getConnection(url, user, password);
				
				//개발자가 직접 트랜잭션을 제어할 수 있도록 
				//자동 커밋 비활성화
				
				conn.setAutoCommit(false);
				
			}
		}catch(Exception e ) {
			System.out.println("[Connection 생성 중 예외 발생]");
			e.printStackTrace();
		}
		return conn;
	}
	/** Connection 객체 자원 반환 메서드 
	 * @param conn
	 */
	public static void close(Connection conn) {
		try { //isClosed()이 닫혀 있을 때 true -> !conn.isClosed()은 열려있을 때 true
			//전달 받은 conn이 
			//참조하는 Connection 객체가 있고
			// 그 Connection 객체가 close 상태가 아니라면
			if(conn != null && !conn.isClosed())conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	/** Statement, PreparedStatement 객체 자원 반환 메서드 
	 * (다형성,동적바인딩)
	 * @param conn
	 */
	public static void close(Statement stmt) {
		try { //isClosed()이 닫혀 있을 때 true -> !conn.isClosed()은 열려있을 때 true
			//전달 받은 conn이 
			//참조하는 Connection 객체가 있고
			// 그 Connection 객체가 close 상태가 아니라면
			if(stmt != null && !stmt.isClosed())stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/**ResultSet 객체 자원 반환 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try { //isClosed()이 닫혀 있을 때 true -> !conn.isClosed()은 열려있을 때 true
			//전달 받은 conn이 
			//참조하는 Connection 객체가 있고
			// 그 Connection 객체가 close 상태가 아니라면
			if(rs != null && !rs.isClosed())rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/** 트랜잭션 Commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			//연결되어 Connection 객체일 경우에만 Commit 진행 
			if(conn != null && !conn.isClosed()) conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/** 트랜잭션 Rollback 메서드
	 * @param conn
	 */  
	public static void rollback(Connection conn) {
		try {
			//연결되어 Connection 객체일 경우에만 Commit 진행 
			if(conn != null && !conn.isClosed()) conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * conn.setAutoCommit(false); //AutoCommit 비활성화
	// -> AutoCommit 비활성화해도 
	// conn.close() 구문이 수행되면 자동으로 Commit이 수행됨 
	//--> close() 수행 전에 트랜잭션 제어 코드를 작성해야함 
	// SQL 수행 후 결과 반환 받기
	result = pstmt.executeUpdate();
	//executeUpdate() : DML(INSERT, UPDATE, DELETE) 수행 후 결과 행 개수 반환 
	//executeQuery() : SELECT 수행 후 ResultSet 반환 
			
	// *** 트랜잭션 제어 처리 ***
	// -> DML 성공 여부에 따라서 commit, rollback 제어 
	if(result >0) conn.commit();  //DML 성공시 commit
	else          conn.rollback(); //DML 실패시 rollback
	*/
}
