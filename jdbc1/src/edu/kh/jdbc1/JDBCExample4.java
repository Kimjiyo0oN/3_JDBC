package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {
	public static void main(String[] args) {
		// 직급명, 급여를 입력 받아 
		// 해당 직급에서 입력 받은 급여보다 많이 받은 사원의
		// 이름, 직급명, 급여, 연봉 출력
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("직급명 : ");
			String jobName = sc.nextLine();
			
			System.out.print("급여 : ");
			int salary = sc.nextInt();
			
			// JDBC 참조 변수에 알맞은 객체 대입 
			Class.forName("oracle.jdbc.driver.OracleDriver");
						
			String type ="jdbc:oracle:thin:@"; // JDBC 드라이버의 종류
			String ip ="localhost"; // DB 서버 컴퓨터 IP
			// localhost == 127.0.0.1 (loop back ip)
					
			String port =":1521"; // 포트번호
			//1521(기본값)
						
			String sid =":XE"; // DB 이름 
			String user ="kh_kjy";
			String pw ="kh1234";
			
			conn = DriverManager.getConnection(type + ip +port + sid, user, pw);
	
			String sql = "SELECT EMP_NAME, JOB_NAME, SALARY , (SALARY*12) AS 연봉"
					+ " FROM EMPLOYEE"
					+ " LEFT JOIN JOB USING (JOB_CODE)"
					+ " WHERE JOB_NAME = '" + jobName + "' AND SALARY > " + salary;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<>();
			
			while(rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String jobName1 = rs.getString("JOB_NAME");
				int salary1 = rs.getInt("SALARY");
				int ann = rs.getInt("연봉");
				Employee emp = new Employee(empName,jobName1,salary1, ann);
				list.add(emp);
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			}else {
				for(Employee e : list) {
					System.out.println(e);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
}
