package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {
	
	/**
	* Method : getBoardList
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 게시판 목록 조회
	 */
	List<BoardVO> getBoardList();
	
	/**
	* Method : insertBoard
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param map
	* @return
	* Method 설명 : 게시판 생성
	 */
	int insertBoard(Map<String, Object> map);
	
	/**
	* Method : updateBoard
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param map
	* @return
	* Method 설명 : 게시판 수정
	 */
	int updateBoard(Map<String, Object> map);
	
	/**
	* Method : getBoard
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param boardNo
	* @return
	* Method 설명 : 게시판 조회
	 */
	BoardVO getBoard(int boardNo);
}
