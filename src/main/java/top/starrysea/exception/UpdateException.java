package top.starrysea.exception;

public class UpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5659256177586217063L;

	public UpdateException(String message) {
		super(message);
	}
	
	public UpdateException(Throwable cause) {
		super(cause);
	}
}
