package kr.or.ddit.users.web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.users.model.UsersVO;
import kr.or.ddit.users.service.IUserService;

@Controller
public class LoginController {
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	/**
	* Method : view
	* 작성자 : PC-08
	* 변경이력 :
	* @return
	* Method 설명 : 로그인 화면 요청처리
	 */
	@RequestMapping(path = "login", method = RequestMethod.GET)
	public String view() {
		// cookie 처리는 생략
		return "user/login";
	}
	
	/**
	* Method : loginProcess
	* 작성자 : PC-08
	* 변경이력 :
	* @param userId
	* @param pass
	* @param rememberMe
	* @param response
	* @param session
	* @return
	* Method 설명 : 로그인 요청 처리
	 */
	@RequestMapping(path = "login", method = RequestMethod.POST)
	public String loginProcess(String userId, String pass, String rememberMe, HttpServletResponse response, HttpSession session, Model model) {
		manageUserIdCookie(response, userId, rememberMe);
		UsersVO user = userService.getUser(userId);
		
		if(user == null) {
			return "user/login";	// view();
		}	
		else if(user.checkLoginValidate(userId, pass)){
			session.setAttribute("S_USERVO", user);			
			model.addAttribute("boardList", boardService.getBoardList());
			return "commonJsp/main";
		}else {
			return "user/login";	// view();
		}
	}
	
	private void manageUserIdCookie(HttpServletResponse response, String userId, String rememberMe) {
		// rememberMe 파라미터가 존재할 경우 userId를 cookie로 생성
		Cookie cookie = new Cookie("userId", userId);
		if(rememberMe != null) {
			cookie.setMaxAge(60*60*24*30);	// 유효기간: 30일
		}else {
			cookie.setMaxAge(0);	// 유효기간: 0으로 주면 쿠기 바로 삭제 / 음수로 주면 웹브라우저가 닫힐 때 삭제
		}
		response.addCookie(cookie);
	}
}
