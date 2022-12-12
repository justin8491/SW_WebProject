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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/insert")
public class InsertMemberServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertMemberServelt() {
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
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		
		String accept = request.getParameter("accept");
		
		String phone = phone1 + "-" + phone2+ "-" + phone3;
		String email = email1 + "@" + email2;
		
		System.out.println("====>" + id);
		System.out.println("====>" + pwd);

		MemberDAO dao = new MemberDAO();
		MemberBean memberBean = new MemberBean();
		memberBean.setId(id);
		memberBean.setPwd(pwd);
		memberBean.setName(name);
		memberBean.setPhone(phone);
		memberBean.setEmail(email);
//		dao.isExisted(memberBean);

		if (!(dao.isExisted(memberBean))) {
			try {
				dao.insertMember(new MemberBean(id, pwd, name, phone, email));
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("에러 내용 : " + e.getMessage());
				
			}
		}
		System.out.println("memberBean ==>"+memberBean);
		
		response.sendRedirect("login.jsp");

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
