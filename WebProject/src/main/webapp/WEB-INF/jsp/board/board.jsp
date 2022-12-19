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
<!-- 프로그래스바 스크립트 START -->
<script>
// 인터넷 스크롤 이동 시 이벤트
window.onscroll = function() {createPrograssBar()}; 

function createPrograssBar() {
  var winScroll = document.body.scrollTop || document.documentElement.scrollTop;
  var height = document.documentElement.scrollHeight - document.documentElement.clientHeight;
  var scrolled = (winScroll / height) * 100;
  document.getElementById("indicator").style.width = scrolled + "%";
}
</script>
<!-- 프로그래스바 스크립트 END -->
<meta charset="UTF-8" />
<title>Main</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous" />

<link rel="stylesheet" href="/WebProject/css/board.css" />
<script type="text/javascript" src="./js/board.js" defer></script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap")
	;
</style>
</head>
<body>
	<!-- progress-bar 영역 Start -->
	<div class="progress-container">
		<div class="progress-bar" id="indicator"></div>
	</div>
	<!-- progress-bar 영역 End -->
	<!-- 탑 로고 시작 -->
	<nav class="navbar bg-light fixed-top"
		style="padding-top: 0; padding-bottom: 0">
		<div class="container-fluid"
			style="background-color: #A4A4A4" >
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
				${id} 님
			</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
				aria-controls="offcanvasNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- 탑 사이드 바 태그 -->
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
							href='/WebProject/member/logout.do'>Logout</a></li>
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
	<!-- 탑 or 사이드 End -->
	
	<div id="tableHeader">
		<!-- 검색창 -->
		<div>
			<form id="Search" action="Search" method="post">
				<select name="selectValue">
					<option value="title">제목</option>
					<option value="id">작성자</option>
				</select> 
				<input id="searchText" type="text" name="searchValue" placeholder="검색어" /> 
				<input id="submit"
					type="submit" value="검색" />
			</form>
		</div>
		<!-- 글쓰기 버튼 -->
		<div>
			<a href="board_Insert.jsp" class="btn btn-primary me-md-2"
				role="button" id="write-article">글쓰기</a>
		</div>
	</div><!-- 검색 글쓰기 End -->
	<!-- 테이블 시작 -->
	<table id="boardTable">
		<tr align="center">
			<td><b>분류</b></td>
			<td colspan="2"><b>제목</b></td>
			<td><b>작성자</b></td>
			<td><b>작성일</b></td>
			<td><b>조회수</b></td>
		</tr>
		<c:choose>
			<c:when test="${ empty boardList}"></c:when>
		</c:choose>
		<c:choose>
			<c:when test="${!empty boardList}">
				<c:forEach var="b" items="${boardList}">
					<tr align="center">
						<td id="category">${b.category}</td>
						<td id="boardNO">${b.boardNO}</td>
						<td id="title"><a href="boardDetail.do?boardNO=${b.boardNO}">${b.title}</a></td>
						<td id="id">${b.id}</td>
						<td id="writeDate">${b.writeDate}</td>
						<td id="view">${b.view}</td>
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
