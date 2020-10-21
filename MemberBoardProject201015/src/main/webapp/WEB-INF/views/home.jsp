<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>MemboerBoard 201015.</h1>

	<P>현재시각 = ${serverTime}.</P>


	<button onclick="location.href='joinform'">회원가입</button>

	<button onclick="location.href='loginform'">로그인</button>

	<h3>카카오로 회원가입</h3>
	<a href="kakaojoin"> <img
		src="${pageContext.request.contextPath }/resources/img/kakao_login_large_wide.png"
		style="width: 600; height: 100;">
	</a>

	<h3>네이버로 회원가입</h3>
	<a href="naverjoin"> <img
		src="${pageContext.request.contextPath }/resources/img/Log%20in%20with%20NAVER_Official_White.PNG"
		style="width: 600; height: 100;">
	</a>

</body>
</html>
