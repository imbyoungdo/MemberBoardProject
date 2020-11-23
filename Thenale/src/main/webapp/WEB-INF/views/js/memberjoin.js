/**
 * MemberJoin Scprit
 */
//비밀번호 확인
function pwdEqFn1() {
	var pwd = document.getElementById("mpassword").value;
	var pwdch = document.getElementById("mpasswordch").value;
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

function pwdCheck1() {
	var exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%#?&])[A-Za-z\d$@$!%#?&]{8,16}$/;
	var pwd = document.getElementById("mpassword").value;
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

function phonech() {
	var pnum = document.getElementById("mphone").value;
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