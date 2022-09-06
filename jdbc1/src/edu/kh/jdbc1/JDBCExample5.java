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

public class JDBCExample5 {
	public static void main(String[] args) {
		// 입사일을 입력 ("2022-09-06")받아 
		// 입력 받은 값 보다 먼저 입사한 사람의 
		// 이름, 입사일, 성별(M,F) 조회
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("입사일 입력(YYYY-MM-DD) : ");
			String input = sc.next();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "kh_kjy", "kh1234");
			
			String sql = "SELECT EMP_NAME , TO_CHAR(HIRE_DATE, 'YYYY\"년\" MM\"월\" DD\"일\"') 입사일, DECODE(SUBSTR(EMP_NO, 8,1),1 ,'M','F') 성별"
					+ " FROM  EMPLOYEE"
					+ " WHERE HIRE_DATE < TO_DATE('" + input + "') ORDER BY 입사일";
			 
			//문자열 내부에 쌍따옴표 작성 시 \" 로 작성해야한다! (Escape 문서)
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<>();
			
			while(rs.next()) {
				Employee emp = new Employee(); // 기본 생성자로 Employee 객체 생성 
											   // 필드 초기화 X
											   // setter를 이용해서 하나씩 세팅 
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setHireDate(rs.getString("입사일"));
				emp.setGender(rs.getString("성별").charAt(0));
				
				list.add(emp);
			}
			
			if(list.isEmpty()) { //if(list.size() == 0)
				System.out.println("조회되지 않습니다");
			}else {
				for(int i = 0; i < list.size(); i++) { //for(Employee e : list)
					//System.out.println(e.getEmpName()+ " / " + e.getHireDate() + " / " + e.getGender());
					System.out.printf("%02d) %s / %s / %c\n", i+1, list.get(i).getEmpName(),list.get(i).getHireDate(), list.get(i).getGender());
				}
			}
			
			
		} catch (Exception e) {
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
