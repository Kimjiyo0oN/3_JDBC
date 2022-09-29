package jdbc.common.stock.model.service;

import static jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import jdbc.common.stock.model.dao.StockDAO;
import jdbc.employee.vo.Employee;
import jdbc.sales.model.vo.Order;
import jdbc.sales.model.vo.Stock;



public class StockService {
	private StockDAO dao =new StockDAO();

	/**재고현황 서비스
	 * @param teamCode
	 * @param input1
	 * @param INOUTS
	 * @param date
	 * @return stockList
	 * @throws Exception
	 */
	public List<Stock> stockST(String teamCode, int input1, String INOUTS, String date) throws Exception{
		Connection conn = getConnection();
		
		List<Stock> stockList = dao.stockST(conn,teamCode, input1,INOUTS,date);
		
		close(conn); 
		
		return stockList;
	}

	/**발주 현황 조회 서비스
	 * @param loginEmployee
	 * @param selectDate
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public List<Order> orderST(Employee loginEmployee, String selectDate, boolean flag) throws Exception{
		Connection conn = getConnection();
		List<Order> orderList = dao.orderST(conn, loginEmployee,selectDate,flag);
		close(conn);
		return orderList;
	}

	/**발주 등록
	 * @param stock
	 * @return
	 * @throws Exception
	 */
	public int insertorder(Stock stock) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insertorder(conn,stock);
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
	
}
