package top.starrysea.service;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.object.dto.Question;

public interface IQuestionService {

	ServiceResult queryAllQuestionService(Condition condition,Question question);
	
	ServiceResult askQuestionService(Question question);
	
	ServiceResult answerQuestionService(Question question);
	
}
