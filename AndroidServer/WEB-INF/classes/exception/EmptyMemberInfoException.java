package exception;

// 회원 정보가 전달되지 않았을 때 발생시킬 예외
public class EmptyMemberInfoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5493597953839385701L;
	
	public EmptyMemberInfoException(String msg) {
		super(msg);
	}
}
