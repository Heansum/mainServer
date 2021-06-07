package exception;

// 회원 정보를 찾지 못했을 때 발생시킬 예외
public class NotFoundMemberInfoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2430347396350637903L;

	public NotFoundMemberInfoException(String msg) {
		super(msg);
	}
}
