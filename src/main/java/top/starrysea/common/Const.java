package top.starrysea.common;

public class Const {

	private Const() {
	}

	public static final String ADMIN_SESSION_KEY = "adminId";
	public static final String ERRINFO = "errInfo";
	public static final String ERROR_VIEW = "error";
	public static final String LOGIN_VIEW = "admin_login";
	public static final String SUCCESS_VIEW = "success";
	public static final String BOSS = "boss";
	public static final String MOBILE = "mobile/";
	public static final double FUNDING_FACTOR = 0.07;
	public static final String INFO = "info";
	public static final String QUESTION = "question/";
	public static final String TOKEN = "token";
	public static final String SHOPPINGCAR = "shoppingCar";
	public static final String NOT_FOUND_VIEW = "not_found";
	public static final String CUCUIMG = "cucuImg";

	public static class HttpCode {
		private HttpCode() {
		}

		public static final int BAD_REQUEST = 400;
		public static final int NOT_FOUND = 404;
		public static final int SERVER_ERROR = 500;
	}
}
