package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberInfo;
import dto.MemberUpdateInfo;
import exception.NotFoundMemberInfoException;
import util.DBMng;

public class MemberService {
	public boolean login(MemberInfo memberLoginInfo) throws SQLException {
		boolean isLogin = false;
		
		// DB 커넥션 연결
		Connection conn = DBMng.getConnection();
		
		// id, pw를 사용해서 SELECT
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE id = ? AND pw = ?");
		pstmt.setString(1, memberLoginInfo.getId());
		pstmt.setString(2, memberLoginInfo.getPw());
		
		ResultSet rs = pstmt.executeQuery();
		
		isLogin = rs.next();
		
		DBMng.closeConnection();
		
		return isLogin;
	}
	
	public boolean join(MemberInfo memberJoinInfo) throws SQLException {
		boolean isJoin = false;
		
		Connection conn = DBMng.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user(id, pw, joinDate) VALUES(?, ?, ?)");
		pstmt.setString(1, memberJoinInfo.getId());
		pstmt.setString(2, memberJoinInfo.getPw());
		pstmt.setString(3, memberJoinInfo.getJoinDate());
		
		int insertResult = pstmt.executeUpdate();
		
		isJoin = insertResult == 1;
		
		DBMng.closeConnection();
		
		return isJoin;
	}
	
	public boolean update(MemberUpdateInfo memberUpdateInfo) throws SQLException, NotFoundMemberInfoException {
		boolean isUpdate = false;
		
		Connection conn = DBMng.getConnection();
		
		// 회원 정보를 수정하기 전 수정하려는 회원의 정보가 존재하는지 여부를 체크하고
		// 회원 정보를 수정할 때 사용할 idx값을 가져오는 부분
		int updateIdx = selectByIdPw(memberUpdateInfo.getId(), memberUpdateInfo.getOldPW());
		
		if(updateIdx > -1) {
			// 수정하려는 회원 정보가 존재한다면
			PreparedStatement updatePstmt = conn.prepareStatement("UPDATE user SET pw = ? WHERE idx = ?");
			updatePstmt.setString(1, memberUpdateInfo.getNewPW());
			updatePstmt.setInt(2, updateIdx);
			
			int updateResult = updatePstmt.executeUpdate();
			isUpdate = updateResult == 1;
		} else {
			// 수정하려는 회원 정보가 존재하지 않는다면은
			throw new NotFoundMemberInfoException("회원 정보가 없습니다.");
		}
		// 회원 정보를 수정하기 전 수정하려는 회원의 정보가 존재하는지 여부를 체크하고
		// 회원 정보를 수정할 때 사용할 idx값을 가져오는 부분
		
		DBMng.closeConnection();
		
		return isUpdate;
	}
	
	public boolean delete(MemberInfo memberDeleteInfo) throws SQLException, NotFoundMemberInfoException {
		boolean isDelete = false;
		
		Connection conn = DBMng.getConnection();
		
		// 회원 탈퇴를 하기 전 탈퇴를하려는 회원의 정보가 존재하는지 여부를 체크하고
		// 회원 탈퇴를할 때 사용할 idx값을 가져오는 부분
		int deleteIdx = selectByIdPw(memberDeleteInfo.getId(), memberDeleteInfo.getPw());
		if(deleteIdx > -1) {
			// 회원 탈퇴를 할 사용자의 정보가 존재한다면
			
			PreparedStatement deletePstmt = conn.prepareStatement("DELETE FROM user WHERE idx = ?");
			deletePstmt.setInt(1, deleteIdx);
			
			int deleteResult = deletePstmt.executeUpdate();
			
			isDelete = deleteResult == 1;
		} else {
			// 탈퇴하려는 회원 정보가 존재하지 않는다면은
			throw new NotFoundMemberInfoException("회원 정보가 없습니다.");
		}
		
		DBMng.closeConnection();
		
		return isDelete;
	}
	
	public int selectByIdPw(String id, String pw) {
		// 일치하는 회원 정보가 없다 라고 가정을 하고 시작하기 때문에 -1을 저장
		int idx = -1;
		
		try {
			Connection conn = DBMng.getConnection();
			
			PreparedStatement selectPstmt = conn.prepareStatement("SELECT idx FROM user WHERE id = ? AND pw = ?");
			selectPstmt.setString(1, id);
			selectPstmt.setString(2, pw);
			
			ResultSet rs = selectPstmt.executeQuery();
			if(rs.next()) {
				// id, pw가 일치하는 회원정보가 있다면
				
				idx = rs.getInt("idx");
			}
			
			DBMng.closeConnection();
		} catch(SQLException e) {
			
		}
		
		return idx;
	}
}










