package board;

import jakarta.servlet.DispatcherType;
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
@WebServlet("/boardInsert")
public class InsertBoardServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertBoardServelt() {
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
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		
		String id = (String) session.getAttribute("id");
		String content = request.getParameter("content");
		
		

		BoardDAO dao = new BoardDAO();
		BoardVO board = new BoardVO(category, title, content, id);
		
		
		
			try {
				dao.insertBoard(board);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("에러 내용 : " + e.getMessage());
				
			}
		
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardList");
		dispatcher.forward(request, response);
		//response.sendRedirect("boardList");

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
