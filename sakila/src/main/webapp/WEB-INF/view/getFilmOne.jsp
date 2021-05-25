<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>film VIEW(spring mvc 방식)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h1>film VIEW</h1>
     <table class="table">
         <tbody>
			<tr>
                   <td>filmId :</td>
                   <td>${filmOne.filmId}</td>
            </tr>
            <tr>
                   <td>title :</td>
                   <td>${filmOne.title}</td>
            </tr>
            <tr>
                   <td>description :</td>
                   <td>${filmOne.description}</td>
            </tr>
            <tr>
                   <td>releaseYear :</td>
                   <td>${filmOne.releaseYear}</td>
            </tr>
            <tr>
                   <td>languageId :</td>
                   <td>${filmOne.languageId}</td>
            </tr>
            <tr>
                   <td>originalLaguageId :</td>
                   <td>${filmOne.originalLaguageId}</td>
            </tr>
            <tr>
                   <td>rentalDuration :</td>
                   <td>${filmOne.rentalDuration}</td>
            </tr>
            <tr>
                   <td>rentalRate :</td>
                   <td>${filmOne.rentalRate}</td>
            </tr>
            <tr>
                   <td>length :</td>
                   <td>${filmOne.length}</td>
            </tr>
            <tr>
                   <td>replacementCost :</td>
                   <td>${filmOne.replacementCost}</td>
            </tr>
            <tr>
                   <td>rating :</td>
                   <td>${filmOne.rating}</td>
            </tr>
            <tr>
                   <td>specialFeatures :</td>
                   <td>${filmOne.specialFeatures}</td>
            </tr>
            <tr>
                   <td>lastUpdate :</td>
                   <td>${filmOne.lastUpdate}</td>
            </tr>
            <tr>
                   <td>stock of store1 :</td>
                   <td>${store1Stock}</td>
            </tr>
            <tr>
                   <td>stock of store2 :</td>
                   <td>${store2Stock}</td>
            </tr>
            
        </tbody>
    </table>
    	<div>
		    <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getFilmList">글목록</a>	
    	</div>
	
</div>
</body>
</html>