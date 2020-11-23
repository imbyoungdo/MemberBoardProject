<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
//아이디 정규식
function idCheck() {
		var exp = /^[a-z]+[a-z0-9]{4,19}$/g;
	var id = document.getElementById("mid").value;
	if (id.match(exp)) {
		alert('완료되었습니다.');
	} else if (id.length == 0) {
		alert('아이디를 입력하세요');

	} else {
		alert('아이디를 5~20자로 입력하세요');
	}
	
	var inputId = document.getElementById("m_id").value;
	var idch = document.getElementById("idch");
	$.ajax({
		type : "post",
		url : "idchk",
		data : {"m_id" : inputId},
		dataType : "text",
		
		success : function(result){
			if(result == "OK"){
				idch.style.color = "green";
				idch.innerHTML = "사용가능한 ID 입니다.";
			}else{
				idch.style.color = "red";
				idch.innerHTML = "이미 사용중인 ID 입니다.";
			}
		},
		error : function(){
			idch.style.color = "red";
			idch.innerHTML = "ajax 실패!!";
		}
	});
}

//비밀번호 확인
function pwdEqFn1() {
	var pwd = document.getElementById("m_password").value;
	var pwdch = document.getElementById("m_passwordch").value;
	var eqmsg = document.getElementById("pwdEq");
	if (pwd == pwdch) {
		eqmsg.style.color = "green";
		eqmsg.style.fontSize = "12px";
		eqmsg.innerHTML = "일치";
	} else {
		eqmsg.style.color = "red";
		eqmsg.style.fontSize = "12px";
		eqmsg.innerHTML = "불일치";
	}
}
//비밀번호 형식 확인
function pwdCheck1() {
	var exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%#?&])[A-Za-z\d$@$!%#?&]{8,16}$/;
	var pwd = document.getElementById("m_password").value;
	var pwdch = document.getElementById("pwdch");
	if (pwd.match(exp)) {
		pwdch.style.color = "green";
		pwdch.innerHTML = "비밀번호 형식 맞음";
		pwdch.style.fontSize = "12px";
	} else {
		pwdch.style.color = "red";
		pwdch.innerHTML = "소문자,대문자,숫자,특수문자가 포함되고 자릿수는 8~16";
		pwdch.style.fontSize = "12px";
	}
}
//전화번호 정규식
function phonech() {
	var pnum = document.getElementById("m_phone").value;
	var exp = /^\d{3}-\d{4}-\d{4}$/;
	var num = document.getElementById("pch");
	if (pnum.match(exp)) {
		num.style.color = "green";
		num.innerHTML = "전화번호 형식 맞음";
		num.style.fontSize = "12px";
	} else {
		num.style.color = "red";
		num.style.fontSize = "12px";
		num.innerHTML = "전화번호 형식 안맞음";
	}
}
</script>
</head>
<body>
	<h1>멤버 수정</h1>
	<form action="memberupdateprocess" method="post" enctype="multipart/form-data">
	아이디<br> 
	<input type="text" name="m_id" id="m_id" value="${mDTO.m_id}" readonly><br>
	비밀번호<br> 
	<input type="text" name="m_password" value="${mDTO.m_password}" id="mpassword" onkeyup="pwdCheck1()"><br> <span
			id="pwdch"></span><br>
	비밀번호 확인<br> 
	<input type="text" name="m_password" id="m_passwordch" onkeyup="pwdEqFn1()"><br> <span id="pwdEq"></span><br>
	이름 <br> 
	<input type="text" name="m_name" value="${mDTO.m_name}" id="m_name"><br>
	이메일<br>  
	<input type="text" name="m_email" value="${mDTO.m_email}" id="m_email"><br>
	전화번호<br> 
	<input type="text" name="m_phone" value="${mDTO.m_phone}" id="m_phone" onkeyup="phonech()"><br>
		<span id="pch"></span><br>
	프로필<br> 
	<input type="file" name="mfile" id="mfile"><br>
	<input type="submit" value="수정">
	</form>
</body>
</html>