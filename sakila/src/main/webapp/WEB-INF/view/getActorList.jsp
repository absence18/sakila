<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getActorList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<div class="container">
    <h1>getActorList</h1>
    <a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
	<a href="${pageContext.request.contextPath}/admin/getStaffList">직원목록</a>
	<a href="${pageContext.request.contextPath}/admin/getFilmList">영화목록</a>
	<a href="${pageContext.request.contextPath}/admin/getActorList">배우목록</a>
	
    <table class="table table-striped">
        <thead>
            <tr>
                <th>actorId</th>
                <th>actorName</th>
                <th>filmInfo</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="a" items="${actorList}">
                <tr>
                	<!-- 추가적으로 배우이름 누르면 상세페이지로 이동하고 거기서 출연영화들 보여주는것으로 바꾸기 (영화는 필름 테이블과 조인해서 id값 가져오기) -->
                	<td>${a.actorId}</td>
                	<!-- 상세페이지 작성예정 -->
                    <td><a href="${pageContext.request.contextPath}/admin/getActordOne?actorId=${a.actorId}">${a.name}</a></td>
                    <td>${a.filmInfo}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 검색어 입력창 -->
    <form action="" method="get">
        <label for="searchWord">검색어(이름) :</label> 
        <input name="searchWord" type="text">
        <button type="submit">검색</button>
    </form>

    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getActorList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getActorList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
        </c:if>
    </ul>
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addActor">배우추가</a>
    </div>
</div>
</body>
</html> 