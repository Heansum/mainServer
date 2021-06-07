package dto;

import javax.servlet.http.HttpServletRequest;

import exception.EmptyMemberInfoException;
import exception.OverflowMeberInfoException;

// 회원 정보 수정 시 사용하는 DTO
public class MemberUpdateInfo {
	private String id; // 회원 정보를 변경하려는 사용자의 ID
	private String oldPW; // 회원 정보를 변경하려는 사용자의 현재 PW
	private String newPW; // 회원 정보를 변경하려는 사용자의 새로운 PW

	public MemberUpdateInfo(HttpServletRequest request) throws EmptyMemberInfoException, OverflowMeberInfoException {
		this.id = request.getParameter("id");
		this.oldPW = request.getParameter("oldPW");
		this.newPW = request.getParameter("newPW");

		if (id == null || id.trim().length() == 0) {
			throw new EmptyMemberInfoException("id가 전달되지 않음");
		}

		if (oldPW == null || newPW == null) {
			throw new EmptyMemberInfoException("oldPW 또는 newPW가 전달되지 않음");
		} else if (oldPW.trim().length() == 0 || newPW.trim().length() == 0) {
			throw new EmptyMemberInfoException("oldPW 또는 newPW가 전달되지 않음");
		} else if (oldPW.length() > 16 || newPW.length() > 16) {
			throw new OverflowMeberInfoException("oldPW 또는 newPW 16자 초과");
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldPW() {
		return oldPW;
	}

	public void setOldPW(String oldPW) {
		this.oldPW = oldPW;
	}

	public String getNewPW() {
		return newPW;
	}

	public void setNewPW(String newPW) {
		this.newPW = newPW;
	}

}
