package jdbc.main.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jdbc.common.sales.view.SalesView;
import jdbc.common.stock.view.StockView;
import jdbc.employee.vo.Employee;
import jdbc.main.model.service.MainService;

public class MainView {
	private Scanner sc =new Scanner(System.in);
	
	private MainService service = new MainService();
	
	//로그인된 사원 정보를 저장한 객체를 참조하는 참조변수
	public static Employee loginEmployee = null;
	
	public static List<Employee> loginList = null;
	
	private SalesView salesView = new SalesView();
	private StockView stockView = new StockView();
	
	public void mainMenu() {
		int input = -1;
		loginList();
		do {
			try {
				
				if(loginEmployee == null) {//로그인 하기 전 화면
					System.out.println("\n--------- 회사 운영 관리 프로그램 ---------\n");
					System.out.println("1. 로그인");
					System.out.println("2. 아이디/비번 찾기");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("\n메뉴 선택 : ");
					
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (input) {
					case 1: login(loginList); break;
					case 2: findLogin(loginList);break;//  
					case 0: System.out.println("프로그램 종료"); break;
					default: System.out.println("정확한 메뉴 번호를 선택해주세요.");
					}
				}else {//로그인한 후 화면 
					System.out.println("\n--------- 회사 운영 관리 프로그램 ---------\n");
					System.out.println("1. 매출관리");
					System.out.println("2. 재고관리");
					System.out.println("3. 매입");
					System.out.println("4. 운영관리");
					System.out.println("0. 로그아웃");
					System.out.println("99. 프로그램 종료");
					
					System.out.print("\n메뉴 선택 : ");
					
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (input) {
					case 1: salesView.salesMenu(loginEmployee); break;
					case 2: stockView.StockMenu(loginEmployee); break;
					case 3: break;
					case 4: break;
					case 0:
						loginEmployee = null;
						System.out.println("로그아웃하셨습니다.");
						input = -1;
						break;
					case 99: System.out.println("프로그램 종료"); 
					input =0;
					break;
					default: System.out.println("정확한 메뉴 번호를 선택해주세요.");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\n[[입력 형식이 올바르지 않습니다.]]\n");
				sc.nextLine();
			}
		} while (input != 0);
		
	}
	
	private void loginList() {
		try {
			loginList = service.loginList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void login(List<Employee> loginList) {
		System.out.println("[로그인 화면]");
		//int num =0;
		boolean flag = true;
		boolean flag2 = true;
		try {
			while(flag) {
				System.out.print("아이디 : ");
				String employeeID = sc.next();
				
				System.out.print("비밀번호 : ");
				String employeePW = sc.next();
				//sc.nextLine();
				if(!loginList.isEmpty()) {
					for(Employee e : loginList) {
						if(e.getEmployeeID().equals(employeeID)) {
							flag2 =false;
							if(e.getEmployeePW().equals(employeePW)) {
								if(e.getLoginNum() >= 3) {
									System.out.println("<<3회 초과 로그인 실패 이력 존재 로그인 불가>>");
									//System.out.print("비번 찾기를 원하십니까? (Y/N)");
									findPW();
									flag = false;
									break;
								}
								loginEmployee = service.login(employeeID,employeePW);
								e.setLoginNum(0);
								flag = false;
								break;
							}else {
								//num++;
								e.setLoginNum(e.getLoginNum()+1);
								if(e.getLoginNum() < 3) {
									System.out.println("로그인 시도 "+e.getLoginNum() +"번\n총 3회 초과 시 로그인 불가");
								}
								if(e.getLoginNum() >= 3) {
									System.out.println("<<3회 로그인 실패! 로그인 불가>>");
									//System.out.print("비번 찾기를 원하십니까? (Y/N)");
									findPW();
									flag = false;
								}
								break;
							}
						}
					}
					if(flag2) {
						flag = false;
						System.out.println("[아이디 또는 비밀번호가 일치하지 않거나 권한이 없습니다.]");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("\n[[경고!!로그인 중 예외 발생]]\n");
			e.printStackTrace();
		}
		
	}
	
	private void findLogin(List<Employee> loginList) {
		System.out.println("[아이디/비번 찾기]");
		System.out.print("사번 입력해주세요 : ");
		String employeeNO = sc.next();
		try {
			if(!loginList.isEmpty()) {
				for(int i =0; i < loginList.size(); i++) {
					if(loginList.get(i).getEmployeeNO().equals(employeeNO)) {
						System.out.println("아이디 : "+ loginList.get(i).getEmployeeID()+"입니다.");
						findPW();
						break;		
					}
					if(i == loginList.size()-1) {
						System.out.println("[일치하는 사번이 없거나 권한이 없습니다.]");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("\n[[경고!!아이디 찾기 중 예외 발생]]\n");
			e.printStackTrace();
		}
		
	}
	
	private void findPW() {
		int flag =0;
		boolean flag2 = true;
		String result =null;
		try {
			System.out.print("비밀번호를 찾으시겠습니까?(Y/N) : ");
			String ans=sc.next().toUpperCase();
			if(ans.equals("Y")) {
				while(flag2) {
					System.out.print("\n아이디를 입력해주세요 : ");
					String EmployeeID = sc.next();
					System.out.print("주민번호를 입력해주세요 : ");
					String EmployeeSSN = sc.next();
					if(!loginList.isEmpty()) {
						for(int i =0; i < loginList.size(); i++) {
							if(loginList.get(i).getEmployeeSSN().equals(EmployeeSSN)) {
								result = service.findPW(EmployeeSSN);
								if(result !=null) {
									System.out.println("비밀번호 변경 성공");
									loginList.get(i).setLoginNum(0);
									loginList.get(i).setEmployeePW(result);
								}else {
									System.out.println("비밀번호 변경 실패");
								}
								flag2 = false;
								break;
							}
							if(i == loginList.size()-1){
								System.out.println("주민번호가 일치하지 않습니다.");
								flag +=1;
								if(flag >=2) {
									if(flag ==3) {
										System.out.println("비밀번호 찾기 실패");
										flag2 = false;
									}else {
										System.out.println("마지막 기회입니다");
									}
								}
							}
						}
					}
				}
			}else {
				System.out.println("\n[로그인 창으로 전환됩니다.]");
				login(loginList);
			}
		} catch (Exception e) {
			System.out.println("\n[[경고!!비번 찾기 중 예외 발생]]\n");
			e.printStackTrace();
		}
	}
}
