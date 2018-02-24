package top.starrysea.params.responsibility;

/*
 * 职责链生成
 */
public class HandlerFactory {
	public static ParamsHandler createHandler() {
		ParamsHandler bResultHandler = new BResultHandler();
		ParamsHandler deviceHandler = new DeviceHandler();
		ParamsHandler httpSessionHandler = new HttpSessionHandler();
		ParamsHandler conditionHandler = new ConditionHandler();
		ParamsHandler servletRequestHandler = new ServletRequestHandler();
		ParamsHandler servletResponseHandler = new ServletResponseHandler();
		ParamsHandler mutipartFileHandler = new MutiPartFileHandler();
		ParamsHandler mutipartFileArrayHandler = new MutiPartFileArrayHandler();
		ParamsHandler defaultHandler = new DefaultHandler();
		bResultHandler.setNextHandler(deviceHandler);
		deviceHandler.setNextHandler(httpSessionHandler);
		httpSessionHandler.setNextHandler(conditionHandler);
		conditionHandler.setNextHandler(servletRequestHandler);
		servletRequestHandler.setNextHandler(servletResponseHandler);
		servletResponseHandler.setNextHandler(mutipartFileHandler);
		mutipartFileArrayHandler.setNextHandler(defaultHandler);
		return bResultHandler;
	}
}
