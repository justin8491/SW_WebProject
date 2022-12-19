//package member;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.jasper.tagplugins.jstl.core.Out;
//
///**
// * Servlet implementation class LoginServlet
// */
////@WebServlet("/login")
//public class LoginMemberServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public LoginMemberServlet() {
//		super();
//	}
//
//	@Override
//	public void init() throws ServletException {
//		super.init();
//
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doHandle(request, response);
//	}
//
//	private void doHandle(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		MemberDAO dao = new MemberDAO();
//		HttpSession session = request.getSession();
//		MemberBean memberBean = new MemberBean();
//		memberBean.setId(id);
//		memberBean.setPwd(pwd);
//
//		boolean result = dao.isExisted(memberBean);
//		if (result) {
//			memberBean = dao.findByUid(id);
//			// 탈퇴 회원 로그인 X
//			if (memberBean.getIsexist().equals("0")) {
//				System.out.println("회원 탈퇴된 아이디 입니다.");
//				response.addHeader("Refresh", "0;login.jsp");
//			} else {
//				session.setAttribute("isLogon", true);
//				session.setAttribute("id", id);
//				session.setAttribute("pwd", pwd);
//				
//				if(id.equals("admin")) {
//					response.sendRedirect("list");
//				} else {
//					response.sendRedirect("main.jsp?id=" + id);
//				}
//				
//
//				
//			} 
//			System.out.println("로그인 가능여부 ==>"+session.getAttribute("isLogon"));
//
//		} else {
//			response.addHeader("Refresh", "0;login.jsp");
//		}
//
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doHandle(request, response);
//	}
//
//}
