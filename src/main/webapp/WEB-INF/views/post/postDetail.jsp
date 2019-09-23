<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>게시판</title>
<%@include file="../commonJsp/basicLib.jsp" %>
<script>
	$(function(){
		$(".replySave").click(function(){
			$("#replyFrm").submit();
		});
		
		$("#replyCont").keyup(function() {
	         var len = $(this).val().length;
	         $('#counter').html("("+len+" / 최대 500자)");

	         if(len >= 500) {
	            $(this).val($(this).val().slice(0, 500));
	            alert("댓글은 500자를 넘길 수 없습니다.")
	         }
	     });
	});
</script>
</head>

<body>

	<%@include file="../commonJsp/header.jsp" %>
	
	<div class="container-fluid">
		<div class="row">
				
			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left -->
				<%@include file="../commonJsp/left.jsp" %>
			</div>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				
				<form id="frm" class="form-horizontal" role="form" action="${cp }/postModify" method="post" enctype="multipart/form-data">
					
					<div class="form-group">
						<label for="postNm" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<label class="control-label">${post.POSTTITLE }</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userId" class="col-sm-2 control-label">글내용</label>
						<div class="col-sm-10">
							<label class="control-label">${post.POSTCONT }</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-10">
							<c:choose>
								<c:when test="${!empty attachedFileList}">
									<c:forEach items="${attachedFileList }" var="file">
										<a href="${cp }/fileDownload?fileNo=${file.fileNo}" 
										download="${file.fileUploadNm }" class="control-label">
											${file.fileUploadNm }
										</a>
										<br>
									</c:forEach>
								</c:when>
								<c:otherwise>
									첨부파일 없음.
								</c:otherwise>
							</c:choose>
							<br>
						</div>
					</div>
					
					<div class="form-group" style="margin: auto 150px;">
						<c:choose>
							<c:when test="${S_USERVO.userId==post.USERID }">
								<a href="${cp }/postModify?boardNo=${boardNo}&postNo=${post.POSTNO}" class="btn btn-default">수정</a>
								<a href="${cp }/postDelete?boardNo=${boardNo}&postNo=${post.POSTNO}" class="btn btn-default">삭제</a>
								<a href="${cp }/postEnroll?boardNo=${boardNo }&parentPostNo=${post.POSTNO}" class="btn btn-default">답글</a>
							</c:when>
							<c:otherwise>
								<a href="${cp }/postEnroll?boardNo=${boardNo }&parentPostNo=${post.POSTNO}" class="btn btn-default">답글</a>
							</c:otherwise>
						</c:choose>
					</div>
					<br>
				</form>
					
					<br>
					<div class="form-group">
						<label for="reply" class="col-sm-2 control-label">댓글</label>
						<div class="col-sm-10">
							<c:forEach items="${replyList }" var="reply">
								<c:choose>
									<c:when test="${reply.DELETEYN == 'Y'}">
										<p style="font-weight: bold;">[삭제된 댓글입니다]</p>
									</c:when>
									<c:otherwise>
										<div class="replyBox">
											<label class="control-label">${reply.REPLYCONT} [${reply.USERID } / ${reply.REPLYREGIDATE }]</label>
											<c:if test="${S_USERVO.userId==reply.USERID }">
													<a href="${cp }/replyDelete?boardNo=${boardNo }&postNo=${post.POSTNO }&replyNo=${reply.REPLYNO}"> 
														<span class="glyphicon glyphicon-remove"></span>
													</a>
											</c:if>
											<br>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<form id="replyFrm" class="form-horizontal" role="form" action="${cp }/replySave" method="post">
								<input type="hidden" name="boardNo" value="${boardNo }">
								<input type="hidden" name="postNo" value="${post.POSTNO }">
								<input type="hidden" name="userId" value="${S_USERVO.userId }">
								<input type="hidden" name="postNo" value="${S_USERVO.userId }">
								<input type="text" style="width: 500px; float: left;" class="form-control" id="replyCont" name="replyCont"></textarea>
							</form>
							<a class="btn btn-default replySave">댓글 저장</a>
							<br>
							<span style="color:#aaa;" id="counter">(0 / 최대 500자)</span>
						</div>
					</div>
			</div>
		</div>
	</div>
</body>
</html>