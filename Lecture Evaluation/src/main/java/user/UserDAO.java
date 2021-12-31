package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {
	
	public int login(String userID, String userPassword) {
		String SQL = "select userPassword from user where userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				}
				else {
					return 0; // 비밀번호 틀림
				}
			}
			return -1; // 아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(pstmt !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(rs !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(UserDTO user) {
		String SQL = "insert into user values (?, ?, ?, ?, false)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(pstmt !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(rs !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return -1; // 회원가입 실패
	}
	
	public boolean getUserEmailChecked(String userID) {	// 사용자가 현재 이메일 인증이 되었는지 확인
		String SQL = "select userEmailChecked from user where userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(pstmt !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(rs !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return false; // 데이터베이스 오류
	}
	
	public String getUserEmail(String userID) {
		String SQL = "select userEmail from user where userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(pstmt !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(rs !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return null; // 데이터베이스 오류
	}
	
	
	
	public boolean setUserEmailChecked(String userID) {	// 사용자의 이메일 인증 진행
		String SQL = "update user set userEmailChecked = true where userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(pstmt !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try {if(rs !=  null) conn.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return false; // 데이터베이스 오류
	}
}
