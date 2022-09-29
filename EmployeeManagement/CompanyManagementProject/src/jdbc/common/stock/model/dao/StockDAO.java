package jdbc.common.stock.model.dao;

import static jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import jdbc.employee.vo.Employee;
import jdbc.sales.model.vo.Order;
import jdbc.sales.model.vo.Product;
import jdbc.sales.model.vo.Sales;
import jdbc.sales.model.vo.Stock;

public class StockDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public StockDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("stock-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Stock> stockST(Connection conn, String teamCode, int input1, String INOUTS, String date) throws Exception{
		List<Stock> stockList = new ArrayList<>();
		try {
			
			boolean flag = false;
			String sql = null;
			if(teamCode.equals("DC")||teamCode.equals("HQ")) {
				sql= prop.getProperty("stockST"+ input1); //1이 전체 2가 월별
				
			}else {
				 sql= prop.getProperty("stockST"+ input1)
						 + prop.getProperty("stockST"+input1+"_DM");    //? 2개 + DM 일때 
				 flag = true;
			}
			
			pstmt = conn.prepareStatement(sql);
			if(input1 == 2) {
				pstmt.setString(1, INOUTS);
				pstmt.setString(2, date);
				if(flag) {
					pstmt.setString(3, teamCode);
				}
			}else {
				if(flag) {
					pstmt.setString(1, teamCode);
				}
			}
			rs = pstmt.executeQuery();
						 

			while(rs.next()) {
				//TEAM_CODE,PRODUCT_CODE,PRODUCT_NAME, SUM(AMOUNT) 재고수량
//				,s.getTeamCode(),s.getProduct().getProductCode()
//				,s.getProduct().getProductName(),s.getStockAmount());
				Stock st = new Stock();
				Product p =new Product();
				st.setTeamCode(rs.getString("TEAM_CODE"));
				p.setProductCode(rs.getString("PRODUCT_CODE"));
				p.setProductName(rs.getString("PRODUCT_NAME"));
				st.setProduct(p);
				st.setStockAmount(rs.getString("재고수량"));
				stockList.add(st);
			}
						
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return stockList;
	}

	/** 발주현황 조회 DAO
	 * @param conn 
	 * @param loginEmployee
	 * @param selectDate
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public List<Order> orderST(Connection conn, Employee loginEmployee, String selectDate, boolean flag) throws Exception{
		List<Order> orderList = new ArrayList<>();
		try {
			boolean flag1 = false;
			String sql = null;
			if(!flag) {
				if(loginEmployee.getTeamCode().equals("DC")||loginEmployee.getTeamCode().equals("HQ")) {
					sql= prop.getProperty("orderST");
				}else {
					sql= prop.getProperty("orderST")
							 + prop.getProperty("orderST_DM"); //
					 flag1 = true;
				}
				
			}else {
				if(loginEmployee.getTeamCode().equals("DC")||loginEmployee.getTeamCode().equals("HQ")) {
					sql= prop.getProperty("orderST1");
				}else {
					sql= prop.getProperty("orderST1")
							 + prop.getProperty("orderST1_DM"); //1.날짜 2.
					 flag1 = true;
				}
			}
			
			pstmt = conn.prepareStatement(sql);
			if(!flag) {
				if(flag1) pstmt.setString(1, loginEmployee.getTeamCode());
			}else {
				pstmt.setString(1, selectDate);
				if(flag1) pstmt.setString(2, loginEmployee.getTeamCode());
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//ORDER_NO ,ORDER_CODE , PRODUCT_CODE , ORDER_AMOUNT, TO_CHAR(ORDER_DATE, 'YYYY-MM-DD') 날짜,TEAM_CODE 
				Order o = new Order();
				Product p = new Product();
				o.setOrderNo(rs.getString("ORDER_NO"));
				o.setOrderCode(rs.getString("ORDER_CODE"));
				p.setProductCode(rs.getString("PRODUCT_CODE"));
				o.setProduct(p);
				o.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				o.setOrderDate(rs.getString("날짜"));
				o.setOrderTeam(rs.getString("TEAM_CODE"));
				orderList.add(o);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderList;
	}

	public int insertorder(Connection conn, Stock stock) throws Exception{
		int result =0;
		try {
//			product.setProductCode(productCode);
//			stock.setStockAmount(pAmount);
//			stock.setProduct(product);
//			stock.setTeamCode(teamCode);
			int result1 = 0;
			int result2 = 0;
			SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
			String today = format.format(new Date());
			
			String sql2 = prop.getProperty("insertorderTB");
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, stock.getStockAmount());
			pstmt.setString(2, stock.getProduct().getProductCode());
			pstmt.setString(3,stock.getTeamCode());
			pstmt.setString(4,today+stock.getTeamCode());
			result2 = pstmt.executeUpdate();
			
			String sql = prop.getProperty("insertorder");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, stock.getStockAmount());
			pstmt.setString(2, stock.getProduct().getProductCode());
			pstmt.setString(3,stock.getTeamCode());
			result = pstmt.executeUpdate();
			
			String sql1 = prop.getProperty("insertorderDC");
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, stock.getStockAmount());
			pstmt.setString(2, stock.getProduct().getProductCode());
			pstmt.setString(3,stock.getTeamCode());
			result1 = pstmt.executeUpdate();
			if(result+result1+result2 == 3) {
				result =1;
			}else {
				result =0;
			}
		} finally {
			close(pstmt);
		}
		return result;
	}
}
