<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>영화(film) 출연자(actor) 수정</h1>
	<form>
		<c:forEach var="m" items="${filmActorList}">
			<c:if test="${m.filmId == null}">
				<input type="checkbox" name="ck" value="${m.actorId}">
			</c:if>
			<c:if test="${m.filmId != null}">
				<input type="checkbox" name="ck" checked="checked" value="${m.actorId}">
			</c:if>
			<span style="color:red;">${m.name.substring(0, 1)}</span>${m.name.substring(1)}&nbsp;
		</c:forEach>
		<div>
			<button type="button">출연배우 수정</button>
		</div>
	</form>
</body>
</html>