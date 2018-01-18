package top.starrysea.dao;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.object.dto.Question;

public interface IQuestionDao {

	DaoResult getAllQuestionDao(Condition condition, Question question);

	DaoResult saveQuestionDao(Question question);

	DaoResult updateQuestionDao(Question question);
}
