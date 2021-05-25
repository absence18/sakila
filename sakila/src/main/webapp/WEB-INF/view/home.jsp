
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#btn').click(function () {
			$('#loginForm').submit();
		});
	});
</script>

</head>
<body>
	<h1>Home</h1>
	<a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
	<a href="${pageContext.request.contextPath}/admin/getStaffList">직원목록</a>
	<a href="${pageContext.request.contextPath}/admin/getFilmList">영화목록</a>
	<!-- 로그오프 일때 -->
	<c:if test="${loginStaff == null}">
		<form id = "loginForm" action="${pageContext.request.contextPath}/login" method="post">
			<div>email :</div>
			<div><input type = "text" id = "email" name = "email"></div>
			<div>password :</div>
			<div><input type = "password" id="password" name = "password"></div>
			<div>
				<button id = "btn" type = "button">로그인</button>
			</div>
			
		</form>
	</c:if>
	
	<!-- 로그온 일때 -->
	<c:if test="${loginStaff != null}">
		<a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
		<a href="${pageContext.request.contextPath}/admin/logout">로그아웃</a>
	</c:if>
	
	
	
</body>
</html>