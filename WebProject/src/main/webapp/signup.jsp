<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="./css/signup.css" />
<!-- Font Awesome CDN -->
<script src="https://kit.fontawesome.com/d350cb3dc1.js"
	crossorigin="anonymous"></script>
<style type="text/css">
.fa-times {
color: red;
}

.fa-check {
	display : none;
	color : green;
}
</style>
<title>회원가입</title>
</head>
<body>
	<!-- 
            1. 이름
            2. 아이디
            3. 비밀번호
            4. 패스워드 or 패스워드 확인
            5. 전화번호
            6. 이메일
         -->
	<header id="header">
		<h1>회원가입</h1>
	</header>
	<section id="information">
		<div id="infoImgs">
			<img id="infoImg" alt="" src="./images/information.gif" /> <img
				id="infoImg" alt="" src="./images/accept.gif" />
		</div>
		<form id="information__form" action="insert" method="post">
			<div id="information__form-list">
				<div id="name_check">
					<input id="name" name="name" type="text" placeholder="이름" /><i class="success fa fa-check"></i><i  class="fail fa fa-times"></i>
				</div>

				<div id="id_check">
					<input id="id" name="id" type="text" placeholder="아이디" /><i 
						class="fa fa-check"></i><i  class="fail fa fa-times"></i>
				</div>
				<div id="pwd_check">
					<input id="pwd" name="pwd" type="password" placeholder="비밀번호" /> <input
						id="pwd_chenge_check" type="password" placeholder="비밀번호 재입력" /><i 
						class="fa fa-check"></i><i  class="fail fa fa-times"></i>
				</div>

				<div id="phone_check">
					<select id="phone1" name="phone1">
						<option value="010">휴대폰 번호 선택</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="02">02</option>
						<option value="031">031</option>
						<option value="070">070</option>
					</select>
					<p>-</p>
					<input type="text" id="phone2" name="phone2" />
					<p>-</p>
					<input type="text" id="phone3" name="phone3" /><br /> <i 
						class="fa fa-check"></i><i  class="fail fa fa-times"></i>
				</div>
				<div id="email_check">
					<input id="email1" name="email1" type="text" placeholder="이메일" /> @ 
					<select id="email2" name="email2">
						<option>이메일 선택</option>
						<option>naver.com</option>
						<option>daum.net</option>
						<option>google.com</option>
						<option>hanmail.net</option>
						<option>hatmail.com</option>
						<option>nate.com</option>
						<option>직접입력</option>
					</select> 
					<i  hidden="" class="fa fa-check"></i>
					<i  class="fail fa fa-times"></i>
				</div>
				
				<div id="accept_check">
					<p class="sort_row accept">
						<input id="accept" name="accept" type="checkbox"/>개인정보 수집 및 이용<span>(필수)</span>
					</p>
				</div>
				<div id="btn_check">
					<input id="submit" type="submit" value="회원가입" /> 
					<input type="button" id="cancel"
						value="취소" onclick="location.href='login.jsp'"/>
				</div>
			</div>
		</form>
	</section>
</body>
<script type="text/javascript" src="./js/signup.js"></script>
</html>

