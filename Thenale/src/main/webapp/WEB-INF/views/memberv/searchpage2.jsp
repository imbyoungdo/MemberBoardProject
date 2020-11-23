<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="areachoice" method="post">

<h2>어떤 여행을 계획 중이신가요?</h2>

<input type="radio" name="type_num" value="1">먹는거 = 남는거!! 하나라도 더먹게쓰~<br>
<input type="radio" name="type_num" value="2">쉬고 싶다... 힐링이 하고싶다...<br>
<input type="radio" name="type_num" value="3">우리 사랑 영원히~~<br>
<input type="radio" name="type_num" value="4">나 이런데도 가봤어!!<br>
<input type="radio" name="type_num" value="5">나는 누구인가, 어디서 와서 어디로 가는가...<br>

<h2>누구와 여행을 가시나요?</h2>

<input type="radio" name="with_num" value="1">혼자
<input type="radio" name="with_num" value="2">커플
<input type="radio" name="with_num" value="3">가족
<input type="radio" name="with_num" value="4">친구
<br>
<input type="hidden" name="area" value="${searchArea.area}">
<button type="submit">위 내용으로 계획짜기</button>
</form>






</body>
</html>