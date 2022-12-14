package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberBean> listMember() {
		List<MemberBean> list = new ArrayList<>();
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "select * from t_member ";
			query += "order by createDate desc";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberBean member = new MemberBean(
						rs.getString("id"),	
						rs.getString("pwd"),	
						rs.getString("name"),
						rs.getString("phone"),
						rs.getString("email"),
						rs.getString("accept"),
						rs.getString("isexist"),
						rs.getDate("createDate"));
				System.out.println(member);
				list.add(member);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean isExisted(MemberBean member) {
		boolean result = false;
		System.out.println(member.getId());
		String userid = member.getId();
		String pwd = member.getPwd();
		try {
			conn = dataFactory.getConnection();
			String query = "select case count(*) when 1 then 'true' else 'false' end as result from t_member";
			query += " where id=? and pwd=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, pwd);
			System.out.println(query);
			System.out.println(userid);
			System.out.println(pwd);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next(); 
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
//	public MemberBean viewMember(String id) {
//		try {
//			// connDB();
//			conn = dataFactory.getConnection();
//			String query = "select * from t_member where id = ?";
//			System.out.println("prepareStatememt: " + query);
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, id);
//			ResultSet rs = pstmt.executeQuery();
//			MemberBean memberBean = null;
//			
//			if (rs.next()) {
//				memberBean = new MemberBean(
//						rs.getString("id"),	
//						rs.getString("pwd"),	
//						rs.getString("name"),
//						rs.getString("phone"),
//						rs.getString("email"),
//						rs.getString("isexist"),
//						rs.getDate("createDate"));
//			}
//			rs.close();
//			
//			return memberBean;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pstmt.close();
//				conn.close();
//			} catch (Exception e) {}
//		}
//		return null;		
//	}

	public void insertMember(MemberBean memberBean) throws SQLException{
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "insert into t_member (id, pwd, name, phone, email) values (?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getPwd());
			pstmt.setString(3, memberBean.getName());
			pstmt.setString(4, memberBean.getPhone());
			pstmt.setString(5, memberBean.getEmail());
			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e; //?????? ????????? ????????? ???????????? ????????? ????????? 
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {}
		}		
	}
	
	public MemberBean findByUid(String id){
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member";
			query += " where id=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// ?????? ???????????? ??????
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				MemberBean memberBean = new MemberBean();
				memberBean.setId(rs.getString("ID"));
				memberBean.setPwd(rs.getString("PWD"));
				memberBean.setName(rs.getString("NAME"));
				memberBean.setPhone(rs.getString("PHONE"));
				memberBean.setEmail(rs.getString("EMAIL"));
				memberBean.setIsexist(rs.getString("ISEXIST"));
				memberBean.setCreateDate(rs.getDate("CREATEDATE"));
				rs.close();
				return memberBean;
			} else {
				System.out.println("[" + id + "] ????????? ???????????? ????????????"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------- ?????? ?????? : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {}
		}
		return null;
	}
	
	public MemberBean findId(MemberBean memberBean){
		try {
			conn = dataFactory.getConnection();
			String query = "select id from t_member";
			query += " where name=? and email=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// ?????? ???????????? ??????
			pstmt.setString(1, memberBean.getName());
			pstmt.setString(2, memberBean.getEmail());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				
				memberBean.setId(rs.getString("ID"));
				rs.close();
				return memberBean;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------- ?????? ?????? : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {}
		}
		return null;
	}
	public MemberBean findPwd(MemberBean memberBean){
		try {
			conn = dataFactory.getConnection();
			String query = "select pwd from t_member";
			query += " where id=? and name=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// ?????? ???????????? ??????
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getName());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				
				memberBean.setPwd(rs.getString("PWD"));
				rs.close();
				return memberBean;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------- ?????? ?????? : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {}
		}
		return null;
	}

	public void deleteMamber(String id, String value) {
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(true);
			String query = "update t_member set isexist=? ";
			query += " where id=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, value);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateMember(MemberBean member) {
		try {
			conn = dataFactory.getConnection();
			//?????? ?????? ??????
			conn.setAutoCommit(true);
			String query = "update t_member set pwd=?, name=?, phone=?, email=?";
			query += " where id=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			// ?????? ?????? ??????

			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getId());

			pstmt.executeUpdate();
			pstmt.close();
			//?????? ?????? ??????
			conn.commit();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
