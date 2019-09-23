package kr.or.ddit.reply.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface IReplyDao {
	/**
	* Method : getReply
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : 해당 게시글의 댓글 조회
	 */
	List<Map> getReply( int postNo); 
	
	/**
	* Method : insertReply
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param paramMap
	* @return
	* Method 설명 : 댓글 등록
	 */
	int insertReply( Map paramMap);
	
	/**
	* Method : updateReply
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param replyNo
	* @return
	* Method 설명 : 댓글 삭제
	 */
	int deleteReply( int replyNo);
}
