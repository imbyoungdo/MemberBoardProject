<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function boardupdate() {
		var password = '${boardUpdate.bpassword}';
		var passConfirm = document.getElementById("bpassword").value;

		if (password == passConfirm) {

			boardupdateform.submit();

		} else {
			alert('비밀번호가 틀립니다.')
		}
	}
</script>
</head>
<body>
	<h2>boardUpdate입니다.</h2>



<form action="boardupdateprocess" method="post" name="boardupdateform" ><br>
		글번호 <input type="text" id="bnumber" name="bnumber" value="${boardUpdate.bnumber}" readonly><br>
	  	작성자 <input type="text" id="bid" name="bid" value="${boardUpdate.bid}" readonly><br>
	  	비밀번호 <input type="text" id="bpassword" name="bpassword"><br>
		제목 <input type="text" id="btitle" name="btitle" value="${boardUpdate.btitle}"><br>
	  	내용 <textarea rows="10" cols="10" id="bcontents" name="bcontents">${boardUpdate.bcontents}</textarea>

	</form>

<button onclick="boardupdate()"> 수정완료</button>

<button onclick="location.href= 'boardlist'"> 글 목록</button>



</body>
</html>