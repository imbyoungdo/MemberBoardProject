<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><h2>memberUpdate입니다.</h2>

<form action="memberUpdateProcess" method="post" name="memberUpdateProcessForm">

아이디 -> <input type="text" id="mid" name="mid" value="${memberUpdate.mid}" readonly><br>
이름 -> <input type="text" id="mname" name="mname" value="${memberUpdate.mname}" readonly><br>
생년월일 -> <input type="text" id="mdate" name="mdate" value="${memberUpdate.mdate}" readonly><br>
이메일 -> <input type="text" id="memail" name="memail" value="${memberUpdate.memail}"><br>
주소 -> <input type="text" id="madress" name="madress" value="${memberUpdate.madress}"><br>
전화번호 -> <input type="text" id="mphone" name="mphone" value="${memberUpdate.mphone}"><br>

<input type="submit" value="수정완료">
</form>

</body>
</html>