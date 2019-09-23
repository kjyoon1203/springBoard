package kr.or.ddit.users.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.users.model.UsersVO;

public class UserDaoTest extends RootTestConfig {
	// userDao를 테스트 하기 위해 필요한 파일
	// db연결, 트랜잭션, Dao

	private String userId = "brownTest";

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	/**
	 * Method : getUserTest 
	 * 작성자 : PC-08 
	 * 변경이력 : 
	 * Method 설명 : 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UsersVO user = userDao.getUser(userId);
		
		/***Then***/
		assertEquals("브라운", user.getUserNm());
	}
}
