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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//게시판 목록
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
						rs.getString("content"), rs.getString("id"), rs.getInt("view"), rs.getDate("writeDate"), rs.getString("isExist"));
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
	
	//게시판 삭제
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
	
	//게시판 생성
	public void insertBoard(BoardVO board) throws SQLException {
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "insert into t_board (category, title, id, content) values (?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getId());
			pstmt.setString(4, board.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e; // 해당 함수를 호출한 부분으로 예외를 던진다
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	//게시판 기본키값 기준으로 데이터 가져오기
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
	
	//게시판 업데이트
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
	
	//게시판 검색
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
				BoardVO board = new BoardVO(rs.getInt("boardNO"), 
						rs.getString("category"), 
						rs.getString("title"),
						rs.getString("content"), 
						rs.getString("id"), 
						rs.getInt("view"), 
						rs.getDate("writeDate"), 
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
