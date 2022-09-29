package jdbc.common.sales.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdbc.sales.model.vo.Product;
import jdbc.sales.model.vo.Sales;

import static jdbc.common.JDBCTemplate.*;

public class SalesDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	//private Product product = null;
	public SalesDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("sales-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 월별 매출 DAO
	 * @param conn
	 * @param teamCode
	 * @return salesList
	 * @throws Exception
	 */
	public List<Sales> monthSales(Connection conn, String teamCode) throws Exception {
		List<Sales> salesList = new ArrayList<>();
		try {
			boolean flag = false;
			String sql = null;
			if(teamCode.equals("DC")||teamCode.equals("HQ")) {
				sql= prop.getProperty("monthSales")
						 + prop.getProperty("monthSales_HQDC"); 
				
			}else {
				 sql= prop.getProperty("monthSales")
						 + prop.getProperty("monthSales_DM"); 
				 flag = true;
			}
			
			pstmt = conn.prepareStatement(sql);
			if(flag) {
				pstmt.setString(1, teamCode);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Sales sales = new Sales();
				sales.setSalesDate(rs.getString("SUBSTR(TO_CHAR(STOCK_ST_DATE),1,5)"));
				sales.setSalesAmount(rs.getInt("SUM(AMOUNT)"));
				sales.setTeamCode(rs.getString("TEAM_CODE"));
				sales.setTotalPrice(rs.getInt("매출"));
				salesList.add(sales);
			}
						
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return salesList;
	}
	/** BEST 상품 조회 DAO
	 * @param conn
	 * @param teamCode
	 * @param input1
	 * @return
	 * @throws Exception
	 */
	public List<Sales> bestSales(Connection conn, String teamCode, int input1) throws Exception{
		List<Sales> salesList = new ArrayList<>();
		try {
//			if(input1 == 1) {
//				boolean flag = false;
//				String sql = null;
//				if(teamCode.equals("DC")||teamCode.equals("HQ")) {
//					sql= prop.getProperty("bestSales1")
//							 + prop.getProperty("bestSales1_HQDC"); 
//					
//				}else {
//					 sql= prop.getProperty("bestSales1")
//							 + prop.getProperty("bestSales1_DM"); 
//					 flag = true;
//				}
//				
//				pstmt = conn.prepareStatement(sql);
//				if(flag) {
//					pstmt.setString(1, teamCode);
//				}
//				rs = pstmt.executeQuery();
//				while(rs.next()) {
//					//월, 상품코드, 상품이름, SUM(수량) 수량, SUM(매출) 매출
//					Sales sales = new Sales();
//					sales.setSalesDate(rs.getString("월"));
//					sales.setSalesAmount(rs.getInt("수량)"));
//					sales.getProduct().setProductName(rs.getString("상품이름"));
//					sales.getProduct().setProductCode(rs.getString("상품코드"));
//					sales.setTotalPrice(rs.getInt("매출"));
//					salesList.add(sales);
//				}
//			}
//			if(input1 == 2) {
//				boolean flag = false;
//				String sql = null;
//				if(teamCode.equals("DC")||teamCode.equals("HQ")) {
//					sql= prop.getProperty("bestSales2")
//							 + prop.getProperty("bestSales2_HQDC"); 
//					
//				}else {
//					 sql= prop.getProperty("bestSales2")
//							 + prop.getProperty("bestSales2_DM"); 
//					 flag = true;
//				}
//				
//				pstmt = conn.prepareStatement(sql);
//				if(flag) {
//					pstmt.setString(1, teamCode);
//				}
//				rs = pstmt.executeQuery();
//				while(rs.next()) {
//					//월, 상품코드, 상품이름, SUM(수량) 수량, SUM(매출) 매출
//					Sales sales = new Sales();
//					sales.setSalesAmount(rs.getInt("수량)"));
//					sales.getProduct().setProductName(rs.getString("상품이름"));
//					sales.getProduct().setProductCode(rs.getString("상품코드"));
//					sales.setTotalPrice(rs.getInt("매출"));
//					salesList.add(sales);
//				}
//			}
			
			boolean flag = false;
			String sql = null;
			if(teamCode.equals("DC")||teamCode.equals("HQ")) {
				sql= prop.getProperty("bestSales"+ input1) //1이 전체 2가 월별
						 + prop.getProperty("bestSales"+input1+"_HQDC"); 
				
			}else {
				 sql= prop.getProperty("bestSales"+ input1)
						 + prop.getProperty("bestSales"+input1+"_DM"); 
				 flag = true;
			}
			
			pstmt = conn.prepareStatement(sql);
			if(flag) {
				pstmt.setString(1, teamCode);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//월, 상품코드, 상품이름, SUM(수량) 수량, SUM(매출) 매출
				Sales sales = new Sales();
				Product product = new Product();
				sales.setSalesAmount(rs.getInt("수량"));
				if(input1==2)sales.setSalesDate(rs.getString("월"));
				product.setProductName(rs.getString("상품이름"));
				product.setProductCode(rs.getString("상품코드"));
				sales.setProduct(product);
				sales.setTotalPrice(rs.getInt("매출"));
				salesList.add(sales);
			}
						
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return salesList;
	}
	/**월별 전체 달성률 DAO
	 * @param conn
	 * @param teamCode
	 * @return
	 * @throws Exception
	 */
	public List<Sales> salesResultT(Connection conn, String teamCode) throws Exception{
		List<Sales> salesList = new ArrayList<>();
		try {
			boolean flag = false;
			String sql = null;
			if(teamCode.equals("DC")||teamCode.equals("HQ")) {
				sql= prop.getProperty("salesResultT"); 
				
			}else {
				 sql= prop.getProperty("salesResultT")
						 + prop.getProperty("salesResultT_DM"); 
				 flag = true;
			}
			
			pstmt = conn.prepareStatement(sql);
			if(flag) {
				pstmt.setString(1, teamCode);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Sales sales = new Sales();
				/* sub.월, sub.매출, stt.TARGET_AMOUNT 목표금액,ROUND((sub.매출/stt.TARGET_AMOUNT*100),1) 달성률 ,sub.팀코드
				 * s.getTeamCode(),s.getSalesDate()
				,s.getTotalPrice() ,s.getTargetPrice(),s.getSalesPer()
				 * Product product = new Product();
				 * product.setProductCode(rs.getString("상품코드")); sales.setProduct(product);
				 */
				sales.setSalesDate(rs.getString("월"));
				sales.setTargetPrice(rs.getInt("목표금액"));
				sales.setSalesPer(rs.getFloat("달성률"));
				sales.setTeamCode(rs.getString("팀코드"));
				sales.setTotalPrice(rs.getInt("매출"));
				salesList.add(sales);
			}
						
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return salesList;
	}
	/**월마다 분류별 달성률 DAO
	 * @param conn
	 * @param teamCode
	 * @return
	 * @throws Exception
	 */
	public List<Sales> salesResultC(Connection conn, String teamCode) throws Exception{
		List<Sales> salesList = new ArrayList<>();
		try {
			boolean flag = false;
			String sql = null;
			if(teamCode.equals("DC")||teamCode.equals("HQ")) {
				sql= prop.getProperty("salesResultC"); 
				
			}else {
				 sql= prop.getProperty("salesResultC")
						 + prop.getProperty("salesResultT_DM"); 
				 flag = true;
			}
			
			pstmt = conn.prepareStatement(sql);
			if(flag) {
				pstmt.setString(1, teamCode);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Sales sales = new Sales();
				/* 월, 분류코드, 팀코드,매출 ,목표금액, 달성률
				 * s.getTeamCode(),s.getSalesDate()
				,s.getTotalPrice() ,s.getTargetPrice(),s.getSalesPer()
				 * 
				 */
				Product product = new Product();
				product.setCategoryCode(rs.getString("분류코드"));
				sales.setProduct(product);
				sales.setSalesDate(rs.getString("월"));
				sales.setTargetPrice(rs.getInt("목표금액"));
				sales.setSalesPer(rs.getFloat("달성률"));
				sales.setTeamCode(rs.getString("팀코드"));
				sales.setTotalPrice(rs.getInt("매출"));
				salesList.add(sales);
			}
						
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return salesList;
	}
	
	/**단품 전점 매출 현황 DAO
	 * @param conn
	 * @param selectYN
	 * @param selectName 
	 * @return salesList
	 * @throws Exception
	 */
	public List<Sales> productSales(Connection conn, String selectYN, String selectName) throws Exception{
		List<Sales> salesList = new ArrayList<>();
		try {
			String sql = null;
			if(selectYN.equals("Y")) {
				sql = prop.getProperty("productSales")
						+ prop.getProperty("productSalesY");
			}else {
				sql = prop.getProperty("productSales")
						+ prop.getProperty("productSalesN");
						
			}
			pstmt =conn.prepareStatement(sql);
			if(selectYN.equals("Y")) pstmt.setString(1, selectName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//// TEAM_CODE,PRODUCT_CODE,PRODUCT_NAME,재고수량,판매수량
				Sales sales = new Sales();
				Product product = new Product();
				product.setProductCode(rs.getString("PRODUCT_CODE"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				sales.setProduct(product);
				sales.setTeamCode(rs.getString("TEAM_CODE"));
				sales.setTotalAmount(rs.getInt("재고수량"));
				sales.setSalesAmount(rs.getInt("판매수량"));
				salesList.add(sales);
			}
						
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return salesList;
	}
	
}
