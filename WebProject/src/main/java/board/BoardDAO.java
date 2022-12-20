package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;

	public BoardDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int totalPageNo(String text) throws SQLException {
		int totalPageSize = 0;
		final int rowSize = 10;
		try {
			conn = dataFactory.getConnection();
			String query = "select ceil(COUNT(*) / ? ) from t_board where title like concat('%',?, '%') or content like concat('%', ?, '%')or Id like concat('%', ?, '%') ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rowSize);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setString(4, text);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				totalPageSize = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.commit();
			return totalPageSize;
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.close();
		return totalPageSize;
	}

	// 게시판 목록
	public List<BoardVO> listboard(String text, int pageNo) {
		List<BoardVO> list = new ArrayList<>();
		final int rowSize = 10;
		try {
			System.out.println("검색어 : " + text);
			text = text == null ? "" : text;
			// connDB();
			System.out.println("페이지 num : " + pageNo);
			String query = "select * from t_board where title like concat('%', ?, '%') or content like concat('%', ?, '%')";
			query += " or Id like concat('%', ?, '%') AND isExist != '0' ORDER BY parentNo, `boardNO` LIMIT ?, ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setInt(4, (pageNo - 1) * rowSize);
			pstmt.setInt(5, rowSize);

			System.out.println("prepareStatememt: " + query);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO(rs.getInt("boardNO"), rs.getString("parentNo"), rs.getString("category"),
						rs.getString("title"), rs.getString("content"), rs.getString("id"), rs.getDate("writeDate"),
						rs.getInt("view"), rs.getInt("like_count"), rs.getInt("dis_like_count"),
						rs.getString("isExist"));
				System.out.println(board);
				list.add(board);

			}
			rs.close();
			pstmt.close();
			conn.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시판 삭제
	public void deleteBoard(String boardNO, String value) {
		try {

			String query = "update t_board set isexist=? ";
			query += " where boardNO=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, value);
			pstmt.setString(2, boardNO);
			System.out.println("boardNO : " + boardNO);
			System.out.println("value : " + value);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시판 생성
	public int insertBoard(BoardVO board) throws SQLException {
		try {
			// connDB();
			String query = "insert into t_board (category, title, id, content) values (?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getId());
			pstmt.setString(4, board.getContent());
			pstmt.executeUpdate();

			pstmt.close();

			query = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			int boardNO = 0;
			if (rs.next()) {
				boardNO = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
			return boardNO;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	// 게시판 기본키값 기준으로 데이터 가져오기
	public BoardVO findByBNO(String boardNO) {
		try {
			String query = "select * from t_board";
			query += " where boardNO=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// 멤버 존재여부 확인
			pstmt.setString(1, boardNO);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNO(rs.getInt("BOARDNO"));
				board.setCategory(rs.getString("CATEGORY"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setId(rs.getString("ID"));
				board.setView(rs.getInt("VIEW"));
				board.setIsExist(rs.getString("ISEXIST"));
				board.setWriteDate(rs.getDate("WRITEDATE"));
				rs.close();
				pstmt.close();
				conn.close();
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------- 실패 사유 : " + e.getMessage());
		}
		return null;
	}

	// 게시판 업데이트
	public void updateBoard(BoardVO board) {
		try {
			String query = "update t_board set category=?, title=?, id=?, content=?";
			query += " where boardNO=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// 멤버 정보 설정

			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getId());
			pstmt.setString(4, board.getContent());
			pstmt.setInt(5, board.getBoardNO());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 게시판 검색
//	public List<BoardVO> listBoardValue(String selectValue, String searchValue) {
//		List<BoardVO> list = new ArrayList<>();
//		try {
//			String query = "select * from t_board ";
//			query += "WHERE isexist != '0' " + "AND " + selectValue + " LIKE '%" + searchValue + "%' "
//					+ "AND  ORDER BY parentNo, `boardNO` LIMIT ?, ?";
//			System.out.println("prepareStatememt: " + query);
//			pstmt = conn.prepareStatement(query);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				BoardVO board = new BoardVO(rs.getInt("boardNO"), rs.getString("parentNo"), rs.getString("category"),
//						rs.getString("title"), rs.getString("content"), rs.getString("id"), rs.getDate("writeDate"),
//						rs.getInt("view"), rs.getInt("like_count"), rs.getInt("dis_like_count"),
//						rs.getString("isExist"));
//				System.out.println(board);
//				list.add(board);
//			}
//			rs.close();
//			pstmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	public void viewBoard(String BoardNO) {
		try {
			String query = "update t_board set view = view + 1";
			query += " where boardNO=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// 멤버 정보 설정
			pstmt.setString(1, BoardNO);
			pstmt.executeUpdate();
			pstmt.close();
			// 자동 커밋 세팅
			conn.commit();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() throws Exception {
		if (conn != null) {
			System.out.println("이걸 사용한 곳이 있나?");
			conn.close();
		}
	}

}
