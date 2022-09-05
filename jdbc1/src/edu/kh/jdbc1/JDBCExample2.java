package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//1단계 : JDBC 객체 참조 변수 생성(jave.sql)
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 2단계 : 참조변수에 알맞은 객체 대입 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type ="jdbc:oracle:thin:@"; // JDBC 드라이버의 종류
			String ip ="localhost"; // DB 서버 컴퓨터 IP
			// localhost == 127.0.0.1 (loop back ip)
			
			String port =":1521"; // 포트번호
			//1521(기본값)
			
			String sid =":XE"; // DB 이름 
			String user ="kh_kjy";
			String pw ="kh1234";
//			Connection conn = null;
//			Statement stmt = null;
//			ResultSet rs = null;
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
			
			System.out.println("<입력 받은 급여보다 많이 받는(초과) 직원만 조회>");
			
			System.out.print("급여 입력 : ");
			int input = sc.nextInt();
			
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE WHERE SALARY > " + input +"ORDER BY SALARY DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d\n",empId,empName,salary);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
