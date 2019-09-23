<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>게시판 관리</title>
<%@include file="../commonJsp/basicLib.jsp"%>
<style>
label {
	float: left;
	padding: 5px 10px;
}

.form-control {
	width: 200px;
	float: left;
	margin: 0 10px;
}

.btn {
	margin: 0 10px;
}
</style>
<script>
	$(function() {
// 		var boardNm = $("#boardNm").val().trim();
// 		$(".btn").click(function(){
// 			if(boardNm == ""){
// 				alert("게시판 이름을 설정해주세요.");
// 				return;
// 			}			
// 		});
		
		$("#insert").click(function() {
			$("#frminsert").submit();
		});
		
		$(".modify").click(function() {
			$(this).parent("form").submit();
		});
	});
</script>
</head>
<body>
	<%@include file="../commonJsp/header.jsp"%>

	<div class="col-sm-3 col-md-2 sidebar">
		<!-- left -->
		<%@include file="../commonJsp/left.jsp"%>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

		<div class="row">
			<div class="col-sm-8 blog-main">
				<h2 class="sub-header">게시판</h2>
				<form id="frminsert" action="${cp }/boardManage" method="post">
					<input type="hidden" name="userId" value="${S_USERVO.userId }">
					<input type="hidden" name="btn" value="insert">
					
					<label>게시판 이름 </label> 
					<input type="text" class="form-control" id="boardNm" name="boardNm" /> 
					
					<select id="useYN" name="useYN">
						<option value="Y">사용</option>
						<option value="N">미사용</option>
					</select>
					
					<button class="btn btn-success" id="insert" type="button" value="insert">생성</button>
				</form>
				<br>
				<c:forEach items="${boardList}" var="board">
					<form id="frm${board.boardNo }" action="${cp }/boardManage" method="post">
						<input type="hidden" name="userId" value="${S_USERVO.userId }">
						<input type="hidden" name="boardNo" value="${board.boardNo }">
						<input type="hidden" name="btn" value="modify">
						
						<label>게시판 이름 </label>
						<input type="text" class="form-control" id="boardNm" name="boardNm" value="${board.boardNm }">
						
						<select id="useYN" name="useYN">
							<option value='Y' <c:if test="${board.useYN == 'Y' }"> selected </c:if>>사용</option>
							<option value='N' <c:if test="${board.useYN == 'N' }"> selected </c:if>>미사용</option>
						</select>
						
						<button class="btn btn-primary modify"  type="button" value="modify">수정</button>
					</form>
					<br>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>