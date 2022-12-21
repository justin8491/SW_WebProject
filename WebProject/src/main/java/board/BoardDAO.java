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
	ResultSet rs;
	private static DataSource dataFactory;

	private void open() {
		try {
			conn = dataFactory.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int totalPageNo(String text) throws SQLException {
		int totalPageSize = 0;
		final int rowSize = 10;
		open();
		if(text==null)
			text="";
		try {
			String query = "select ceil(COUNT(*) / ? ) from t_board where title like concat('%',?, '%') or content like concat('%', ?, '%')or Id like concat('%', ?, '%') ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rowSize);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setString(4, text);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalPageSize = rs.getInt(1);
			}
			return totalPageSize;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return totalPageSize;
	}

	// 게시판 목록
	public List<BoardVO> listboard(String text, int pageNo) {
		List<BoardVO> list = new ArrayList<>();
		final int rowSize = 10;
		open();
		try {
			text = text == null ? "" : text;
			String query = "select * from t_board where title like concat('%', ?, '%') or content like concat('%', ?, '%')";
			query += " or Id like concat('%', ?, '%') AND isExist != '0' ORDER BY parentNo, `boardNO` LIMIT ?, ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setInt(4, (pageNo - 1) * rowSize);
			pstmt.setInt(5, rowSize);

			System.out.println("prepareStatememt: " + query);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO(rs.getInt("boardNO"), rs.getString("parentNo"), rs.getString("category"),
						rs.getString("title"), rs.getString("content"), rs.getString("id"), rs.getDate("writeDate"),
						rs.getInt("view"), rs.getInt("like_count"), rs.getInt("dis_like_count"),
						rs.getString("isExist"));
				System.out.println(board);
				list.add(board);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
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
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 게시판 생성
	public int insertBoard(BoardVO board) throws SQLException {
		try {
			open();
			String query = "insert into t_board (category, title, id, content) values (?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getId());
			pstmt.setString(4, board.getContent());
			pstmt.executeUpdate();

			query = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			int boardNO = 0;
			if (rs.next()) {
				boardNO = rs.getInt(1);
			}
			return boardNO;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;

	}
	
	// 게시판 생성
	public int insertBoardReply(BoardVO board) throws SQLException {
		try {
			open();
			String query = "insert into t_board (parentNo, category, title, id, content) values (?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getParentNo());
			pstmt.setString(2, board.getCategory());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getId());
			pstmt.setString(5, board.getContent());
			pstmt.executeUpdate();
			
			query = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			int boardNO = 0;
			if (rs.next()) {
				boardNO = rs.getInt(1);
			}
			return boardNO;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
		
	}

	// 게시판 기본키값 기준으로 데이터 가져오기
	public BoardVO findByBNO(String boardNO) {
		try {
			open();
			String query = "select * from t_board";
			query += " where boardNO=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// 멤버 존재여부 확인
			pstmt.setString(1, boardNO);
			rs = pstmt.executeQuery();
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
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------- 실패 사유 : " + e.getMessage());
		} finally {
			close();
		}
		return null;
	}

	// 게시판 업데이트
	public void updateBoard(BoardVO board) {
		try {
			open();
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
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
			open();
			String query = "update t_board set view = view + 1";
			query += " where boardNO=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// 멤버 정보 설정
			pstmt.setString(1, BoardNO);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public static void setDataFactory(DataSource dataSource) {
		dataFactory = dataSource;
	}

}
