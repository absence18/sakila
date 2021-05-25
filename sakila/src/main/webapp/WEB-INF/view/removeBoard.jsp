<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>removeBoard</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#btn').click(function(){
		 if ($('#boardPw').val().length < 4) {
             alert('boardPw는 4자이상 이어야 합니다');
             $('#boardPw').focus();
         } else {
        	 $('#removeForm').submit();
         }
	});
});
</script>
</head>
<body>
	<div class="container">
	    <h1>removeBoard</h1>
	    <form action="${pageContext.request.contextPath}/admin/removeBoard" id="removeForm" method="post">
			<div class="form-group">
			    <label for="boardId">boardId :</label> 
			    <input class="form-control" name="boardId" id="boardId" type="text" readonly="readonly" value="${boardMap.boardId}" />
			</div>
			<div class="form-group">
			    <label for="boardTitle">boardTitle :</label> 
			    <input class="form-control" name="boardTitle" id="boardTitle" type="text" readonly="readonly" value="${boardMap.boardTitle}"/>
			</div>
			<div class="form-group">
			    <label for="boardPw">boardPw :</label> 
			    <input class="form-control" name="boardPw" id="boardPw" type="password" />
			</div>
			<div class="form-group">
			    <label for="boardContent">boardContent :</label>
			    <textarea class="form-control" name="boardContent" id="boardContent"
			        rows="5" cols="50" readonly="readonly">${boardMap.boardContent}</textarea>
			</div>
			<div class="form-group">
			    <label for="userName">userName :</label> 
			    <input class="form-control" type="text" value = "${boardMap.username}" readonly="readonly"/>
			</div>
			<div class="form-group">
			    <label for="insertDate">insertDate :</label> 
			    <input class="form-control" type="text" value = "${boardMap.insertDate}" readonly="readonly"/>
			</div>
					
	       <button class="btn btn-default" id="btn" type="button">삭제</button>
	        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getBoardList">글목록</a>
	    </form>
	</div>
</body>
</html>