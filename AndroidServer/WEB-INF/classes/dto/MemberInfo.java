package dto;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import exception.EmptyMemberInfoException;
import exception.OverflowMeberInfoException;

public class MemberInfo {
	private String id;
	private String pw;
	private String joinDate;
	
	public MemberInfo(HttpServletRequest request) throws EmptyMemberInfoException, OverflowMeberInfoException {
		this.id = request.getParameter("id");
		this.pw = request.getParameter("pw");
		
		if(id == null || pw == null) {
			// 아이디 또는 비밀번호가 전달되지 않았다면은
			throw new EmptyMemberInfoException("아이디 또는 비밀번호가 전달되지 않았습니다.");
		} else if(id.length() > 20 || pw.length() > 16) {
			// 아이디가 20자를 초과했거나 비밀번호가 16자를 초과했다면
			throw new OverflowMeberInfoException("아이디 또는 비밀번호가 지정한 길이를 초과했습니다.");
		} else if(id.trim().length() == 0 || pw.trim().length() == 0) {
			// 아이디 또는 비밀번호가 공백으로만 전달됬다면
			throw new EmptyMemberInfoException("아이디 또는 비밀번호가 전달되지 않았습니다.");
		}
	}
	
	public MemberInfo(HttpServletRequest request, LocalDateTime joinDate) throws EmptyMemberInfoException, OverflowMeberInfoException {
		this(request);
		
		this.joinDate = joinDate.toString();
	}
	
	public MemberInfo(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

}
