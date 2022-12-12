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
@WebServlet("/Search")
public class SearchBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String selectValue = request.getParameter("selectValue");
		String searchValue = request.getParameter("searchValue");
		BoardDAO dao = new BoardDAO();
		System.out.println("selectValue : " + selectValue);
		System.out.println("searchValue : " + searchValue);
		List<BoardVO> boardList = dao.listBoardValue(selectValue, searchValue);

		request.setAttribute("boardList", boardList);
		RequestDispatcher dispatch = request.getRequestDispatcher("board.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
