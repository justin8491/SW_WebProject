<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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

<link rel="stylesheet" href="./css/chat_Insert.css" />
<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap")
	;
</style>
</head>
<body style="background-color: rgba(0, 0, 0, 0.2)">
	<nav class="navbar bg-light fixed-top"
		style="padding-top: 0; padding-bottom: 0">
		<div class="container-fluid" style="background-color: #A4A4A4">
			<a class="navbar-brand" href="main.jsp?id=${id}"
				style="font-size: 2rem">Developer</a> <a class="navbar-brand"
				href="boardList" style="font-size: 1.5rem">게시판</a> <a
				class="navbar-brand" href="chat.jsp?id=${id}"
				style="font-size: 1.5rem">채팅</a> <a class="navbar-brand" href="#"
				style="font-size: 1.5rem"></a> <a class="navbar-brand" href="#"
				style="font-size: 1.5rem"></a> <a class="navbar-brand" href="#"
				style="font-size: 1.5rem"></a> <a class="navbar-brand" href="#"
				style="font-size: 1.5rem"></a> <a class="navbar-brand" href="#"
				style="font-size: 1.5rem"></a>
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
	<!-- 채팅방 div -->
	<section id="container" style="margin-top: 5rem">
		<div>
			<h1>채팅방</h1>
		</div>
		<hr>
		<form>
			<div hidden="">
				<label>사용자명 : </label><input id="user" type="text" value="${id}">
			</div>

			<!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
			<textarea id="messageTextArea" rows="10" cols="50"></textarea>
			<!-- 송신 메시지를 작성하는 텍스트 박스 -->
			<div id="input_box">
				<div>
					<input id="textMessage" onfocus="" type="text">
					<!-- 메시지 송신을 하는 버튼 -->
					<input onclick="sendMessage()" value="전송" type="button">
				</div>

				<!-- WebSocket 접속 종료하는 버튼 -->
				<input onclick="disconnect()" value="연결해제" type="button">
			</div>

		</form>


		<script type="text/javascript">
			// 「WebSocketEx」는 프로젝트 명
			// 「websocket」는 호스트 명
			// WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
			var webSocket = new WebSocket(
					"ws://localhost:8980/WebProject/broadsocket");

			// 콘솔 텍스트 에리어 오브젝트
			var messageTextArea = document.getElementById("messageTextArea");

			// WebSocket 서버와 접속이 되면 호출되는 함수
			webSocket.onopen = function(message) {
				// 콘솔 텍스트에 메시지를 출력한다.
				messageTextArea.value += "서버 연결...\n";
			};
			// WebSocket 서버와 접속이 끊기면 호출되는 함수
			webSocket.onclose = function(message) {
				// 콘솔 텍스트에 메시지를 출력한다.
				messageTextArea.value += "서버 연결 종료...\n";
			};
			// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
			webSocket.onerror = function(message) {
				// 콘솔 텍스트에 메시지를 출력한다.
				messageTextArea.value += "error...\n";
			};
			// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
			webSocket.onmessage = function(message) {
				// 콘솔 텍스트에 메시지를 출력한다.
				messageTextArea.value += message.data + "\n";
			};
			// Send 버튼을 누르면 호출되는 함수
			function sendMessage() {
				// 유저명 텍스트 박스 오브젝트를 취득
				var user = document.getElementById("user");

				// 송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득한다.
				var message = document.getElementById("textMessage");
				// 콘솔 텍스트에 메시지를 출력한다.
				messageTextArea.value += ${id} + " : " + message.value + "\n";
				
				messageTextArea
				//json 객체를 생성한다
				let jsonObject = {
					userName : user.value,
					message : message.value
				};
				//WebSocket 서버에  json 문자열을 전송한다 
				webSocket.send(JSON.stringify(jsonObject));
				// 송신 메시지를 작성하는 텍스트 박스를 초기화한다.
				message.value = "";
			}
			// Disconnect 버튼을 누르면 호출되는 함수
			function disconnect() {
				// WebSocket 접속 해제
				webSocket.close();
			}
		</script>



	</section>
	<!-- 채팅방 div End -->



</body>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
</html>
