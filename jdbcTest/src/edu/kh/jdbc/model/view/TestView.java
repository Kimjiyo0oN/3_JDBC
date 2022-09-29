package edu.kh.jdbc.model.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class TestView {
	
	private TestService service = new TestService();
	private List<TestVO> list = new ArrayList<>();
	
	private Scanner sc = new Scanner(System.in);
	
	public void testView() {
		int input = 0;
		try {
			do {
				System.out.println("*** 업데이트/삽입 프로그램 ***");
				System.out.println("1. 삽입");
				System.out.println("2. 업데이트");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine(); //입력 버퍼 개행문자 제거 
				System.out.println();
				
				switch (input) {
				case 1: insert(); break;
				case 2: update(); break;
				case 0:System.out.println("프로그램 종료"); break;
				default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
			} while (input !=0);
		} catch (Exception e) {
			System.out.println("SQL 수행 중 오류 발생");
			
			e.printStackTrace();
		}
	}
//	번호, 제목, 내용을 입력받아 번호가 일치하는 행의 제목, 내용 수정
//
//	수정 성공 시->수정되었습니다.
//	수정 실패 시->일치하는 번호가 없습니다.
//	예외 발생 시->수정 중 예외가 발생하였습니다
	private void update() {
		try {
			System.out.println("테이블에 내용 업데이트");
			
			System.out.print("번호 : ");
			int testNo = sc.nextInt();
			sc.nextLine();
			
			System.out.print("제목 : ");
			String testTitle = sc.next();
			sc.nextLine();
			
			System.out.print("내용 : ");
			String testContent = sc.nextLine();
			
			int result = service.update(new TestVO(testNo, testTitle,testContent));
			
			if(result >0) {
				System.out.println("수정되었습니다");  //삽입 성공시
			}else {
				System.out.println("일치하는 번호가 없습니다."); 
			}
			
		} catch (Exception e) {
			System.out.println("SQL 수행 중 오류 발생");
			e.printStackTrace();
		}
	}

	private void insert() {
		try {
//			private int testNo;
//			private String testTitle;
//			private String testContent;
			
			System.out.println("테이블에 내용 삽입");
			
			System.out.print("몇 행 삽입 : ");
			int input = sc.nextInt();
			
			sc.nextLine(); //입력 버퍼 개행문자 제거 
			System.out.println();
			
			for(int i =0; i < input; i++) {
				System.out.print("번호 : ");
				int testNo = sc.nextInt();
				sc.nextLine();
				
				System.out.print("제목 : ");
				String testTitle = sc.next();
				sc.nextLine();
				
				System.out.print("내용 : ");
				String testContent = sc.nextLine();
				
				//sc.nextLine();
				System.out.println();
				
				list.add(i, new TestVO(testNo, testTitle, testContent));
			}
			
			int result = service.insert(list);
			if(result >0) {
				System.out.println("삽입 성공");  //삽입 성공시
			}else {
				System.out.println("실패..........."); 
			}
		} catch (Exception e) {
			System.out.println("SQL 수행 중 오류 발생");
			e.printStackTrace();
		}
	}
}


