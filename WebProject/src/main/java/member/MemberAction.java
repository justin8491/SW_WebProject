package member;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberAction {

	public String mainForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/main.jsp";

	}

	public String loginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "/jsp/member/login.jsp";
	}

	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberDAO dao = new MemberDAO();
		HttpSession session = request.getSession();
		MemberBean memberBean = new MemberBean();
		memberBean.setId(id);
		memberBean.setPwd(pwd);

		boolean result = dao.isExisted(memberBean);
		if (result) {
			memberBean = dao.findByUid(id);
			// 탈퇴 회원 로그인 X
			if (memberBean.getIsexist().equals("0")) {
				System.out.println("회원 탈퇴된 아이디 입니다.");
				response.addHeader("Refresh", "0;login.jsp");
			} else {
				session.setAttribute("isLogon", true);
				session.setAttribute("id", id);
				session.setAttribute("pwd", pwd);

			}
			System.out.println("로그인 가능여부 ==>" + session.getAttribute("isLogon"));

		} else {
			return "/jsp/member/login.jsp";
		}
		if (id.equals("admin")) {
			return "/jsp/admin/admin.jsp";
		} else {
			return "/jsp/member/main.jsp?id=" + id;
		}

	}

	public String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		session.setAttribute("isLogon", false);
		session.invalidate();
		System.out.println("로그아웃 완료");

		return "/jsp/member/login.jsp";

	}

//	public JSONObject update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
//			String jsonStr = in.readLine();
//			System.out.println("jsonStr = " + jsonStr);
//			
//			JSONObject jsonMember = new JSONObject(jsonStr);
//			
//			HttpSession session = request.getSession();
//			
//			MemberDAO memberDAO = new MemberDAO();
//			String uid = jsonMember.getString("uid");
//			String pwd = jsonMember.getString("pwd");
//			String name = jsonMember.getString("name2");
//			String sex = jsonMember.getString("sex");
//			String address = jsonMember.getString("address");
//			String phone = jsonMember.getString("phone");
//			String email = jsonMember.getString("email");
//			Member member = new Member(uid, pwd, name, sex, address, phone, email, "", null);
//			int count = memberDAO.updateMember(member);
//			JSONObject jsonResult = new JSONObject();
//			
//			if (count == 1) {
//				jsonResult.put("status", true);
//				jsonResult.put("message", "수정되었습니다");
//				session.setAttribute("session_member", member);
//			} else {
//				jsonResult.put("status", false);
//				jsonResult.put("message", "해당아이디는 현재 DB에 존재하지 않습니다");
//			}
//			
//			return jsonResult;
////			PrintWriter out = response.getWriter();
////			out.println(jsonResult.toString());
//			
//	     } catch (Exception e) {
//	        e.printStackTrace();
//	     }
//		return null;
//	}

	public String registerForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/registerForm.jsp";
//		try {
//			HttpSession session = request.getSession();
//
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/member/updateForm.jsp");
//			dispatcher.forward(request, response);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

//	public JSONObject register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
//			String jsonStr = in.readLine();
//			System.out.println("jsonStr = " + jsonStr);
//			
//			JSONObject jsonMember = new JSONObject(jsonStr);
//			
//			HttpSession session = request.getSession();
//			
//			MemberDAO memberDAO = new MemberDAO();
//			String uid = jsonMember.getString("uid");
//			String pwd = jsonMember.getString("pwd");
//			String name = jsonMember.getString("name2");
//			String sex = jsonMember.getString("sex");
//			String address = jsonMember.getString("address");
//			String phone = jsonMember.getString("phone");
//			String email = jsonMember.getString("email");
//			Member member = new Member(uid, pwd, name, sex, address, phone, email, "", null);
//			int count = memberDAO.updateMember(member);
//			JSONObject jsonResult = new JSONObject();
//			
//			if (count == 1) {
//				jsonResult.put("status", true);
//				jsonResult.put("message", "수정되었습니다");
//				session.setAttribute("session_member", member);
//			} else {
//				jsonResult.put("status", false);
//				jsonResult.put("message", "해당아이디는 현재 DB에 존재하지 않습니다");
//			}
//
//			return jsonResult;
////			PrintWriter out = response.getWriter();
////			out.println(jsonResult.toString());
//			
//	     } catch (Exception e) {
//	        e.printStackTrace();
//	     }
//		return null;
//	}	
}
