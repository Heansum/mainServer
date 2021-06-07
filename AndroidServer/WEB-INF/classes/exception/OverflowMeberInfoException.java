package exception;

// 회원 정보의 길이가 지정한 길이를 초과했을 경우 발생시킬 예외
public class OverflowMeberInfoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7661638729030835550L;

	public OverflowMeberInfoException(String msg) {
		super(msg);
	}
}
