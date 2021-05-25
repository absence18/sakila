<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>staffList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<div class = "container">
	<h1>직원목록</h1>
	<a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
	<a href="${pageContext.request.contextPath}/admin/getStaffList">직원목록</a>
	<a href="${pageContext.request.contextPath}/admin/getFilmList">영화목록</a>
	<table class="table table-striped">
        <thead>
            <tr>
                <th>staff ID</th>
                <th>staff name</th>
                <th>staff address</th>
                <th>staff zipCode</th>
                <th>staff phone</th>
                <th>staff city</th>
                <th>staff country</th>
                <th>staff SID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${staffViewList}">
                <tr>
                	<td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${s.address}</td>
                    <td>${s.zipCode}</td>
                    <td>${s.phone}</td>
                    <td>${s.city}</td>
                    <td>${s.country}</td>
                    <td>${s.SID}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
	
	
</body>
</html>