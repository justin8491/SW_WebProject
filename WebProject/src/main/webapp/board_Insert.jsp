<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

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

<link rel="stylesheet" href="/WebProject/css/board_Update.css" />
<!-- <script type="text/javascript" src="/WebProject/js/board_Detail.js" -->
defer>
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap")
	;
</style>
<script
	src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/ckeditor.js"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/translations/ko.js"></script>
</head>
<body style="background-color: rgba(0, 0, 0, 0.2)">
	<nav class="navbar bg-light fixed-top"
		style="padding-top: 0; padding-bottom: 0">
		<div class="container-fluid"
			style="background-color: rgba(0, 0, 0, 0.7)">
			<a class="navbar-brand" href="main.jsp?id=${id}"
				style="font-size: 2rem">Developer</a> <a class="navbar-brand"
				href="boardList" style="font-size: 1.5rem">게시판</a> <a
				class="navbar-brand" href="chat.jsp?id=${id}" style="font-size: 1.5rem">채팅</a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a>
			<!-- 유저 세션 닉네임 -->
			<a class="navbar-brand" href="detail" style="font-size: 1.5rem">
				${id} 님 </a>

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
					<h5 class="offcanvas-title" id="sideBarLabel">Menu Bar</h5>
					<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
						aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<ul id="sideBar"
						class="navbar-nav justify-content-end flex-grow-1 pe-3">
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

	<section class="container">
		<div id="boardTable">
			<form id="boardInsert" action="boardInsert" method="post">
				<!-- Category -->
				<h1>글쓰기</h1>
				<hr>
				<!-- Title -->
				<input type="text" name="title" id="title"> <select
					name="category" id="category">
					<option value="선택">카테고리 선택</option>
					<option value="공지사항">공지사항</option>
					<option value="일반게시판">일반게시판</option>
					<option value="건의사항">건의사항</option>
					<option value="Q&A">Q&A</option>
				</select> <br>

				<!-- Content -->
				<textarea name="content" id="editor"></textarea>
				<input type="submit" class="btn btn-primary" value="등록">
				<button id="cancel" onclick="location.href='boardList'"
					type="button" class="btn btn-danger">취소</button>
			</form>
		</div>
	</section>



</body>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/d350cb3dc1.js"
	crossorigin="anonymous"></script>
<script>
        ClassicEditor
            .create( document.querySelector( '#editor' ), {language : "ko"} )
            .catch( error => {
                console.error( error );
        } );
</script>
</html>
