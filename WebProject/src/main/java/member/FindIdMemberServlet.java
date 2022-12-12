package member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/findid")
public class FindIdMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindIdMemberServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		MemberDAO dao = new MemberDAO();
		MemberBean memberBean = new MemberBean();
		memberBean.setName(name);
		memberBean.setEmail(email);
		dao.findId(memberBean);

		System.out.println(memberBean.getId());
		String html = """
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

				#loginForm {
				background-color: white;
				border-radius: 2rem;
				padding: 2rem;
				}

				#id_check {

				}

				</style>
				<title>Main</title>
				</head>
				<body id="main">
					<header id="header">
						<h1>Developer</h1>
					</header>
					<section class="container">
						<form id="loginForm" method="post" action="login.jsp">

							<h1>당신의 아이디는 :""" + memberBean.getId();

		html += """

									</h1> <br>
							<a href='login.jsp'> 로그인하러 가기</a>
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

								""";

		out.println(html);

		// response.addHeader("Refresh", "0;login.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

}
