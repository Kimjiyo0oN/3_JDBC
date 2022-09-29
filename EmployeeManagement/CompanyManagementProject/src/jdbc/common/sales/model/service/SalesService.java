package jdbc.common.sales.model.service;

import java.sql.Connection;
import java.util.List;

import jdbc.common.sales.model.dao.SalesDAO;
import jdbc.employee.vo.Employee;
import jdbc.sales.model.vo.Sales;
import static jdbc.common.JDBCTemplate.*;

public class SalesService {
	private SalesDAO dao =new SalesDAO();
	/**월별 매출 조회
	 * @param loginEmployee
	 * @return salesList
	 * @throws Exception
	 */
	public List<Sales> monthSales(String teamCode)throws Exception{
		Connection conn = getConnection();
		
		List<Sales> salesList = dao.monthSales(conn,teamCode); 
		
		close(conn);
		
		return salesList;
	}
	/**BEST 상품 조회
	 * @param teamCode
	 * @param input1
	 * @return salesList
	 * @throws Exception
	 */
	public List<Sales> bestSales(String teamCode, int input1) throws Exception{
		Connection conn = getConnection();
		
		List<Sales> salesList = dao.bestSales(conn,teamCode, input1);
		
		close(conn); 
		
		return salesList;
	}
	/**월별 전체 달성률
	 * @param teamCode
	 * @return salesList
	 * @throws Exception
	 */
	public List<Sales> salesResultT(String teamCode) throws Exception{
		Connection conn = getConnection();
		
		List<Sales> salesList = dao.salesResultT(conn,teamCode);
		
		return salesList;
	}
	/**월마다 분류별 달성률
	 * @param teamCode
	 * @return salesList
	 * @throws Exception
	 */
	public List<Sales> salesResultC(String teamCode) throws Exception{
		Connection conn = getConnection();
		
		List<Sales> salesList = dao.salesResultC(conn,teamCode);
				
		return salesList;
	}
	/** 단품 전점 판매 현황 서비스
	 * @param selectYN
	 * @param selectName 
	 * @return salesList
	 */
	public List<Sales> productSales(String selectYN, String selectName) throws Exception{
		Connection conn = getConnection();
		
		List<Sales> salesList = dao.productSales(conn,selectYN,selectName);
				
		return salesList;
	}

}
