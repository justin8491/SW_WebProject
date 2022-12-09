package board;

import java.io.IOException;

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
@WebServlet("/boardUpdate")
public class UpdateBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		BoardDAO dao = new BoardDAO();
		String boardNO = request.getParameter("boardNO");
		System.out.println("이게 왜 널이야~~~~~~~" + boardNO);
		
		BoardVO board = dao.findByBNO(boardNO);
		
		
		
//		String category = request.getParameter("category");
//		String title = request.getParameter("title");
//		String id = request.getParameter("id");
//		String content = request.getParameter("content");
//		System.out.println("Update board : " + board.getBoardNO() + "\n"
//				   + category + "\n"
//				   + title + "\n"
//				   + content);
		
		
//		board.setCategory(category);
//		board.setTitle(title);
//		board.setId(id);
//		board.setContent(content);

		//dao.updateBoard(board);
		
		
		System.out.println("Update board : " + board.getBoardNO() + "\n"
				   + board.getCategory() + "\n"
				   + board.getTitle() + "\n"
				   + board.getContent());
		request.setAttribute("board", board);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("board_Update.jsp"); 
		dispatcher.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
