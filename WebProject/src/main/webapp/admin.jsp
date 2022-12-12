<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%
session = request.getSession();
%>
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

<link rel="stylesheet" href="./css/admin.css" />
<script type="text/javascript" src="./js/admin.js" defer></script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap")
	;
</style>
</head>
<body>
	<nav class="navbar bg-light fixed-top"
		style="padding-top: 0; padding-bottom: 0">
		<div class="container-fluid"
			style="background-color: #A4A4A4" >
			<a class="navbar-brand" href="list" style="font-size: 2rem">Developer</a>
			<a class="navbar-brand" href="list" style="font-size: 1.5rem">회원
				목록</a> <a class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a> <a
				class="navbar-brand" href="#" style="font-size: 1.5rem"></a>
			<!-- 유저 세션 닉네임 -->
			<a class="navbar-brand" href="detail" style="font-size: 1.5rem">
				관리자 님 </a>

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
					<h5 class="offcanvas-title"
						id="sideBarLabel">Menu Bar</h5>
					<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
						aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<ul id="sideBar" class="navbar-nav justify-content-end flex-grow-1 pe-3">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="list">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Boards</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="#">Talk</a></li>
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

	<table id="userTable">
		<tr align="center">
			<td><b>번호</b></td>
			<td><b>아이디</b></td>
			<td><b>비밀번호</b></td>
			<td><b>이름</b></td>
			<td><b>핸드폰 번호</b></td>
			<td><b>이메일</b></td>
			<td><b>회원상태</b></td>
			<td><b>회원 가입일</b></td>
		</tr>
		<c:choose>
			<c:when test="${ empty memberList}"></c:when>
		</c:choose>
		<c:set var="i" value="1" />
		<c:set var="i">1</c:set>
		<c:choose>
			<c:when test="${!empty memberList}">
				<c:forEach var="m" items="${memberList}">
					<tr align="center">
						<td>${i}</td>
						<td>
						<a href="update?id=${m.id}">${m.id}
						
						</a>
						</td>
						<td>${m.pwd}</td>
						<td>${m.name}</td>
						<td>${m.phone}</td>
						<td>${m.email}</td>
						<td>${m.isexist}</td>
						<td>${m.createDate}</td>
						<td><input onclick="location.href='delete?id=${m.id}'" type="button" value="휴면계정"></td>
						<td><input onclick="location.href='undelete?id=${m.id}'" type="button" value="휴면계정 해제"></td>
						<c:set var="i">${i+1}</c:set>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</body>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
</html>
