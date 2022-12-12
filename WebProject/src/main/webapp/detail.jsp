<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8" />
<title>Main</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous" />

<link rel="stylesheet" href="./css/detail.css" />
<script type="text/javascript" src="./js/detail.js" defer></script>
</head>
<body style="background-color: rgba(0, 0, 0, 0.2);">
	<nav class="navbar bg-light fixed-top"
		style="padding-top: 0; padding-bottom: 0">
		<div class="container-fluid"
			style="background-color: #A4A4A4">
			<a class="navbar-brand" href="main.jsp?id=${id}" style="font-size: 2rem">Developer</a>
			<a class="navbar-brand" href="boardList" style="font-size: 1.5rem">게시판</a> <a
				class="navbar-brand" href="chat.jsp?id=${id}" style="font-size: 1.5rem">채팅</a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a>
			<!-- 유저 세션 닉네임 -->
			<a class="navbar-brand" href="detail" style="font-size: 1.5rem"> <%
 session = request.getSession();
 out.print(session.getAttribute("id"));
 %> 님
			</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
				aria-controls="offcanvasNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- 탑 nav 바 태그 -->
			<div style="background-color: rgba(0, 0, 0, 0.1)"
				class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar"
				aria-labelledby="offcanvasNavbarLabel">
				<div class="offcanvas-header">
					<h5 style="color: black" class="offcanvas-title"
						id="offcanvasNavbarLabel">Menu Bar</h5>
					<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
						aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="main.jsp?id=${id}">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="boardList">Boards</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="chat.jsp?id=${id}">Talk</a></li>
						<li class="nav-item"><a class="nav-link" id="logout"
							href='logout'>Logout</a></li>
					</ul>
					<form class="d-flex mt-3" role="search">
						<input class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search" />
						<button class="btn btn-outline-success" type="submit">
							Search</button>
					</form>
				</div>
			</div>
		</div>
	</nav>

	<!-- 상세페이지 시작 -->
	<section id="container">
		<ul id="userInfo">
			<li id="img_box"><img id="infoImg" alt=""
				src="./images/info.jpg"></li>
			<li id="id_box"><label>아이디 :</label>
				<p>
					${id}
				</p></li>
			<li id="pwd_box"><label>비밀번호 :</label>
				<p>
					${pwd}
				</p></li>
			<li id="name_box"><label>이름 :</label>
				<p>
					${name}
				</p></li>
			<li id="phone_box"><label>핸드폰 번호 :</label>
				<p>
					${phone}
				</p></li>
			<li id="email_box"><label>이메일 :</label>
				<p>
					${email}
				</p></li>
			<li id="createdate_box"><label>회원 가입일 :</label>
				<p>
					${createdate}
				</p></li>
		</ul>
		<div id="btn_box">
			<button id="updateBtn" onclick="location.href='update.jsp'" type="button" class="btn btn-primary">회원정보 수정</button>
			<button id="deleteBtn" type="button" class="btn btn-danger">회원 탈퇴</button>
		</div>
	</section>

</body>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
</html>