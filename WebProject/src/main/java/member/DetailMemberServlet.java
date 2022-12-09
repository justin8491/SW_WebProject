package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowMember
 */
@WebServlet("/detail")
public class DetailMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String id = "";
		Boolean isLogon = false;
		HttpSession session = request.getSession(false);

		if (session != null) {
			isLogon = (Boolean) session.getAttribute("isLogon");
			if (isLogon == true) {
				MemberDAO dao = new MemberDAO();
				id = (String) session.getAttribute("id");
				MemberBean memberBean = new MemberBean();
				if (id.equals("admin")) {
					String getId = request.getParameter("id");
					memberBean = dao.findByUid(getId);

				}

				memberBean = dao.findByUid(id);
				session.setAttribute("id", memberBean.getId());
				session.setAttribute("pwd", memberBean.getPwd());
				session.setAttribute("name", memberBean.getName());
				session.setAttribute("phone", memberBean.getPhone());
				session.setAttribute("email", memberBean.getEmail());
				session.setAttribute("createdate", memberBean.getCreateDate());

				response.sendRedirect("detail.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}
}
