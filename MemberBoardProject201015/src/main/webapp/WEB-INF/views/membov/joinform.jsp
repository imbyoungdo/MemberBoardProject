<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	function idol() {
		var inputId = document.getElementById("mid").value;
		var idol = document.getElementById("idol");

		$.ajax({
			type : "post",
			url : "idoverlap",
			data : {
				"mid" : inputId
			},
			dataType : "text",
			success : function(result) {
				if (result == "OK") {
					idol.innerHTML = "멋진 아이디입니다!"
				} else {
					idol.innerHTML = "이미 사용중인 아이디입니다."
				}
			},
			error : function() {
				alert("ajax Fail")
			}
		});
	}

	function pwcheck() {
		var pw = document.getElementById("mpassword").value;
		var exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;

		if (pw.match(exp)) {
			alert('사용 하실 수 있는 비밀번호 입니다.')

		} else {
			alert('비밀번호 형식에 맞지 않습니다.')
		}
	}
	
	
	function passwordeq(){
		var pw = document.getElementById("mpassword").value;
		var pweq = document.getElementById("mpasswordeq").value;
		var pweqch = document.getElementById("pweqch");
		
		if(pw.match(pweq)){
			pweqch.innerHTML = "비밀번호가 같습니다."
		}else{
			
			pweqch.innerHTML = "비밀번호가 같지 않습니다."
		}
		
	}
	function phonum() {
		var pnum = document.getElementById("mphone").value;
		var exp = /^\d{3}-\d{4}-\d{4}$/;
		var phonum = document.getElementById("phonum");

		if (pnum.match(exp)) {
			phonum.innerHTML = "전화번호 형식이 맞습니다.";

		} else {
			phonum.innerHTML = "전화번호 형식이 맞지 않습니다.";
		}

	}
</script>
<body>
	<h2>회원가입!</h2>
<p>카카오 아이디 : ${kakaoId}</p>
<p>네이버 아이디 : ${naverId}</p>
	<form action="memberJoin" method="post" enctype="multipart/form-data"
		name="joinform">
		<c:choose>
			<c:when test="${kakaoId ne null}">
			아이디 : <input type="text" name="mid" id="mid" onkeyup="idol()">
				<input type="hidden" name="kakaoId" value="${kakaoId}">
				<span id="idol"></span>
			</c:when>
			<c:when test="${naverId ne null}">
			아이디 : <input type="text" name="mid" id="mid" onkeyup="idol()">
				<input type="hidden" name="naverId" value="${naverId}">
				<span id="idol"></span>
			</c:when>
			<c:otherwise>
		아이디 <input type="text" id="mid" name="mid" onkeyup="idol()">
				<br>
				<span id="idol"></span>
				<br>
			</c:otherwise>
		</c:choose>
		비밀번호<input type="text" id="mpassword" name="mpassword"
			placeholder="영문,숫자,특문 조합 8~16자"><br> <input
			type="button" value="비밀번호 정규식 확인" onclick="pwcheck()"> <br>
		비밀번호 확인<input type="text" id="mpasswordeq" name="mpasswordeq"><br>
		<input type="button" value="비밀번호 중복확인" onclick="passwordeq()"><br>
		<span id="pweqch"></span><br>
		이름<input type="text" id="mname" name="mname"><br>
		생년월일<input type="date" id="mdate" name="mdate"><br>
		이메일<input type="text" id="memail" name="memail"><br>
		 <input type="text" id="sample3_postcode" name="madress" placeholder="우편번호">
		<input type="button" onclick="sample3_execDaumPostcode()"
			value="우편번호 찾기"><br> <input type="text"
			id="sample3_address" name="madress" placeholder="주소"><br>
		<input type="text" id="sample3_detailAddress" name="madress"
			placeholder="상세주소"> <input type="text"
			id="sample3_extraAddress" name="madress" placeholder="참고항목">
		<div id="wrap"
			style="display: none; border: 1px solid; width: 500px; height: 300px; margin: 5px 0; position: relative">
			<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
				id="btnFoldWrap"
				style="cursor: pointer; position: absolute; right: 0px; top: -1px; z-index: 1"
				onclick="foldDaumPostcode()" alt="접기 버튼">
		</div>
		<script
			src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
			var element_wrap = document.getElementById('wrap');
			function foldDaumPostcode() {
				element_wrap.style.display = 'none';
			}
			function sample3_execDaumPostcode() {
				var currentScroll = Math.max(document.body.scrollTop,
						document.documentElement.scrollTop);
				new daum.Postcode(
						{
							oncomplete : function(data) {
								var addr = '';
								var extraAddr = '';

								if (data.userSelectedType === 'R') {
									addr = data.roadAddress;
								} else {
									addr = data.jibunAddress;
								}
								if (data.userSelectedType === 'R') {
									if (data.bname !== ''
											&& /[동|로|가]$/g.test(data.bname)) {
										extraAddr += data.bname;
									}
									if (data.buildingName !== ''
											&& data.apartment === 'Y') {
										extraAddr += (extraAddr !== '' ? ', '
												+ data.buildingName
												: data.buildingName);
									}
									if (extraAddr !== '') {
										extraAddr = ' (' + extraAddr + ')';
									}
									document
											.getElementById("sample3_extraAddress").value = extraAddr;
								} else {
									document
											.getElementById("sample3_extraAddress").value = '';
								}
								document.getElementById('sample3_postcode').value = data.zonecode;
								document.getElementById("sample3_address").value = addr;
								document
										.getElementById("sample3_detailAddress")
										.focus();
								element_wrap.style.display = 'none';
								document.body.scrollTop = currentScroll;
							},
							onresize : function(size) {
								element_wrap.style.height = size.height + 'px';
							},
							width : '100%',
							height : '100%'
						}).embed(element_wrap);
				element_wrap.style.display = 'block';
			}
		</script>
		<br>
		 전화번호<input type="text" id="mphone" name="mphone"
			onkeyup="phonum()"><br> <span id="phonum"></span><br>
		사진<input type="file" id="mphoto" name="mphoto"><br> <input
			type="submit" value="회원가입">
	</form>

</body>
</html>