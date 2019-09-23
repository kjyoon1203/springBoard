package kr.or.ddit.users.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.users.model.UsersVO;

public class UserServiceTest extends RootTestConfig{

	private String userId = "brownTest";
	
	@Resource(name = "userService")
	private IUserService userService;
	

	/**
	 * Method : getUserTest 
	 * 작성자 : PC-08 
	 * 변경이력 : Method 
	 * 설명 : 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/*** Given ***/
		String userId = "brown";

		/*** When ***/
		UsersVO userVo = userService.getUser(userId);

		/*** Then ***/
		assertEquals("브라운", userVo.getUserNm());
	}
}
