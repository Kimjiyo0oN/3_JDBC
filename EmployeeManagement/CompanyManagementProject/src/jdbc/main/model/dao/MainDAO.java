package jdbc.main.model.dao;

import java.sql.Statement;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdbc.employee.vo.Employee;

import static jdbc.common.JDBCTemplate.*;

public class MainDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public MainDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**로그인 DAO
	 * @param conn
	 * @param employeeID
	 * @param employeePW
	 * @return loginEmployee
	 * @throws Exception
	 */
	public Employee login(Connection conn, String employeeID, String employeePW) throws Exception{
//		EMP_NO VARCHAR2(50) CONSTRAINT PK_EMP_TB PRIMARY KEY,
//		   EMP_ID VARCHAR2(50) NOT NULL,
//		   EMP_PW VARCHAR2(50) NOT NULL,
//		   TEAM_CODE VARCHAR2(50) NOT NULL,  ---FK_EMP_TEAM_CODE
//		   DEPT_CODE VARCHAR2(50) NOT NULL,  ---FK_EMP_DEPT_CODE   ---NULL 변경 해야함
//		   JOB_CODE VARCHAR2(50) NOT NULL,   ---FK_EMP_JOB_CODE
//		   EMP_NAME VARCHAR2(10) NOT NULL,
//		   EMP_SSN VARCHAR2(50) NOT NULL,
//		   HIRE_DATE DATE DEFAULT SYSDATE,
//		   ENT_DATE DATE,
//		   ENT_YN CHAR(1) DEFAULT 'N' CONSTRAINT CK_ENT_YN CHECK(ENT_YN IN('Y','N')),
//		   CONSTRAINT FK_EMP_TEAM_CODE FOREIGN KEY(TEAM_CODE) REFERENCES "TEAM_TB",
//		   CONSTRAINT FK_EMP_DEPT_CODE FOREIGN KEY(DEPT_CODE) REFERENCES "DEPT_TB",
//		   CONSTRAINT FK_EMP_JOB_CODE FOREIGN KEY(JOB_CODE) REFERENCES "JOB_TB"0
		Employee loginEmployee = null;
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, employeeID);
			pstmt.setString(2, employeePW);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginEmployee = new Employee();
				loginEmployee.setEmployeeNO(rs.getString("EMP_NO"));
				loginEmployee.setEmployeeID(rs.getString("EMP_ID"));
				loginEmployee.setTeamCode(rs.getString("TEAM_CODE"));
				loginEmployee.setDeptCode(rs.getString("DEPT_CODE"));
				loginEmployee.setJobCode(rs.getString("JOB_CODE"));
				loginEmployee.setEmployeeName(rs.getString("EMP_NAME"));
				loginEmployee.setDeptName(rs.getString("DEPT_NAME"));
				loginEmployee.setTeamName(rs.getString("TEAM_NAME"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginEmployee;
	}

	/**아이디 찾기 DAO
	 * @param conn
	 * @param employeeNO
	 * @return loginEmployee
	 * @throws Exception
	 */
	public Employee findID(Connection conn, String employeeNO) throws Exception{
		Employee loginEmployee = null;
		try {
			String sql = prop.getProperty("findID");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, employeeNO);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginEmployee = new Employee();
				loginEmployee.setEmployeeNO(rs.getString("EMP_NO"));
				loginEmployee.setEmployeeID(rs.getString("EMP_ID"));
				loginEmployee.setEmployeePW(rs.getString("EMP_PW"));
				loginEmployee.setEmployeeSSN(rs.getString("EMP_SSN"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginEmployee;
	}

	/**비밀번호 변경 DAO
	 * @param conn
	 * @param newPW1
	 * @param employeeNO
	 * @return result
	 * @throws Exception
	 */
	public int findPW(Connection conn, String newPW1, String EmployeeSSN) throws Exception{
		int result =0;
		try {
			String sql = prop.getProperty("findPW");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPW1);
			pstmt.setString(2, EmployeeSSN);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 로그인 할 수 있는 사원들 정보 리스트 저장용 DAO
	 * 비번 입력 3번 제한 저장용 변수까지 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Employee> loginList(Connection conn) throws Exception{
		List<Employee> loginList = new ArrayList<>();
		try {
			String sql = prop.getProperty("loginList");
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeNO(rs.getString("EMP_NO"));
				emp.setEmployeeID(rs.getString("EMP_ID"));
				emp.setEmployeePW(rs.getString("EMP_PW"));
				emp.setEmployeeSSN(rs.getString("EMP_SSN"));
				emp.setLoginNum(0);
				loginList.add(emp);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		return loginList;
	}
}
