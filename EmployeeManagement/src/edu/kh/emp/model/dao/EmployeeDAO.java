package edu.kh.emp.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.emp.model.vo.Employee;

// DAO(Data Access Object, 데이터 접근 객체)
// -> 데이터베이스에 접근(연결)하는 객체
// --> JDBC 코드 작성
public class EmployeeDAO {

	// JDBC 객체 참조 변수로 필드 선언(Class 내부에서 공통 사용)
	private Connection conn; // 필드(Heap, 변수가 비어있을 수 없음)
	private Statement stmt;  // -> JVM이 지정한 기본값으로 초기화
	private ResultSet rs = null;   // -> 참조형의 초기값은 null
								   // -> 별도 초기화 안해도 된다!
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "kh_kjy";
	private String pw = "kh1234";
	
//	public void method() {
//		Connection conn2; // 지역변수(Stack, 변수가 비어있을 수 있음)
//	}

	/** 전체 사원 정보 조회 DAO
	 * @return emplist
	 */
	public List<Employee> selectAll() {
		// 1. 결과 저장용 변수 선언
		List<Employee> empList = new ArrayList<>();
		
		try {
			// 2. JDBC 참조 변수에 객체 대입
			// -> conn, stmt, rs에 객체 대입
			Class.forName(driver); // 오라클 jdbc 드라이버 객체 메모리 로드
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "SELECT EMP_ID , EMP_NAME , EMP_NO , EMAIL , PHONE , NVL(DEPT_TITLE, '부서없음') DEPT_TITLE, JOB_NAME, SALARY"
					+ " FROM EMPLOYEE"
					+ " LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)"
					+ " JOIN JOB USING (JOB_CODE)";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			//3. 조회 결과를 얻어
			// 
			//
			while(rs.next()) {
				int empId = rs.getInt("EMP_ID"); 
				// EMP_ID 컬럼은 문자열 컬럼이지만 저장된 값들이 모두 숫자형태 
				// -> DB에서 자동으로 형변환 진행해서 얻어옴 
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				empList.add(new Employee(empId, empName, empNo, email, phone, deptTitle, jobName, salary));
			}
			
			
			
		} catch(Exception e){
			e.printStackTrace();	
		} finally {
			try {
				
				//4. JDBC 객체 자원 반환
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 5. 결과 반환
		return empList;
		
	}
	public int addEmployee(int empId,String empName,String empNo,String email,String phone,String deptTitle,String jobName,int salary) {
		try {
			// 2. JDBC 참조 변수에 객체 대입
			// -> conn, stmt, rs에 객체 대입
			Class.forName(driver); // 오라클 jdbc 드라이버 객체 메모리 로드
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "INSERT All INTO EMPLOYEE(EMP_ID , EMP_NAME , EMP_NO , EMAIL , PHONE , DEPT_TITLE, JOB_NAME, SALARY)"
					+ " VALUES('" + empId + "', '" + empName + "', '" + empNo + "', '" + email + "', '" + phone + "',"
					+ "'" + deptTitle + "', '" + jobName + "', " + salary + ")";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			String sql1 = "";
			
			//stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			
		} catch(Exception e){
			e.printStackTrace();	
		} finally {
			try {
				
				//4. JDBC 객체 자원 반환
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return 1;
	}
}