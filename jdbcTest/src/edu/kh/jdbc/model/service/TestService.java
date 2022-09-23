package edu.kh.jdbc.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.model.dao.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

public class TestService {

	private TestDAO dao = new TestDAO();
	
	public int insert(List<TestVO> list) throws Exception{
		Connection conn = getConnection();
		List<Integer> result = new ArrayList<>();
		int res =0;
		
		try {
			for(int i = 0; i < list.size(); i++) {
				int add = dao.insert(conn, new TestVO(list.get(i).getTestNo(), list.get(i).getTestTitle(),list.get(i).getTestContent()));
				result.add(i, add); 
			}
			for(int i =0; i < result.size(); i++) {
				res += result.get(i);
			}
			if(res == result.size()) {//모두 insert 성공한 경우
				commit(conn);
				res = 1;
			}else {
				rollback(conn);
				res = 0; 
			}
			
			
		} catch (SQLException e) { //dao 수행 중 예외 발생시 
			rollback(conn);
			//-> 실패된 데이터를 DB에 삽입하지 않음
			//-> DB에는 성공된 데이터만 저장이 된다
			// == DB에 저장된 데이터의 신뢰도가 상승한다
			e.printStackTrace();
			// Run2 클래스로 예외를 전달 할 수 있도록 예외 강제 발생 
			throw new Exception("DAO 수행 중 예외가 발생했다.");
		} finally {//무조건 conn 반환하기 
			close(conn);
		}
		
		return res;
	}

	public int update(TestVO testVO) throws Exception{
		Connection conn = getConnection();
		
		int result =0;
		
		try {
			
			result = dao.upate(conn, testVO);
			
			if(result == 1 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		} catch (SQLException e) { //dao 수행 중 예외 발생시 
			rollback(conn);
			//-> 실패된 데이터를 DB에 삽입하지 않음
			//-> DB에는 성공된 데이터만 저장이 된다
			// == DB에 저장된 데이터의 신뢰도가 상승한다
			e.printStackTrace();
			// Run2 클래스로 예외를 전달 할 수 있도록 예외 강제 발생 
			throw new Exception("DAO 수행 중 예외가 발생했다.");
		} finally {//무조건 conn 반환하기 
			close(conn);
		}
		
		return result;
	}

}
