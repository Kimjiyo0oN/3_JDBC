package edu.kh.jdbc.run;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run2 {
	public static void main(String[] args) {
		// TB_TEST 테이블에 한번에 3행 삽입
		TestService service = new TestService();
		
		TestVO vo1 = new TestVO(40, "제목40", "내용40 입니다");
		TestVO vo2 = new TestVO(50, "제목50", "내용20 입니다");
		TestVO vo3 = new TestVO(60, "제목60", null);
		//TestVO vo4 = new TestVO(40, "제목40", "내용40 입니다");
		
		try {
			int result = service.insert(vo1, vo2, vo3);
			
			if(result >0) {
				System.out.println("삽입 성공");  //삽입 성공시
			}else {
				System.out.println("실패..........."); 
				//서브쿼리...서브쿼리를 사용했을 때 검색나오는 경우가 하나도 없어서
				//데이터베이스에는 오류가 나오지 않지만 결과는 삽입이 되지 않아서
				//result값이 3이 아니고 2가 나오는 경우에 실패라고 뜬다 
			}
		}catch(Exception e) {
			// service, dao 수행 중 발생한 예외를 처리
			System.out.println("SQL 수행 중 오류 발생");
			
			e.printStackTrace();
		}
	}
	
}
