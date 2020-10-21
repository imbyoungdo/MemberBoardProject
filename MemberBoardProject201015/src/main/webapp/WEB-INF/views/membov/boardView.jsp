<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
$(document).ready(function(){
	
	$("#commentWriteBtn").click(function(){
		var cwriter = $("#cwriter").val();
		var ccontents = $("#ccontents").val();
		var cbnumber = "${boardView.bnumber}";
		$.ajax({
			type : "post",
			url : "comment/commentwrite",
			data : {
				"cwriter" : cwriter,
				"ccontents" :ccontents,
				"cbnumber" : cbnumber},
			dataType : "json",
			success : function(result){
				console.log("댓글성공");
				console.log(result);
				
				var output = "<table border='1'>";
				output += "<tr><th>작성자</th>";
				output += "<th>내용</th></tr>";
				for(var i in result){
					output += "<tr>";
					output += "<td>"+result[i].cwriter+"</td>"
					output += "<td>"+result[i].ccontents+"</td>"
					output += "</tr>"
				}
				output +="</table>"
				$("#commentArea").html(output);
				$("#cwriter").val("");
				$("#ccontents").val("");
				
			},
			error : function(){
				console.log("댓글실패");
			}
		});
	});
	
});
</script>
</head>
<body>
	<h2>boardView입니다.</h2>


	글번호 : ${boardView.bnumber}
	<br> 제목 : ${boardView.btitle}
	<br> 작성자 : ${boardView.bid}
	<br> 작성 날짜 : ${boardView.bdate}
	<br> 내용 : ${boardView.bcontents}
	<br> 조회수 : ${boardView.bhits}
	<br>





	<a href="boardupdate?bnumber=${boardView.bnumber}">수정</a>

	<a href="boarddelete?bnumber=${boardView.bnumber}">삭제</a>

	<button onclick="location.href= 'boardlist' ">글 목록</button>

	<div id="commentWrite">
	
	
		<input type="hidden" id="cwriter" value="${sessionScope.loginId}" readonly><br>
		
		
		 내용 : <input
			type="text" id="ccontents"><br>
		<button id="commentWriteBtn">댓글 입력</button>

	</div>


	<div id="commentArea">
		<table border='1'>
			<tr>
				<th>작성자</th>
				<th>내용</th>
				<c:forEach var="comment" items="${commentView}">
					<tr>
						<td>${comment.cwriter}</td>
						<td>${comment.ccontents}</td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</div>

</body>
</html>