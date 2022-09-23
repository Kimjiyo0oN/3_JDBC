package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.board.model.vo.Comment;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();
	
	// CommentDAO 객체 생성 -> 상세 조회 시 댓글 목록 조회용도로 사용
	private CommentDAO cDao = new CommentDAO();

	/**게시글 목록 조회 서비스
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllBoard() throws Exception{
		
		Connection conn = getConnection();
		
//		List<Board> boardList = new ArrayList<>();
//		boardList = dao.selectAllBoard(conn);
		
		List<Board> boardList = dao.selectAllBoard(conn);
		// -> DAO에서 new ArrayList<>(); 구문으로 인해 반환되는 조회 결과는
		//    null이 될수 없다!!!!
		if(boardList.isEmpty()){ //조회 결과가 없을 경우
			System.out.println("게시글이 존재하지 않습니다."); 
		}else {
			for(Board b:boardList) {
				// 3 | 샘플 제목3[4] | 유저삼 | 3시간전 | 10
				System.out.printf("%d | %s[%d] | %s | %s | %d\n",b.getBoardNo(),b.getBoardTitle(),
						b.getCommentCount(),b.getMemberName(),b.getCreateDate(),b.getReadCount());
			}
		}
		
		
		close(conn);
		
		return boardList;
	}

	/**게시글 상세 조회 서비스
	 * @param boardNo
	 * @param memberNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		//1. 게시글 상세 조회 DAO 호출 
		Board board = dao.selectBoard(conn, boardNo);
		//-> 조회 결과가 없으면 null, 있으면 null 아님
	
		if(board != null) { //게시글이 존재 한다면 
			//2. 댓글 목록 조회 DAO 호출
			List<Comment> commentList = cDao.selectCommentList(conn,boardNo);
			
			// 조회된 댓글 목록을 board에 저장
			board.setCommentList(commentList);
			
			//3. 조회 수 증가 
			//단, 로그인한 회원과 게시글 작성자가 다를 경우에만 증가 
			if(board.getMemberNo() !=  memberNo) {
				int result = dao.increaseReadCount(conn, boardNo);
				
				if(result >0) {
					commit(conn);
					//미리 조회된 board의 조회 수를 
					//증가된 DB의 조회 수와 동일 한 값을 가지도록 동기화
					board.setReadCount(board.getReadCount()+1);
				}
				else 		  rollback(conn);
			}
		}
		close(conn);
		
		return board;
	}

	/**게시글 수정 서비스
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Board board) throws Exception{
		
		Connection conn = getConnection();
		int result = dao.updateBoard(conn , board);
		
		if(result>0) commit(conn);
		else rollback(conn);;
		
		close(conn);
		
		return result;
	}

	public int delectBoard(int boardNo) throws Exception{
		Connection conn = getConnection();
		
		int result=dao.delectBoard(conn,boardNo);
		
		if(result >0 ) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
}
