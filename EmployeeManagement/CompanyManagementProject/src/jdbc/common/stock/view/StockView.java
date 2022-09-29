package jdbc.common.stock.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.common.stock.model.service.StockService;
import jdbc.employee.vo.Employee;
import jdbc.sales.model.vo.Order;
import jdbc.sales.model.vo.Product;
import jdbc.sales.model.vo.Stock;

public class StockView {
private Scanner sc = new Scanner(System.in);
	
	private StockService Stservice = new StockService();
	private Employee loginEmployee = null;
	
	private int input =-1;
	/** 재고관리 Menu 화면 
	 * @param loginEmployee
	 */
	public void StockMenu(Employee loginEmployee) {
		this.loginEmployee = loginEmployee;
		do {
			try {
				System.out.println("\n--------- 재고관리 ---------\n");
				System.out.println("1. 재고현황");
				//System.out.println("2. 재고분석");
				System.out.println("2. 발주현황");
				if(!loginEmployee.getTeamCode().equals("HQ")&&!loginEmployee.getTeamCode().equals("DC"))System.out.println("3. 점포 발주 등록");
				System.out.println("0. 메인메뉴로 이동");
				
				System.out.print("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch (input) {
				case 1: stockST(loginEmployee); break;
				//case 2: break;
				case 2: orderST(loginEmployee);break;
				case 3: 
					if(!loginEmployee.getTeamCode().equals("HQ")&&!loginEmployee.getTeamCode().equals("DC")) {
						insertorder();
				}
				break;
				case 0: System.out.println("[메인 메뉴로 이동합니다.]"); break;
				default: System.out.println("정확한 메뉴 번호를 선택해주세요.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\n<<입력형식이 올바르지 않습니다.>>\n");
				e.printStackTrace();
				sc.nextLine();
			}
		} while (input !=0);
		
	}
	
	/**재고현황
	 * @param loginEmployee
	 */
	private void stockST(Employee loginEmployee) {
		try {
			String teamCode =loginEmployee.getTeamCode();
			int input1 =-1;
			do {
				try {
					String date ="";
					String INOUTS ="";
					System.out.println("\n[재고현황 조회]\n");
					System.out.println("1. 전체 재고 현황 조회");
					System.out.println("2. 월마다 입/출고/판매별 재고 현황 조회");
					System.out.println("0. 매출 관리로 이동");
					
					System.out.print("\n메뉴 선택 : ");
					
					input1 = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (input1) {
					case 1:
						List<Stock> stockList = Stservice.stockST(teamCode, input1,INOUTS,date);
						
						if(!stockList.isEmpty()) {
							System.out.println(" 매장코드          상품코드           상품이름             상품재고");
							System.out.println("-------------------------------------------------------------");
							for(Stock s : stockList) {
								System.out.printf("%5s        %5s          %5s          %10s\n"
																,s.getTeamCode(),s.getProduct().getProductCode()
																,s.getProduct().getProductName(),s.getStockAmount());
							}
						}
						break;
					case 2:
						System.out.print("입출고/판매 선택 (IN/OUT/SALES)형식으로 입력해주세요: ");
						INOUTS = sc.next();
						System.out.print("월 (YY/MM)형식으로 입력해주세요: ");
						date = sc.next();
						List<Stock> stockList1 = Stservice.stockST(teamCode, input1,INOUTS,date);
						
						if(!stockList1.isEmpty()) {
							System.out.println(" 매장코드          상품코드           상품이름             변동수량");
							System.out.println("-------------------------------------------------------------");
							for(Stock s : stockList1) {
								System.out.printf("%5s        %5s          %5s          %10s\n"
										,s.getTeamCode(),s.getProduct().getProductCode()
										,s.getProduct().getProductName(),s.getStockAmount());
							}
						}
						break;
					case 0: System.out.println("[재고 관리로 이동합니다.]"); break;
					default: System.out.println("정확한 메뉴 번호를 선택해주세요.");
					}
				} catch (InputMismatchException e) {
					System.out.println("\n<<입력형식이 올바르지 않습니다.>>\n");
					e.printStackTrace();
					sc.nextLine();
				}
			} while (input1 !=0);
		} catch (Exception e) {
			System.out.println("\n<<재고 현황 조회 중 에러가 발생했습니다.>>\n");
			e.printStackTrace();
		}
	}
	/**발주현황
	 * @param loginEmployee
	 */
	private void orderST(Employee loginEmployee) {
		try {
			boolean flag = false;
			System.out.println("\n[발주현황 조회]\n");
			String selectDate = "";
			
			//ORDER_NO ,ORDER_CODE , PRODUCT_CODE , ORDER_AMOUNT, TO_CHAR(ORDER_DATE, 'YYYY-MM-DD') 날짜,TEAM_CODE  
			List<Order> orderSTList = Stservice.orderST(loginEmployee, selectDate,flag);
			if(!orderSTList.isEmpty()) {
				System.out.println(" 주문번호             주문코드                상품코드             발주수량         발주날짜            매장코드");
				System.out.println("--------------------------------------------------------------------------------------------------");
				for(Order s : orderSTList) {
					System.out.printf("%5s        %15s          %10s          %5d          %s         %s\n"
																,s.getOrderNo(),s.getOrderCode(),s.getProduct().getProductCode()
																,s.getOrderAmount(),s.getOrderDate(),s.getOrderTeam());
				}
			}
			System.out.print("조회할 날짜 입력(YYMMDD) : ");
			selectDate = sc.next();
			sc.nextLine();
			flag =true;
			List<Order> orderSTList1 = Stservice.orderST(loginEmployee, selectDate,flag);
			if(!orderSTList1.isEmpty()) {
				System.out.println(" 주문번호             주문코드                상품코드             발주수량         발주날짜            매장코드");
				System.out.println("--------------------------------------------------------------------------------------------------");
				for(Order s : orderSTList1) {
					System.out.printf("%5s        %15s          %10s          %5d          %s         %s\n"
							,s.getOrderNo(),s.getOrderCode(),s.getProduct().getProductCode()
							,s.getOrderAmount(),s.getOrderDate(),s.getOrderTeam());
				}
			}
			
		} catch (Exception e) {
			System.out.println("\n<<발주현황 조회 중 에러가 발생했습니다.>>\n");
			e.printStackTrace();
		}
		
	}
	/**
	 * 매장직원들만 발주할수 있음
	 */
	private void insertorder() {
		try {
			System.out.println("\n[발주 등록]\n");
			String teamCode =loginEmployee.getTeamCode();
			List<Stock> stockList = Stservice.stockST(teamCode,1,"","");
			
			if(!stockList.isEmpty()) {
				System.out.println(" 매장코드          상품코드           상품이름             상품재고");
				System.out.println("-------------------------------------------------------------");
				for(Stock s : stockList) {
					System.out.printf("%5s        %5s          %5s          %10s\n"
													,s.getTeamCode(),s.getProduct().getProductCode()
													,s.getProduct().getProductName(),s.getStockAmount());
				}
			}
			Product product = new Product();
			Stock stock =new Stock();
			System.out.print("상품코드 입력 : ");
			String productCode = sc.next();
			System.out.print("수량 입력 : ");
			String pAmount = sc.next();
			sc.nextLine();
			product.setProductCode(productCode);
			stock.setStockAmount(pAmount);
			stock.setProduct(product);
			stock.setTeamCode(teamCode);
			int result = Stservice.insertorder(stock);
			
			if(result>0) {
				System.out.println("\n발주 등록되었습니다.\n");
				SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
				String today = format.format(new Date());
				
				
				List<Order> orderSTList1 = Stservice.orderST(loginEmployee,today ,true);
				if(!orderSTList1.isEmpty()) {
					System.out.println(" 주문번호             주문코드                상품코드             발주수량         발주날짜            매장코드");
					System.out.println("--------------------------------------------------------------------------------------------------");
					for(Order s : orderSTList1) {
						System.out.printf("%5s        %15s          %10s          %5d          %s         %s\n"
								,s.getOrderNo(),s.getOrderCode(),s.getProduct().getProductCode()
								,s.getOrderAmount(),s.getOrderDate(),s.getOrderTeam());
					}
				}
			}else {
				System.out.println("\n[발주 등록 실패]\n");
			}
		}catch (Exception e) {
			System.out.println("\n<<발주 등록 중 에러가 발생했습니다.>>\n");
			e.printStackTrace();
		}
		
	}
	
}

