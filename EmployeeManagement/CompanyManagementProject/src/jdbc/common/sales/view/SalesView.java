package jdbc.common.sales.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jdbc.common.sales.model.service.SalesService;
import jdbc.employee.vo.Employee;
import jdbc.sales.model.vo.Sales;

public class SalesView {
	private Scanner sc = new Scanner(System.in);
	
	private SalesService Sservice = new SalesService();
	private Employee loginEmployee = null;
	
	private int input =-1;
	
	/** 매출관리 Menu 화면 
	 * @param loginEmployee
	 */
	public void salesMenu(Employee loginEmployee) {
		this.loginEmployee = loginEmployee;
		do {
			try {
				System.out.println("\n--------- 매출 관리 ---------\n");
				System.out.println("1. 월별 매출");
				System.out.println("2. BEST 매출 상품");
				System.out.println("3. 매출 실적");
				System.out.println("4. 단품 전점 매출 현황");
				System.out.println("0. 메인메뉴로 이동");
				
				System.out.print("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch (input) {
				case 1: monthSales(loginEmployee); break;
				case 2: bestSales(loginEmployee); break;
				case 3: salesResult(loginEmployee); break;
				case 4: productSales(); break;
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
	private void monthSales(Employee loginEmployee) {
//		private String teamCode;
//		private String teamName;
//		private String salesDate;
//		private String salesAmount;
//		private String totalPrice;
		System.out.println("\n[월별 매출 조회]\n");
		try {
			String teamCode =loginEmployee.getTeamCode();
			List<Sales> salesList = Sservice.monthSales(teamCode);
			
			if(!salesList.isEmpty()) {
				System.out.println(" 매장코드      판매수량            판매매출           판매월");
				System.out.println("-----------------------------------------------------------");
				for(Sales s : salesList) {
					System.out.printf("%s        %3d          %10d원          %s\n",s.getTeamCode(),s.getSalesAmount()
																			 ,s.getTotalPrice(),s.getSalesDate());
				}
			}
		} catch (Exception e) {
			System.out.println("\n<<월별 매출 조회 중 에러가 발생했습니다.>>\n");
			e.printStackTrace();
		}
	}
	private void bestSales(Employee loginEmployee) {
		try {
			String teamCode =loginEmployee.getTeamCode();
			int input1 =-1;
			do {
				try {
					System.out.println("\n[BEST 매출 상품 조회]\n");
					System.out.println("1. 전체 BEST 매출 상품");
					System.out.println("2. 월별 BEST 매출 상품");
					System.out.println("0. 매출 관리로 이동");
					
					System.out.print("\n메뉴 선택 : ");
					
					input1 = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (input1) {
					case 1:
						List<Sales> salesList = Sservice.bestSales(teamCode, input1);
						
						if(!salesList.isEmpty()) {
							System.out.println(" 상품코드          상품이름           판매수량             판매금액");
							System.out.println("-------------------------------------------------------------");
							for(Sales s : salesList) {
								System.out.printf("%s        %5s          %5d          %10d원\n",s.getProduct().getProductCode(),s.getProduct().getProductName()
																						 ,s.getSalesAmount(),s.getTotalPrice());
							}
						}
						break;
					case 2: 
						List<Sales> salesList1 = Sservice.bestSales(teamCode, input1);
						
						if(!salesList1.isEmpty()) {
							System.out.println(" 판매월            상품코드             상품이름           판매수량             판매금액");
							System.out.println("--------------------------------------------------------------------------------");
							for(Sales s : salesList1) {
								System.out.printf("%s           %s            %5s          %5d          %10d원\n"
																,s.getSalesDate(),s.getProduct().getProductCode(),s.getProduct().getProductName()
																,s.getSalesAmount(),s.getTotalPrice());
							}
						}
						break;
					case 0: System.out.println("[매출 관리로 이동합니다.]"); break;
					default: System.out.println("정확한 메뉴 번호를 선택해주세요.");
					}
				} catch (InputMismatchException e) {
					System.out.println("\n<<입력형식이 올바르지 않습니다.>>\n");
					e.printStackTrace();
					sc.nextLine();
				}
			} while (input1 !=0);
		} catch (Exception e) {
			System.out.println("\n<<BEST 상품 조회 중 에러가 발생했습니다.>>\n");
			e.printStackTrace();
		}
	}
	private void salesResult(Employee loginEmployee) {
		System.out.println("\n[매출 실적 조회]\n");
		try {
			String teamCode =loginEmployee.getTeamCode();
			List<Sales> salesList = Sservice.salesResultT(teamCode);
			List<Sales> salesList1 = Sservice.salesResultC(teamCode);
			if(!salesList.isEmpty()) {
				System.out.println("------------------------------------월별 전체 달성률----------------------------------");
				System.out.println(" 매장코드       판매월                 판매매출              목표금액               달성률");
				System.out.println("----------------------------------------------------------------------------------");
				for(Sales s : salesList) {
					System.out.printf("%s         %s          %10d원          %10d원             %5.2f",s.getTeamCode(),s.getSalesDate()
																			,s.getTotalPrice() ,s.getTargetPrice(),s.getSalesPer());
					System.out.println("%");
				}
			}
			if(!salesList1.isEmpty()) {
				System.out.println("---------------------------------------------분류별 달성률-------------------------------------------");
				System.out.println(" 매장코드      판매월            상품분류코드            판매매출                목표금액               달성률");
				System.out.println("--------------------------------------------------------------------------------------------------");
				for(Sales s : salesList1) {
					System.out.printf("%s         %s         %s          %10d원          %10d원             %5.2f"
																	,s.getTeamCode(),s.getSalesDate()
																	,s.getProduct().getCategoryCode(),s.getTotalPrice() ,s.getTargetPrice(),s.getSalesPer());
					System.out.println("%");
				}
			}
		} catch (Exception e) {
			System.out.println("\n<<매출 실적 조회 중 에러가 발생했습니다.>>\n");
			e.printStackTrace();
		}
		
	}
	/**
	 * 단품 전점 판매 현황
	 */
	private void productSales() {
		// TEAM_CODE,PRODUCT_CODE,PRODUCT_NAME,재고수량,판매수량
		System.out.println("\n[단품 전점 매출 현황]\n");
		String selectYN = "N";
		String selectName ="";
		try {
			List<Sales> salesList = Sservice.productSales(selectYN,selectName);
			
			if(!salesList.isEmpty()) {
				System.out.println(" 매장코드         상품코드                 상품이름           재고수량         판매수량");
				System.out.println("---------------------------------------------------------------------------");
				for(Sales s : salesList) {
					System.out.printf("%5s        %10s          %10s          %5d          %5d\n"
																,s.getTeamCode(),s.getProduct().getProductCode(),s.getProduct().getProductName()
															  ,s.getTotalAmount(),s.getSalesAmount());
				}
			}
			System.out.print("상품별 검색을 원하십니까? (Y/N):");
			selectYN = sc.next();
			if(selectYN.equals("Y")) {
				System.out.print("상품이름 : ");
				selectName= sc.next();
				List<Sales> salesList1 = Sservice.productSales(selectYN,selectName);
				if(!salesList1.isEmpty()) {
					System.out.println(" 매장코드         상품코드                 상품이름           재고수량         판매수량");
					System.out.println("---------------------------------------------------------------------------");
					for(Sales s : salesList1) {
						System.out.printf("%5s        %10s          %10s          %5d          %5d\n"
																	,s.getTeamCode(),s.getProduct().getProductCode(),s.getProduct().getProductName()
																  ,s.getTotalAmount(),s.getSalesAmount());
					}
				}
			}else {
				System.out.println("[매출 관리로 이동합니다.]");
			}
		} catch (Exception e) {
			System.out.println("\n<<단품 전점 매출 현황 조회 중 에러가 발생했습니다.>>\n");
			e.printStackTrace();
		}
	}
}
