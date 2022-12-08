package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberBean;
import member.MemberDAO;

/**
 * Servlet implementation class ShowMember
 */
@WebServlet("/board")
public class ListBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String id = "";
		boardDAO dao = new boardDAO();
		List<boardVO> boardList = dao.listboard();
		HttpSession session = request.getSession(false);
		request.setAttribute("boardList", boardList);
		RequestDispatcher dispatch = request.getRequestDispatcher("board.jsp");
		dispatch.forward(request, response);
		
		
		
		
		
	}
}
