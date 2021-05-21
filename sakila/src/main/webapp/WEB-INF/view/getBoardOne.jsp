<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD VIEW(spring mvc 방식)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<!-- jquery를 사용하기위한 CDN주소 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>BOARD VIEW</h1>

		<table class="table">
			<tbody>
				<tr>
					<td>board Id</td>
					<td>${boardMap.boardId}</td>
				</tr>
				<tr>
					<td>board Title</td>
					<td>${boardMap.boardTitle}</td>
				</tr>
				<tr>
					<td>board Content</td>
					<td>${boardMap.boardContent}</td>
				</tr>
				<tr>
					<td>user Name</td>
					<td>${boardMap.username}</td>
				</tr>
				<tr>
					<td>board file</td>
					<td>
						<div>
							<a
								href="${pageContext.request.contextPath}/admin/addBoardfile?boardId=${boardMap.boardId}"><button
									class="btn btn-default btn-sm" type="button">파일추가</button></a>
						</div> <!-- 보드파일 출력하는 반복문 코드 구현 --> <c:forEach var="f"
							items="${boardfileList}">
							<div>
								<a
									href="${pageContext.request.contextPath}/resource/${f.boardfileName}">${f.boardfileName}</a>
								<a
									href="${pageContext.request.contextPath}/admin/removeBoardfile?boardfileId=${f.boardfileId}&boardId=${f.boardId}&boardfileName=${f.boardfileName}">
									<button type="button">파일삭제</button>
								</a>
							</div>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>insert Date</td>
					<td>${boardMap.insertDate}</td>
				</tr>
			</tbody>
		</table>
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/admin/modifyBoard?boardId=${boardMap.boardId}">수정</a>
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/admin/removeBoard?boardId=${boardMap.boardId}">삭제</a>
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/admin/getBoardList">목록</a> <br>
		<br>
		<br>

		<h4>댓글 목록</h4>
		<!-- 댓글 목록 -->
		<div>
			<!-- 댓글 추가 -->
			<form id="commentForm"
				action="${pageContext.request.contextPath}/admin/addComment"
				method="post">
				<input type="hidden" name="boardId" value="${boardMap.boardId}">
				<input id="username" type="text" name="username"
					placeholder="username">
				<div>
					<textarea id="addComment" name="commentContent" rows="5" cols="130"></textarea>
				</div>
				<button class="btn btn-default btn-sm" id="btn" type="submit">댓글
					입력</button>
			</form>
			<!-- end -->
			<table class="table">
				<c:forEach var="c" items="${commentList}">
					<tr>
						<td>${c.commentContent}</td>
						<td>${c.username}</td>
						<td>${c.insertDate.substring(0,10)}</td>
						<td><a
							href="${pageContext.request.contextPath}/admin/removeComment?boardId=${boardMap.boardId}&commentId=${c.commentId}">
								<button class="btn btn-default btn-sm" type="button">삭제</button>
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>
</body>
</html>