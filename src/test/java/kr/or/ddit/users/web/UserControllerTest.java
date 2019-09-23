package kr.or.ddit.users.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.users.model.UsersVO;
import kr.or.ddit.users.service.IUserService;

public class UserControllerTest extends WebTestConfig{

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : loginViewTest
	* 작성자 : PC-08
	* 변경이력 :
	* Method 설명 : 로그인 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void loginViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/login", mav.getViewName());
	}
	
	/**
	* Method : loginProcessTest
	* 작성자 : PC-08
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 요청 테스트
	 */
	@Test
	public void loginProcessTest() throws Exception {
		/***Given***/
		MockHttpSession session = new MockHttpSession();
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login")
											.param("userId", "moon")
											.param("pass", "moon1234")
											.session(session))
									.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		UsersVO user = (UsersVO) session.getAttribute("S_USERVO");
		
		/***Then***/
		assertEquals("commonJsp/main", mav.getViewName());
		assertNotNull(user);
	}
}
