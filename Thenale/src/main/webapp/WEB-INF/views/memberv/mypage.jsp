<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>my page</h1>
	<table border=1>
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
		</tr>
		<tr>
			<td>${mDTO.m_id}</td>
			<td>${mDTO.m_password}</td>
			<td>${mDTO.m_name}</td>
			<td>${mDTO.m_email}</td>
			<td>${mDTO.m_phone}</td>
		</tr>
	</table>
	<button onclick="location.href='updateform?m_id=${mDTO.m_id}'">회원 정보 수정</button>
	<button onclick="location.href='index'">메인</button>
</body>
</html>