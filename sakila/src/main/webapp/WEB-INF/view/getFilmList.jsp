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
	<a href="${pageContext.request.contextPath}/admin/getActorList">배우목록</a>
	
	
	
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
		<select name = "rentalRate">
			<option value = "">가격선택</option>
			<c:if test="${rentalRate == 0.99}">
				<option value = "0.99" selected="selected">0.99</option>
			</c:if>
			<c:if test="${rentalRate != 0.99}">
				<option value = "0.99">0.99</option>
			</c:if>
			<c:if test="${rentalRate == 2.99}">
				<option value = "2.99" selected="selected">2.99</option>
			</c:if>
			<c:if test="${rentalRate != 2.99}">
				<option value = "2.99">2.99</option>
			</c:if>
			<c:if test="${rentalRate == 4.99}">
				<option value = "4.99" selected="selected">4.99</option>
			</c:if>
			<c:if test="${rentalRate != 4.99}">
				<option value = "4.99">4.99</option>
			</c:if>
		</select>
		
		<!--
		<select name = "rentalRate">
			<option value = "">가격선택</option>
			<c:forEach var ="rate" items="${rentalRateList}">
				<c:if test="${rate == rentalRate}">
					<option value="${rate}" selected="selected">${rate}</option>
					
				</c:if>
				<c:if test="${rate != rentalRate}">
					<option value="${rate}">${rate}</option>	
				</c:if>
				
			</c:forEach>
		</select>
		-->
		<c:if test="${serchWord != null and serchWord != ''}">
			<input name="searchWord" type="hidden" >
		</c:if>
		
		<label for="searchWord">title검색 :</label> 
        <input name="searchWord" type="text" value="${searchWord}">
        <label for="searchActor">actor검색 :</label> 
        <input name="searchActor" type="text" value="${searchActor}">
        <button type="submit">검색</button>
	</form>
	
	
    <table class="table table-striped">
        <thead>
            <tr>
                <th>FID</th>
                <th>title</th>
                <th>category</th>
                <th>price</th>
                <th>length</th>
                <th>rating</th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var="f" items="${filmList}">
                <tr>
                	<td>${f.FID}</td>
                	<td><a href="${pageContext.request.contextPath}/admin/getFilmOne?filmId=${f.FID}&currentPage=${currentPage}&searchWord=${searchWord}&category=${category}&rating=${rating}&rentalRate=${rentalRate}&searchActor=${searchActor}">${f.title}</a></td>
                	<td>${f.category}</td>
                	<td>${f.price}</td>
                	<td>${f.length}</td>
                	<td>${f.rating}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&searchWord=${searchWord}&category=${category}&rating=${rating}&rentalRate=${rentalRate}&searchActor=${searchActor}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&searchWord=${searchWord}&category=${category}&rating=${rating}&rentalRate=${rentalRate}&searchActor=${searchActor}">다음</a></li>
        </c:if>
    </ul>
</div>
</body>
</html>