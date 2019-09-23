package kr.or.ddit.users.repository;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.users.model.UsersVO;

public interface IUserDao {
	/**
	 * 
	* Method : getUser
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param userId
	* @return
	* Method 설명 : userId를 갖는 사용자 정보 조회
	 */
	UsersVO getUser( String userId);
	

}
