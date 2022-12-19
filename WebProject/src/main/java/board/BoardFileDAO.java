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
	public List<FileVO> list(String boardNO) {
		List<FileVO> list = new ArrayList<>();
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "select * from t_file ";
			query += "WHERE 'boardNO' = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardNO);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				FileVO boardFile = new FileVO(rs.getInt("f_id"), rs.getInt("boardNO"), rs.getString("org_name"),
						rs.getString("real_name"), rs.getString("content_type"), rs.getLong("length"));
				System.out.println(boardFile);
				list.add(boardFile);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시물 등록
	public void insertBoardFile(FileVO boardFile) throws SQLException {
		
		conn = dataFactory.getConnection();
		String query = "insert into t_file (boardNO, org_name, real_name, content_type, length) VALUES(?,?,?,?,?)";
		System.out.println("prepareStatememt: " + query);
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, boardFile.getBoardNO());
		pstmt.setString(2, boardFile.getOrg_name());
		pstmt.setString(3, boardFile.getReal_name());
		pstmt.setString(4, boardFile.getContent_type());
		pstmt.setLong(5, boardFile.getLength());
		pstmt.executeUpdate();

		pstmt.close();
		conn.commit();
	}

	public void close() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}


}
