package top.starrysea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static top.starrysea.dao.impl.QuestionDaoImpl.PAGE_LIMIT;
import static top.starrysea.common.ResultKey.LIST_1;
import static top.starrysea.common.ServiceResult.SUCCESS_SERVICE_RESULT;

import java.util.List;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.common.ServiceResult;
import top.starrysea.dao.IQuestionDao;
import top.starrysea.object.dto.Question;
import top.starrysea.service.IQuestionService;

@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private IQuestionDao questionDao;

	@Override
	public ServiceResult queryAllQuestionService(Condition condition, Question question) {
		DaoResult daoResult = questionDao.getAllQuestionDao(condition, question);
		List<Question> questionsList = daoResult.getResult(List.class);
		int totalPage = 0;
		daoResult = questionDao.getQuestionCountDao(question);
		int count = daoResult.getResult(Integer.class);
		if (count % PAGE_LIMIT == 0) {
			totalPage = count / PAGE_LIMIT;
		} else {
			totalPage = (count / PAGE_LIMIT) + 1;
		}

		return ServiceResult.of(true).setResult(LIST_1, questionsList).setNowPage(condition.getPage())
				.setTotalPage(totalPage);
	}

	@Override
	public ServiceResult askQuestionService(Question question) {
		questionDao.saveQuestionDao(question);
		return SUCCESS_SERVICE_RESULT;
	}

	@Override
	public ServiceResult answerQuestionService(Question question) {
		questionDao.updateQuestionDao(question);
		return SUCCESS_SERVICE_RESULT;
	}

}
