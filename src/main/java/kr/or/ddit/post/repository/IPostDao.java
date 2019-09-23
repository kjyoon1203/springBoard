package kr.or.ddit.post.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.post.model.PostVO;

public interface IPostDao {
	
	/**
	* Method : getPostAllList
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param boardNo
	* @return
	* Method 설명 : 게시판번호가 일치하는 게시글 목록 조회
	 */
	List<PostVO> getPostAllList(int boardNo);
	
	/**
	* Method : getPostingUser
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : 게시글번호에 맞는 userId 조회
	 */
	String getPostingUser(int postNo);
	
	/**
	* Method : getPostPagingList
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param map (boardNo, Page)
	* @return
	* Method 설명 : 해당 게시판의 페이징 처리된 게시글 목록 조회
	 */
	List<Map> getPostPagingList(Map<String, Object> paramMap);
	
	/**
	* Method : getPostTotalCnt
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param boardNo
	* @return
	* Method 설명 : 해당 게시판의 총 게시글 수
	 */
	int getPostTotalCnt(Map<String, Object> paramMap);
	
	/**
	* Method : getPost
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param postVO
	* @return
	* Method 설명 : 해당 게시글 조회
	 */
	Map getPost( int postNo);
	
	/**
	* Method : insertPost
	* 작성자 : PC-08
	* 변경이력 :
	* @param postParamMap
	* @return
	* Method 설명 : 게시글 등록
	 */
	int insertPost(Map<String, Object> postParamMap);
	
	/**
	* Method : updatePost
	* 작성자 : PC-08
	* 변경이력 :
	* @param postNo
	* @return
	* Method 설명 : 게시글 삭제
	 */
	int deletePost(int postNo);
	
	/**
	* Method : update
	* 작성자 : PC-08
	* 변경이력 :
	* @param postParamMap
	* @return
	* Method 설명 : 게시글 수정
	 */
	int updatePost(Map<String, Object> postParamMap);
}
