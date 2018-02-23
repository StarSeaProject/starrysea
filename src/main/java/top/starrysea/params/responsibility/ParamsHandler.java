package top.starrysea.params.responsibility;

import top.starrysea.params.strategy.ChangeToString;

/*
 * 抽象职责者
 */
public abstract class ParamsHandler {
	protected ParamsHandler nextHandler;
	protected ChangeToString changeToString;

	public void setNextHandler(ParamsHandler nextHander) {
		this.nextHandler = nextHander;
	}

	public abstract String handleRequest(Object object);
}
