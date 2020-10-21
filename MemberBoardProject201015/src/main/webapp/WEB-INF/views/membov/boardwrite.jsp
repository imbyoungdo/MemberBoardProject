<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function boardwritefile() {
		
		writeform.submit();

	}
	function BoardList() {

		location.href = 'boardlist';

	}
</script>
</head>
<body>
<h2>boardwrite입니다.</h2>

<form action="boardwrite" method="post" name="writeform" enctype="multipart/form-data">
		<br>
		작성자 <input type="text" id="bid" name="bid" value="${sessionScope.loginId}" readonly><br>
		비밀번호 <input type="text" id="bpassword" name="bpassword"><br>
		제목 <input type="text" id="btitle" name="btitle"><br>
		내용<textarea rows="10" cols="10" id="bcontents" name="bcontents"></textarea>
		첨부파일 <input type="file" id="bfile" name="bfile"><br>

	</form>

	<button onclick="boardwritefile()">작성완료</button>
	<button onclick="BoardList()">글 목록</button>




</body>
</html>