<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>hotellist입니다.</h2>
	​
	<c:forEach var="hotel" items="${hList}">
		<table border="1">
			<tr>
				<th>이름
				<th>위치
				<th>사진
			<tr>
				<td><a href="#">${hotel.h_name}</a><br>
				<td>${hotel.h_address}<br>
				<td>${hotel.h_photo}<br>
		</table>
	</c:forEach>​

</body>
</html>