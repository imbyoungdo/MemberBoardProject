<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>MemberList입니다.</h2>

	<table border='3'>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>조회</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="list" items="${memberList}">
			<tr>
				<td>${list.mid}</td>
				<td>${list.mname}</td>
				<td>${list.mdate}</td>
				<td>${list.madress}</td>
				<td>${list.mphone}</td>
				<td>${list.memail}</td>
				<td><a href="memberview?mid=${list.mid}">조회</a></td>
				<td><a href="memberdelete?mid=${list.mid}">삭제</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>