<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<script>
/* 주소 API */
function sample6_execDaumPostcode() {
	new daum.Postcode(
			{
				oncomplete : function(data) {

					var addr = ''; // 주소 변수
					var extraAddr = ''; // 참고항목 변수

					if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
						addr = data.roadAddress;
					} else { // 사용자가 지번 주소를 선택했을 경우(J)
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
									+ data.buildingName : data.buildingName);
						}
						if (extraAddr !== '') {
							extraAddr = ' (' + extraAddr + ')';
						}
						document.getElementById("sample6_extraAddress").value = extraAddr;

					} else {
						document.getElementById("sample6_extraAddress").value = '';
					}

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById('sample6_postcode').value = data.zonecode;
					document.getElementById("sample6_address").value = addr;
					// 커서를 상세주소 필드로 이동한다.
					document.getElementById("sample6_detailAddress")
							.focus();
				}
			}).open();
}
</script>

<body>
	<h1>업체등록</h1>
	
	<form action="comjoinform" method="post" enctype="multipart/form-data">
	아이디<br>
	<input type="text" name="c_id" id="c_id" onkeyup="idOverlap()">
	<span id="idch"></span>
    <input type="button" value="아이디중복확인" onclick="idOverlap()"><br>
    <p></p>    
	비밀번호<br>
	<input type="text" name="c_password" id="c_password" onkeyup="pwdCheck1()"><br> 
	<span id="pwdch"></span>
	<p></p>
	비밀번호 확인<br>
	<input type="text" name="c_passwordch" id="c_passwordch" onkeyup="pwdEqFn1()"><br> <span id="pwdEq"></span>
	<p></p>
	업체명<br>
	<input type="text" name="c_name" id="c_name"><br><p></p>
	프로필<br>
	<input type="file" name="cfile" id="cfile"><br><p></p>
	설명<br>
	<input type="text" name="c_contents" id="c_contents"><br><p></p>
	주소<br>
	<input type="text" id="sample6_postcode"  name="c_address" placeholder="우편번호"> <p></p>
	<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br><p></p>
	<input type="text" id="sample6_address"  name="c_address" placeholder="주소"><br><p></p>
	<input type="text" id="sample6_detailAddress" name="c_address" placeholder="상세주소">
	<input type="text" id="sample6_extraAddress" name="c_address" placeholder="참고항목"><br><p></p>
	y 좌표<br>
	<input type="text" name="c_y" id="c_y"><br><p></p>
	x 좌표<br>
	<input type="text" name="c_x" id="c_x"><br><p></p>
	업종<br>
	<select name="c_sort">
		<option value="숙박">숙박</option>
		<option value="식당">식당</option>
	</select><br>
	전화번호
	<input type="text" id="c_ceo_tel" name="c_ceo_tel"><br><p></p>
	이름
	<input type="text" id="c_ceo" name="c_ceo"><br><p></p>
	지역
	<select name="c_area">
		<option value="제주">제주</option>
		<option value="부산">부산</option>
		<option value="강륵,속초">강릉,속초</option>
		<option value="전주">전주</option>
		<option value="경주">경주</option>
	</select><br>
	<p></p> 
	<input type="submit" value="회원가입">
	</form>
	
</body>
</html>