package jdbc.main.model.service;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static jdbc.common.JDBCTemplate.*;
import jdbc.employee.vo.Employee;
import jdbc.main.model.dao.MainDAO;

public class MainService {
	private MainDAO dao = new MainDAO();
	
	private Scanner sc = new Scanner(System.in);
	
	/** 로그인 서비스
	 * @param employeeID
	 * @param employeePW
	 * @return loginEmployee
	 * @throws Exception
	 */
	public Employee login(String employeeID, String employeePW) throws Exception{
		
		Connection conn = getConnection();
		
		Employee loginEmployee = dao.login(conn,employeeID, employeePW);
		
		close(conn);
		
		return loginEmployee;
	}

	/**아이디 찾기 서비스
	 * @param employeeNO
	 * @return loginEmployee
	 * @throws Exception
	 */
	public Employee findID(String employeeNO) throws Exception{
		Connection conn = getConnection();
		
		Employee loginEmployee = dao.findID(conn,employeeNO);
		
		close(conn);
		return loginEmployee;
	}

	/**비밀번호 변경 서비스
	 * @param employeeNO
	 * @return result
	 * @throws Exception
	 */
	public String findPW(String EmployeeSSN) throws Exception{
		Connection conn = getConnection();
		int result =0;
		String pw =null;
		System.out.println("\n[비밀번호 변경]");
		try {
			String newPW1 = null;
			String newPW2 = null;
			while(true) {		
				System.out.print("새 비밀번호 : ");
				newPW1 = sc.next();
				
				System.out.print("새 비밀번호 확인: ");
				newPW2 = sc.next();
				if(newPW1.equals(newPW2)){
					break;
				}else {
					System.out.println("새 비밀번호가 일치하지 않습니다.");
				}
			}
			result = dao.findPW(conn,newPW1,EmployeeSSN);
			if(result>0) {
				commit(conn);
				pw =newPW1;
			}
			else rollback(conn);
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return pw;
	}

	/**직원들 아이디, 비번, 주민번호를 미리 들고와서 로그인할 때 비번 3번 오류 시 
	 * 주민 번호를 입력해서 비번을 변경하지 못하면 로그인할수 없게 제한을 둠
	 * @return loginList
	 * @throws Exception
	 */
	public List<Employee> loginList() throws Exception{
		Connection conn = getConnection();
		
		List<Employee> loginList = dao.loginList(conn);
		if(loginList.isEmpty()) System.out.println("직원이 존재하지 않는 경우는 없음");
		
		close(conn);
		return loginList;
	}
}
