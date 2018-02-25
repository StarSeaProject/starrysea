package top.starrysea.params.responsibility;

/*
 * 抽象职责者
 */
public abstract class ParamsHandler {
	protected ParamsHandler nextHandler;

	public void setNextHandler(ParamsHandler nextHander) {
		this.nextHandler = nextHander;
	}

	public abstract String handleRequest(Object object);
}
