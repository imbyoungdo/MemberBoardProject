<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>company login</h1>
	<form action="companylogin" method="post">
		아이디<br> <input type="text" name="c_id" id="c_id" placeholder="아이디를 입력하세요" maxlength="20" size="50px">
		<p></p>
		비밀번호<br> <input type="text" name="c_password" id="c_password" maxlength="20" placeholder="비밀번호를 입력하세요" size="50px"><br>
		<p></p>
		<input type="submit" value="로그인">
		</form>
		
		<input type="button" onclick="location.href='memberjoinform'" value="회원가입">
</body>
</html>