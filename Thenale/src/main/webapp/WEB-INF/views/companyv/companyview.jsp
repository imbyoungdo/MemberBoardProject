<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	
	function adm() {
		admissionform.submit();
	}
</script>
</head>
<body>
	<form action="admission" name="admissonform">
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>업체명</th>
				<th>사진</th>
				<th>설명</th>
				<th>주소</th>
				<th>y좌표</th>
				<th>x좌표</th>
				<th>업종</th>
				<th>번호</th>
				<th>이름</th>
				<th>지역</th>
				<th>등급</th>
				<th>승인</th>
			</tr>
			<tr>
				<td>${cDTO.c_id}</td>
				<td>${cDTO.c_name}</td>
				<td>${cDTO.c_photo}</td>
				<td>${cDTO.c_contents}</td>
				<td>${cDTO.c_address}</td>
				<td>${cDTO.c_y}</td>
				<td>${cDTO.c_x}</td>
				<td>${cDTO.c_sort}</td>
				<td>${cDTO.c_ceo_tel}</td>
				<td>${cDTO.c_ceo}</td>
				<td>${cDTO.c_area}</td>
				<td>${cDTO.c_auth}</td>
				<td><button onclick="adm()">승인하기</button></td>
			</tr>
		</table>
		<input type="hidden" name="c_id" value="${cDTO.c_id}"> <input
			type="hidden" name="c_name" value="${cDTO.c_name}"> <input
			type="hidden" name="c_photo" value="${cDTO.c_photo}"> <input
			type="hidden" name="c_contents" value="${cDTO.c_contents}"> <input
			type="hidden" name="c_x" value="${cDTO.c_x}"> <input
			type="hidden" name="c_y" value="${cDTO.c_y}"> <input
			type="hidden" name="c_sort" value="${cDTO.c_sort}"> <input
			type="hidden" name="c_area" value="${cDTO.c_area}"> <input
			type="hidden" name="c_address" value="${cDTO.c_address}">
	</form>
	
	<button onclick="location.href='index'">메인</button>
</body>
</html>