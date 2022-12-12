<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Bootstrap CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous" />
<!-- Font Awesome CDN -->
<script src="https://kit.fontawesome.com/d350cb3dc1.js"
	crossorigin="anonymous"></script>
<!-- Main Script -->
<script type="text/javascript" src="./js/login.js" defer></script>
<link rel="stylesheet" href="./css/login.css" />
<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap")
	;

a {
	margin-top: 0; text-decoration : none;
	color: black;
	text-decoration: none;
	padding-right: 1rem;
	padding-right: 1rem;  
}

#id_check {
	
}

a:hover {
	color: white;
}
</style>
<title>Main</title>
</head>
<body id="main">
	<header id="header">
		<h1>Developer</h1>
	</header>
	<section class="container">
		<form id="loginForm" method="post" action="login">
			<div id="loginForm__container">
				<div id="id_box" class="form-floating mb-3">
					<input name="id" type="text" class="form-control"
						id="floatingInput" placeholder="name@example.com" /> <label
						for="floatingInput">User ID </label>
				</div>
				<div id="pwd_box" class="form-floating">
					<input name="pwd" type="password" class="form-control"
						id="floatingPassword" placeholder="Password" /> <label
						for="floatingPassword">Password</label>
				</div>
				<div id="btn_box">
					<a id="id_find" href="find_id.jsp">Find ID</a> <a id="pw_find"
						href="find_pw.jsp">Find PW</a>
				</div>
			</div>



			<div id="btn_box">
				<input id="login" type="submit" value="Login" /> <input id="signUp"
					type="button" value="SignUp" />
			</div>
		</form>
	</section>
	<footer>

		<p>한국 산업 소프트웨어 ERP 기반 클라우드 전문가 양성 과정</p>
		<br> <span>@유종현</span>
	</footer>
</body>
<!-- Bootstrap JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
</html>
