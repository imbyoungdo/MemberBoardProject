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
	<h1>company 관리 페이지</h1>
<c:if test="${loginId != null}">
<h3>${loginId}님 환영합니다.</h3>
</c:if>
	<button onclick="location.href='index'">메인</button>
</body>
</html>