<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>login입니다.</h2>


	<form action="login" method="post" name="loginform">

		아이디 <input type="text" id="mid" name="mid"><br> 비밀번호 <input
			type="text" id="mpassword" name="mpassword"> <br> <input
			type="submit" value="로그인">
	</form>
	<h3>카카오로 로그인</h3>
	<a href="kakaologin"> <img
		src="${pageContext.request.contextPath }/resources/img/kakao_login_large_wide.png"
		style="width: 600; height: 100;">
	</a>
	<h3>네이버로 로그인</h3>
	<a href="naverlogin"> <img
		src="${pageContext.request.contextPath }/resources/img/Log in with NAVER_Official_White.PNG"
		style="width: 600; height: 100;">
	</a>

</body>
</html>