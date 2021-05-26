<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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

<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>

<script>
    $(document).ready(function() {
        $('#addButton').click(function() {
			if ($('#firstName').val().length < 4) {
                alert('firstName는 3자이상 이어야 합니다');
                $('#firstName').focus();
            } else if ($('#lastName').val().length < 4) {
                alert('lastName는 3자이상 이어야 합니다');
                $('#lastName').focus();
            } else {
                $('#addForm').submit();
            }
        });
    });
</script>
<title>ADD ACTOR</title>
</head>
<body>
    <div class="container">
        <h1>ADD ACTOR</h1>
        <form id="addForm" action="${pageContext.request.contextPath}/admin/addActor" method="post">

            <div class="form-group">
                <label for="firstName">firstName :</label> 
                <input class="form-control" name="firstName" id="firstName" type="text" />
            </div>
			<div class="form-group">
                <label for="lastName">lastName :</label> 
                <input class="form-control" name="lastName" id="lastName" type="text" />
            </div>
            <div>
                <input class="btn btn-default" id="addButton" type="button" value="글입력" /> 
                <input class="btn btn-default" type="reset" value="초기화" /> 
                <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getActorList">글목록</a>
            </div>
        </form>
    </div>
</body>
</html> 