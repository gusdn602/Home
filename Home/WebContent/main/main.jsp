<%@ page language="java" contentType="text/html" charset="UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Home/css/w3.css">
<link rel="stylesheet" href="/Home/css/user.css">
<script type="text/javascript" src="./js/jquery-3.5.0.min.js"></script>
</head>
<body>
	<div class="w3-content mxw2">
		<h2 class="w3-deep-orange w3-padding w3-margin-bottom w3-center w3-card">Home Project Main</h2>
		<div class="w3-col">
			<c:if test="${empty SID}">
				<div class="btn w3-button w3-green inblock w-150" id="login">로그인</div>
				<div class="btn w3-button w3-pink inblock w-150" id="Join">회원가입</div>
			</c:if>
			<c:if test="${not empty SID}">
				<div class="btn w3-button w3-green inblock w-150" id="logout">로그아웃</div>
				<div class="btn w3-button w3-pink inblock w-150" id="gboard">gboard</div>
			</c:if>
		</div>
	</div>
</body>
</html>