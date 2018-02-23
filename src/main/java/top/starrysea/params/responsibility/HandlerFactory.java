package top.starrysea.params.responsibility;

/*
 * 职责链生成
 */
public class HandlerFactory {
	public static ParamsHandler createHandler() {
		ParamsHandler viewInHandler = new ViewInHandler();
		ParamsHandler CommonHandler = new CommonHandler();
		ParamsHandler defaultHandler = new DefaultHandler();
		viewInHandler.setNextHandler(CommonHandler);
		CommonHandler.setNextHandler(defaultHandler);
		return viewInHandler;
	}
}
