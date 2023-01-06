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
		String html = "<!DOCTYPE html>\r\n" + "				<html lang=\"en\">\r\n" + "				<head>\r\n"
				+ "				<meta charset=\"UTF-8\" />\r\n"
				+ "				<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n"
				+ "				<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
				+ "\r\n" + "				<!-- Bootstrap CSS only -->\r\n" + "				<link\r\n"
				+ "					href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\"\r\n"
				+ "					rel=\"stylesheet\"\r\n"
				+ "					integrity=\"sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi\"\r\n"
				+ "					crossorigin=\"anonymous\" />\r\n" + "				<!-- Font Awesome CDN -->\r\n"
				+ "				<script src=\"https://kit.fontawesome.com/d350cb3dc1.js\"\r\n"
				+ "					crossorigin=\"anonymous\"></script>\r\n"
				+ "				<!-- Main Script -->\r\n"
				+ "				<script type=\"text/javascript\" src=\"./js/login.js\" defer></script>\r\n"
				+ "				<link rel=\"stylesheet\" href=\"./css/login.css\" />\r\n" + "				<style>\r\n"
				+ "				@import url(\"https://fonts.googleapis.com/css2?family=Jua&display=swap\")\r\n"
				+ "					;\r\n" + "\r\n" + "				a {\r\n"
				+ "					margin-top: 0; text-decoration : none;\r\n" + "					color: black;\r\n"
				+ "					text-decoration: none;\r\n" + "					padding-right: 1rem;\r\n"
				+ "					padding-right: 1rem;\r\n" + "				}\r\n" + "\r\n"
				+ "				#loginForm {\r\n" + "				background-color: white;\r\n"
				+ "				border-radius: 2rem;\r\n" + "				padding: 2rem;\r\n" + "				}\r\n"
				+ "\r\n" + "				#id_check {\r\n" + "\r\n" + "				}\r\n" + "\r\n"
				+ "				</style>\r\n" + "				<title>Main</title>\r\n" + "				</head>\r\n"
				+ "				<body id=\"main\">\r\n" + "					<header id=\"header\">\r\n"
				+ "						<h1>Developer</h1>\r\n" + "					</header>\r\n"
				+ "					<section class=\"container\">\r\n"
				+ "						<form id=\"loginForm\" method=\"post\" action=\"login.jsp\">\r\n" + "\r\n"
				+ "							<h1>당신의 아이디는 :";
		html += memberBean.getId();

		html += "</h1> <br>\r\n" + "							<a href='login.jsp'> 로그인하러 가기</a>\r\n"
				+ "							</div>\r\n" + "						</form>\r\n"
				+ "					</section>\r\n" + "					<footer>\r\n" + "\r\n"
				+ "						<p>한국 산업 소프트웨어 ERP 기반 클라우드 전문가 양성 과정</p>\r\n"
				+ "						<br> <span>@유종현</span>\r\n" + "					</footer>\r\n"
				+ "				</body>\r\n" + "				<!-- Bootstrap JavaScript Bundle with Popper -->\r\n"
				+ "				<script\r\n"
				+ "					src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js\"\r\n"
				+ "					integrity=\"sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3\"\r\n"
				+ "					crossorigin=\"anonymous\"></script>\r\n" + "				</html>";

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
