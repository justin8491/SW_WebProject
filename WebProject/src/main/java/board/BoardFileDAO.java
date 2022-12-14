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

public class BoardFileDAO {
	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;

	public BoardFileDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시판 목록
	public List<BoardVO> listboard() {
		List<BoardVO> list = new ArrayList<>();
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "select * from t_board ";
			query += "WHERE isexist != '0' order by writeDate DESC";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO(rs.getInt("boardNO"), rs.getString("category"), rs.getString("title"),
						rs.getString("content"), rs.getString("id"), rs.getInt("view"), rs.getDate("writeDate"),
						rs.getString("isExist"));
				System.out.println(board);
				list.add(board);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시판 목록
	public List<FileVO> list(int boardNO) {
		List<FileVO> list = new ArrayList<>();
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "select * from t_file ";
			query += "WHERE 'BoardNO' = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt.setInt(1, boardNO);

			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FileVO file = new FileVO(rs.getInt("f_id"), rs.getInt("boardNO"), rs.getString("org_name"),
						rs.getString("real_name"), rs.getString("content_type"), rs.getInt("length"));
				System.out.println(file);
				list.add(file);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시판 삭제
	public void deleteBoard(String boardNO, String value) {
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(true);
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
			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시판 생성
	public void insertBoard(FileVO file) throws SQLException {
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "insert into t_file (boardNO, org_name, real_name, content_type, length) values (?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, file.getBoardNO());
			pstmt.setString(2, file.getOrg_name());
			pstmt.setString(3, file.getReal_name());
			pstmt.setString(4, file.getContent_type());
			pstmt.setLong(5, file.getLength());
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

			// 마리아 DB 자동 증가값 = LAST_INSERT_ID();
			conn = dataFactory.getConnection();
			query = "SELECT LAST_INSERT_ID()";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			int boardNO = 0;
			if (rs.next()) {
				boardNO = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
			//return boardNO;

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 게시판 기본키값 기준으로 데이터 가져오기
	public BoardVO findByBNO(String boardNO) {
		try {
			conn = dataFactory.getConnection();
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
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------- 실패 사유 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
			}
		}
		return null;
	}

	// 게시판 업데이트
	public void updateBoard(BoardVO board) {
		try {
			conn = dataFactory.getConnection();
			// 자동 커밋 함수
			conn.setAutoCommit(true);
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
			// 자동 커밋 세팅
			conn.commit();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 게시판 검색
	public List<BoardVO> listBoardValue(String selectValue, String searchValue) {
		List<BoardVO> list = new ArrayList<>();
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "select * from t_board ";
			query += "WHERE isexist != '0' " + "AND " + selectValue + " LIKE '%" + searchValue + "%' "
					+ "order by writeDate DESC";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO(rs.getInt("boardNO"), rs.getString("category"), rs.getString("title"),
						rs.getString("content"), rs.getString("id"), rs.getInt("view"), rs.getDate("writeDate"),
						rs.getString("isExist"));
				System.out.println(board);
				list.add(board);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void viewBoard(String BoardNO) {
		try {
			conn = dataFactory.getConnection();
			// 자동 커밋 함수
			conn.setAutoCommit(true);
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

}
