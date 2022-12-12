package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberDAO;

import java.io.IOException;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/undelete")
public class DeleteFalseMemberServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteFalseMemberServelt() {
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
		HttpSession session = request.getSession(false);
		String getId = request.getParameter("id");

		if (session != null) { //세션 값 없을 때
			MemberDAO dao = new MemberDAO();
			String id = (String) session.getAttribute("id");
			if(id.equals("admin")) { // 로그인 계정인 경우
				dao.deleteMamber(getId, "1");
				
				response.sendRedirect("list");
			} else {
				dao.deleteMamber(id, "1");
				response.sendRedirect("login.jsp");
			}
			
			
		} else {
			System.out.println("세션 값 없음");
			response.sendRedirect("login.jsp");
		}

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
