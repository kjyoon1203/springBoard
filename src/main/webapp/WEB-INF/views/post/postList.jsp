<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>게시판</title>
<%@include file="../commonJsp/basicLib.jsp" %>
<style>
	th, td{
		text-align: center;
	}
	#title{
		text-align: left;
	}
</style>
<script>
	$(function(){
		$(".postTr").click(function(){
			var postNoValue = $(this).data("postno");
			
			$("#postNo").val(postNoValue);
			
			$("#frm").submit();
		});
	});
</script>
</head>

<body>
	<form id="frm" action="${cp }/postDetail" method="get">
		<input type="hidden" id="boardNo" name="boardNo" value="${boardNo }"/>
		<input type="hidden" id="postNo" name="postNo"/>
	</form>
	<%@include file="../commonJsp/header.jsp" %>
	
	<div class="container-fluid">
		<div class="row">
				
			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left -->
				<%@include file="../commonJsp/left.jsp" %>
			</div>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${board.boardNm }</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>게시글 번호</th>
									<th>제목</th>
									<th>작성자 아이디</th>
									<th>작성일시</th>
								</tr>
								
								<c:forEach items="${postList}" var="post">
									<c:choose>
										<c:when test="${post.DELETEYN=='Y' }">
											<tr>
												<td style="text-align:center;" colspan="4">삭제된 게시글입니다.</td>
											<tr>
										</c:when>
										<c:otherwise>
											<tr class="postTr" data-postNo = "${post.POSTNO }">
												<td>${post.POSTNO}</td>
												
												<td id="title">
													<c:forEach begin="1" end="${post.LEVEL }">
													&nbsp;&nbsp;&nbsp;&nbsp;
													</c:forEach>
													
													<c:if test="${post.LEVEL > 1 }">
													└ [답글]
													</c:if>
													
													${post.POSTTITLE}
												</td>
												
												
												
												<td>${post.USERID}</td>
												<td>${post.POSTREGIDATE}</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${empty postList }">
									<tr class="postTr">
										<td style="text-align: center;" colspan="4">게시글이 없습니다.</td>
									</tr>							
								</c:if>
							</table>
						</div>
				
						<a href="${cp }/postEnroll?boardNo=${boardNo}" class="btn btn-default pull-right">게시글 등록</a>
	
						<c:if test="${not empty postList }">
							<div class="text-center">
								<ul class="pagination">
									<c:choose>
										<c:when test="${pageVo.page == 1 }">
											<li class="disabled"><span aria-hidden="true">&lt;&lt;</span>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${cp }/postList?boardNo=${boardNo }&page=1" aria-label="Previous"> 
												<span aria-hidden="true">&lt;&lt;</span>
											</a></li>
										</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${pageVo.page == 1 }">
											<li class="disabled"><span aria-hidden="true">&lt;</span>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${cp }/postList?boardNo=${boardNo }&page=${pageVo.page-1 }" aria-label="Previous"> 
												<span aria-hidden="true">&lt;</span>
											</a></li>
										</c:otherwise>
									</c:choose>
	
									<c:forEach begin="1" end="${pageVo.paginationSize }" var="page">
										<c:choose>
											<c:when test="${page == pageVo.page }">
												<li class="active"><span>${page }</span></li>
											</c:when>
											<c:otherwise>
												<li><a href="${cp }/postList?boardNo=${boardNo }&page=${page}">${page }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
	
									<c:choose>
										<c:when test="${pageVo.page == pageVo.paginationSize }">
											<li class="disabled"><span aria-hidden="true">&gt;</span>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${cp }/postList?boardNo=${boardNo }&page=${pageVo.page+1 }" aria-label="Next"> 
												<span aria-hidden="true">&gt;</span>
											</a></li>
										</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${pageVo.page == pageVo.paginationSize }">
											<li class="disabled"><span aria-hidden="true">&gt;&gt;</span>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${cp }/postList?boardNo=${boardNo }&page=${pageVo.paginationSize }" aria-label="Next"> 
												<span aria-hidden="true">&gt;&gt;</span>
											</a></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>												
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>