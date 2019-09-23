<%@page import="kr.or.ddit.users.model.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>
	<script src="${cp}/js/jquery-3.4.1.min.js"></script>
	<script src="${cp}/js/js.cookie.js"></script>
    <link href="${cp}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${cp}/css/signin.css" rel="stylesheet">
	<script>
		$(function(){
			var userId = Cookies.get("userId");
			if(userId != undefined){
				$('#userId').val(userId);
				// remember me checkbox 체크
				$('#rememberMe').prop("checked", true);
				
				$('#pass').focus();
			}
			// signin btn 클릭 이벤트 핸들러
			 $('#signinBtn').click(function(){

				// 로그인 요청
				$('#frm').submit();
				
			 });
		});
	
	</script>
  </head>

<body>
	<div class="container">
		<%
			HttpSession httpSession = request.getSession();
		    		UsersVO userVo = (UsersVO)httpSession.getAttribute("S_USERVO");
		    		String userName = "";
		    		userName = userVo == null ? "" : userVo.getUserNm();
		%>
		<form id="frm" class="form-signin" action="${cp}/login" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="userId" class="sr-only">userId</label>
			<%
        	String userId = request.getParameter("userId");
        	userId = userId == null ? "" : userId;
        %>
			<input type="text" id="userId" name="userId" class="form-control"
				placeholder="ID" required autofocus> <label for="pass"
				class="sr-only">Password</label> <input type="password" id="pass"
				name="pass" class="form-control" placeholder="Password" required>
			<div class="checkbox">
				<label> <input id="rememberMe" name="rememberMe"
					type="checkbox" value="remember-me"> Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="button"
				id="signinBtn">Sign in</button>
		</form>
	</div>
</body>
</html>
    