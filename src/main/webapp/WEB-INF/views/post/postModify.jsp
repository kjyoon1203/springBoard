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
		$(".glyphicon-remove").click(function(){
			$(this).parents().find(".fileDiv").remove();
		});
		
		$(".glyphicon-plus").click(function(){
			var labelId = 0;
			$($(this).parents().find(".fileLabel")).each(function(i){
				labelId = ++i;
			});
			
			$('input[name=file]').each(function(i){
				if(i < (4-labelId)){
					code = '<input type="file" id="file['+(++i)+']" class="form-control" name="file">';
				}else{
					code = "";	
				}
			});
			$(".fileInput").append(code);
		});
		
		$("#saveBtn").click(function(){
			$("#frm").submit();
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
				
				<form id="frm" class="form-horizontal" role="form"
						action="${cp }/postModify" method="post"
						enctype="multipart/form-data">
					<input type="hidden" name="userId" value="${S_USERVO.userId }">
					<input type="hidden" name="boardNo" value="${boardNo }">
					<input type="hidden" name="postNo" value="${post.POSTNO}">
					<input type="hidden" name="parentPostNo" value="${post.PARENTPOSTNO}">
					<%-- <c:forEach items="${attachedFileList }" var="file">
						<input type="hidden" name="fileNo" value="${file.fileNo}">
					</c:forEach> --%>
					<div class="form-group">
						<label for="postNm" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="postTitle" name="postTitle"
								value="${post.POSTTITLE}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userId" class="col-sm-2 control-label">글내용</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="postCont" name="postCont"
								value="${post.POSTCONT}">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-10 fileBox">
							<c:forEach items="${attachedFileList }" var="file" varStatus="i">
								<div class = "fileDiv">
									<label id=${i.count } class="control-label fileLabel">${file.fileUploadNm }</label>
									<a href="${cp }/fileDelete?boardNo=${boardNo }&postNo=${post.POSTNO}&fileNo=${file.fileNo}"> 
										<span class="glyphicon glyphicon-remove"></span>
									</a>
									<br>
								</div>
							</c:forEach>
							<br>
							<div class="col-sm-8 fileInput">
								<input type="file" id="file[0]" class="form-control" name="file"> 
							</div>
							<a id="filePlus" href="#"> 
								<span style="margin:5px; font-size: 1.5em" class="glyphicon glyphicon-plus"></span>
							</a>
							<a class="btn btn-default pull-right" id="saveBtn">저장</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>