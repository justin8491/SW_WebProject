package board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardAction {
	// 게시판 목록
	public String boardList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String text = request.getParameter("text");
			String pageNoStr = request.getParameter("pageNo");
			BoardDAO boardDAO = new BoardDAO();
			if ("".equals(pageNoStr) || null == pageNoStr)
				pageNoStr = "1";
			int pageNo = Integer.parseInt(pageNoStr);
			int pageSize = 10;
			int totalPageNo = boardDAO.totalPageNo(text);
			int startPageNo = ((pageNo - 1) / pageSize) * pageSize + 1;
			int endPageNo = startPageNo + pageSize - 1;

			if (endPageNo > totalPageNo)
				endPageNo = totalPageNo;

			List<BoardVO> boardList = boardDAO.listboard(text, pageNo);

			request.setAttribute("boardList", boardList);
			request.setAttribute("totalPageNo", totalPageNo);
			request.setAttribute("startPageNo", startPageNo);
			request.setAttribute("endPageNo", endPageNo);
			request.setAttribute("currentPageNo", pageNo);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "/jsp/board/board.jsp";

	}

	// 게시판 상세
	public String boardDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String boardNO = request.getParameter("boardNO");
		Boolean isLogon = false;
		HttpSession session = request.getSession(false);

		if (session != null) {
			isLogon = (Boolean) session.getAttribute("isLogon");
			if (isLogon == true) {
				try {

					BoardDAO dao = new BoardDAO();

					dao.viewBoard(boardNO);

					BoardVO board = dao.findByBNO(boardNO);

					board = dao.findByBNO(boardNO);
					request.setAttribute("board", board);

					return "/jsp/board/board_Detail.jsp?boardNO=" + boardNO;
				} catch (Exception e) {
				}

			} else {
				return "/jsp/member/login.jsp";
			}
		} else {
			return "/jsp/member/login.jsp";
		}
		return "/jsp/board/board_Detail.jsp?boardNO=" + boardNO;
	}

	// 게시물 삭제
	public String boardDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession(false);
			String boardNO = request.getParameter("boardNO");
			BoardDAO dao = new BoardDAO();
			String text = request.getParameter("text");
			String pageNoStr = request.getParameter("pageNo");
			int pageNo = Integer.parseInt(pageNoStr);
			if (session != null) { // 세션 값 있을 때

				try {
					dao.deleteBoard(boardNO, "0");

					List<BoardVO> boardList = dao.listboard(text, pageNo);
					request.setAttribute("boardList", boardList);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "/jsp/board/board.jsp";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/jsp/board/board.jsp";
	}

	// 게시판 수정클릭시 정보값 가지고 수정페이지 이동
	public String boardUpdateForm(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");

			HttpSession session = request.getSession(false);

			if (session != null) {
				BoardDAO dao = new BoardDAO();
				String boardNO = request.getParameter("boardNO");

				BoardVO board = dao.findByBNO(boardNO);

				request.setAttribute("board", board);
				return "/jsp/board/board_Update.jsp?boardNO=" + boardNO;
			} else {
				return "/jsp/member/login.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// 값 가져와서 DB에 값 업데이트
	public String boardUpdateInsert(HttpServletRequest request, HttpServletResponse response) {

		BoardDAO dao = new BoardDAO();
		HttpSession session = request.getSession(false);
		String boardNO = request.getParameter("boardNO");
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String id = (String) session.getAttribute("id");
		String content = request.getParameter("content");

		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");

			System.out.println(boardNO);
			BoardVO board = dao.findByBNO(boardNO);
			if (session != null) {
				board.setCategory(category);
				board.setTitle(title);
				board.setId(id);
				board.setContent(content);

				dao.updateBoard(board);

				request.setAttribute("board", board);
				return "/jsp/board/board_Detail.jsp?boardNO=" + boardNO;
			}

		} catch (Exception e) {
		}
		return "/jsp/board/board_Detail.jsp?boardNO=" + boardNO;
	}

	public String boardInsertForm(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/board/board_Insert.jsp";
	}

	// 게시판 생성
	public JSONObject boardInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession(false);
			String category = request.getParameter("category");

			String title = request.getParameter("title");

			String id = (String) session.getAttribute("id");
			String content = request.getParameter("content");

			// 저장소 객체 생성
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 업로드 파일 임시로 저장할 경로 설정
			factory.setRepository(new File("c:\\upload"));

			// 파일 업로드 객체에 저장소 설정
			ServletFileUpload upload = new ServletFileUpload(factory);

			System.out.println(request.getRemoteAddr());

			try {

				PrintWriter out = response.getWriter();
				Map<String, List<FileItem>> mapItems = upload.parseParameterMap(request);

				BoardFileDAO boardFileDAO = new BoardFileDAO();
				JSONObject jsonResult = new JSONObject();

				try {
					BoardDAO dao = new BoardDAO();
					category = new String(mapItems.get("category").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");
					title = new String(mapItems.get("title").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");
					id = (String) session.getAttribute("id");
					content = new String(mapItems.get("content").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");
					System.out.println("category : " + category);
					System.out.println("title : " + title);
					System.out.println("id : " + id);
					System.out.println("content : " + content);
					BoardVO board = new BoardVO(category, title, content, id);

					int boardNO = dao.insertBoard(board);//
					System.out.println("boardNO : " + boardNO);

					// 첨부파일 정보 얻어 저장
					for (FileItem fileItem : mapItems.get("filename1")) {
						if (fileItem.getSize() == 0)
							continue;

						String real_name = "c:\\upload\\" + System.nanoTime();
						fileItem.write(new File(real_name));

						FileVO boardFile = new FileVO(0, boardNO, fileItem.getName(), real_name,
								fileItem.getContentType(), fileItem.getSize());
						boardFileDAO.insertBoardFile(boardFile);
					}
					boardFileDAO.close();

					jsonResult.put("status", true);
					jsonResult.put("message", "글쓰기를 성공했습니다.");
					jsonResult.put("url", "/WebProject/board/boardList.do");

				} catch (SQLException e) {
					jsonResult.put("status", false);
					jsonResult.put("message", "작성 중에 오류가 발생했습니다.");
					e.printStackTrace();
				}

				out.println(jsonResult.toString());

				return jsonResult;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("에러 내용 : " + e.getMessage());

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	public String boardReplyForm(HttpServletRequest request, HttpServletResponse response) {
		String boardNO = request.getParameter("boardNO");

		BoardDAO dao = new BoardDAO();
		BoardVO board = dao.findByBNO(boardNO);
		request.setAttribute("board", board);

		return "/jsp/board/board_Reply.jsp";

	}

	public JSONObject boardReply(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession(false);
			String category = request.getParameter("category");

			String title = request.getParameter("title");

			String id = (String) session.getAttribute("id");
			String content = request.getParameter("content");

			// 저장소 객체 생성
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 업로드 파일 임시로 저장할 경로 설정
			factory.setRepository(new File("c:\\upload"));

			// 파일 업로드 객체에 저장소 설정
			ServletFileUpload upload = new ServletFileUpload(factory);

			System.out.println(request.getRemoteAddr());

			try {

				PrintWriter out = response.getWriter();
				Map<String, List<FileItem>> mapItems = upload.parseParameterMap(request);

				// BoardFileDAO boardFileDAO = new BoardFileDAO();
				JSONObject jsonResult = new JSONObject();

				try {
					BoardDAO dao = new BoardDAO();
					String parentNo = new String(mapItems.get("parentNo").get(0).getString().getBytes("ISO-8859-1"),
							"UTF-8");
					category = new String(mapItems.get("category").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");
					title = new String(mapItems.get("title").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");
					id = (String) session.getAttribute("id");
					content = new String(mapItems.get("content").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");
					System.out.println("parentNo : " + parentNo);
					System.out.println("category : " + category);
					System.out.println("title : " + title);
					System.out.println("id : " + id);
					System.out.println("content : " + content);
					BoardVO board = new BoardVO(parentNo, category, title, content, id);

					int boardNO = dao.insertBoardReply(board);

					// 첨부파일 정보 얻어 저장
//					for (FileItem fileItem : mapItems.get("filename1")) {
//						if (fileItem.getSize() == 0)
//							continue;
//
//						String real_name = "c:\\upload\\" + System.nanoTime();
//						fileItem.write(new File(real_name));
//
//						FileVO boardFile = new FileVO(0, boardNO, fileItem.getName(), real_name,
//								fileItem.getContentType(), fileItem.getSize());
//						boardFileDAO.insertBoardFile(boardFile);
//					}
//					boardFileDAO.close();

					jsonResult.put("status", true);
					jsonResult.put("message", "글쓰기를 성공했습니다.");
					jsonResult.put("url", "/board/boardList.do");

				} catch (SQLException e) {
					jsonResult.put("status", false);
					jsonResult.put("message", "작성 중에 오류가 발생했습니다.");
					e.printStackTrace();
				}

				out.println(jsonResult.toString());

				return jsonResult;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

}
