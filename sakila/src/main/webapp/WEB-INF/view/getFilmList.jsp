<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getFilmList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</head>
<body>
<div class="container">
    <h1>getFilmList</h1>
    <a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
	<a href="${pageContext.request.contextPath}/admin/getStaffList">직원목록</a>
	<a href="${pageContext.request.contextPath}/admin/getFilmList">영화목록</a>
	
	
	
	<form action = "${pageContext.request.contextPath}/admin/getFilmList" method="get"> 
		<select name = "category">
			<option value = "">카테고리선택</option>
			<c:forEach var ="c" items="${categoryList}">
				<c:if test="${c == category}">
					<option value="${c}" selected="selected">${c}</option>
				</c:if>
				<c:if test="${c != category}">
					<option value="${c}">${c}</option>
				</c:if>
				
			</c:forEach>
		</select>
	
		<select name = "rating">
			<option value = "">관람가선택</option>
			<c:forEach var ="r" items="${ratingList}">
				<c:if test="${r == rating}">
					<option value="${r}" selected="selected">${r}</option>
					
				</c:if>
				<c:if test="${r != rating}">
					<option value="${r}">${r}</option>	
				</c:if>
				
			</c:forEach>
		</select>
		
		<c:if test="${serchWord != null and serchWord != ''}">
			<input name="searchWord" type="hidden" >
		</c:if>
		
		<label for="searchWord">검색어(제목,배우이름) :</label> 
        <input name="searchWord" type="text">
        <c:if test="${category == '카테고리선택'}">
			<input name="category" type="hidden" disabled="disabled">
		</c:if>
		<c:if test="${rating =='관람가선택'}">
			<input name="rating" type="hidden" disabled="disabled">
		</c:if>
        <button type="submit">검색</button>
	</form>
	
	
    <table class="table table-striped">
        <thead>
            <tr>
                <th>FID</th>
                <th>title</th>
                <th>description</th>
                <th>category</th>
                <th>price</th>
                <th>length</th>
                <th>rating</th>
                <th>actors</th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var="f" items="${filmList}">
                <tr>
                	<td>${f.FID}</td>
                	<td><a href="${pageContext.request.contextPath}/admin/getFilmOne?filmId=${f.FID}">${f.title}</a></td>
                	<td>${f.description}</td>
                	<td>${f.category}</td>
                	<td>${f.price}</td>
                	<td>${f.length}</td>
                	<td>${f.rating}</td>
                	<td>${f.actors}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&searchWord=${searchWord}&category=${category}&rating=${rating}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&searchWord=${searchWord}&category=${category}&rating=${rating}">다음</a></li>
        </c:if>
    </ul>
</div>
</body>
</html>