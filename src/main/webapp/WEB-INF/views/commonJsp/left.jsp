<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<ul class="nav nav-sidebar">
	<!--  a tag: get method -->
	<li class="active"><a href="${cp}/boardManage">게시판 생성</a></li>
	<br>
	<c:forEach items="${boardList}" var="board">
		<c:choose>
			<c:when test="${board.useYN=='Y'}">
				<li class="active"><a href="${cp}/postList?boardNo=${board.boardNo}&page=1">${board.boardNm }</a></li>
			</c:when>
		</c:choose>
	</c:forEach>
</ul>