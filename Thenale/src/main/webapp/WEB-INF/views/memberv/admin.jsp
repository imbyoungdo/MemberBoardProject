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
	<h1>관리자</h1>
	<table style="margin: 0 auto;">
		<tr>
			<th>아이디</th>
			<th>업체명</th>
			<th>설명</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="cList" items="${cList}">
			<tr>
				<td>${cList.c_id}</td>
				<td><a href="companyview?c_id=${cList.c_id}">${cList.c_name}</a></td>
				<td>${cList.c_contents}</td>
				<td> <a href="companyDelete?c_id=${cList.c_id}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>