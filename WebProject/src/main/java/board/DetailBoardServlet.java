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

/**
 * Servlet implementation class ShowMember
 */
@WebServlet("/boardDetail")
public class DetailBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String boardNO = request.getParameter("boardNO");
		Boolean isLogon = false;
		HttpSession session = request.getSession(false);

		if (session != null) {
			isLogon = (Boolean) session.getAttribute("isLogon");
			if (isLogon == true) {
				
				BoardDAO dao = new BoardDAO();
				
				dao.viewBoard(boardNO);
				BoardVO board = dao.findByBNO(boardNO);

				board = dao.findByBNO(boardNO);
				request.setAttribute("board", board);
				
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("board_Detail.jsp"); 
				dispatcher.forward(request, response);
				
			} else {
				response.sendRedirect("login.jsp");
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}
}
