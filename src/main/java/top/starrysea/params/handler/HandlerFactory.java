package top.starrysea.params.handler;

import static top.starrysea.params.handler.ParamsHandlers.*;

import java.util.function.Function;

/*
 * 职责链生成
 */
public class HandlerFactory {
	private HandlerFactory() {
	}

	public static Function<Object, Object> createHandler() {
		return BINDING_RESULT.andThen(DEVICE).andThen(SESSION).andThen(CONDITION).andThen(REQUEST).andThen(RESPONSE)
				.andThen(MULTIPART_FILE).andThen(MULTIPART_FILES).andThen(DEFAULT);
	}
}
