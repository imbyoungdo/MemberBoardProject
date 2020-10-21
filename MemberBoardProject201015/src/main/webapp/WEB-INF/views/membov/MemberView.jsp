<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><h2>MemberView입니다.</h2>

이름 : ${memberView.mname}<br>
생년월일 : ${memberView.mdate}<br>
이메일 : ${memberView.memail}<br>
주소 : ${memberView.madress}<br>
전화번호 : ${memberView.mphone}<br>


<a href="memberupdate?mid=${memberView.mid}">회원 정보 수정</a>

</body>
</html>