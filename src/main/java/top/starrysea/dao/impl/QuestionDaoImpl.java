package top.starrysea.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IQuestionDao;
import top.starrysea.kql.clause.OrderByType;
import top.starrysea.kql.clause.SelectClause;
import top.starrysea.kql.clause.UpdateSetType;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.EntitySqlResult;
import top.starrysea.kql.facede.IntegerSqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.kql.facede.UpdateSqlResult;
import top.starrysea.object.dto.Question;

import static top.starrysea.common.Common.*;

@Repository("questionDao")
public class QuestionDaoImpl implements IQuestionDao {
	@Autowired
	private KumaSqlDao kumaSqlDao;
	public static final int PAGE_LIMIT = 10;

	@Override
	public DaoResult getAllQuestionDao(Condition condition, Question question) {
		kumaSqlDao.selectMode();
		ListSqlResult theresult = kumaSqlDao.select("question_id").select("question").select("question_update_time")
				.select("answer").select("question_status").from(Question.class)
				.where("question_status", WhereType.EQUALS, question.getQuestionStatus())
				.orderBy("question_update_time", OrderByType.DESC)
				.limit((condition.getPage() - 1) * PAGE_LIMIT, PAGE_LIMIT)
				.endForList((rs, row) -> new Question.Builder().questionId(rs.getString("question_id"))
						.question(rs.getString("question")).questionUpdateTime(rs.getLong("question_update_time"))
						.answer(rs.getString("answer")).questionStatus(rs.getShort("question_status")).build());
		return new DaoResult(true, theresult.getResult());
	}

	@Override
	public DaoResult saveQuestionDao(Question question) {
		kumaSqlDao.insertMode();
		question.setQuestionStatus((short) 1);
		kumaSqlDao.insert("question_id", getCharId("Q-", 10)).insert("question", question.getQuestion())
				.insert("question_create_time", System.currentTimeMillis())
				.insert("question_update_time", System.currentTimeMillis())
				.insert("question_status", question.getQuestionStatus()).table(Question.class).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult updateQuestionDao(Question question) {
		kumaSqlDao.updateMode();
		kumaSqlDao.update("question_status", UpdateSetType.ASSIGN, 2)
				.update("answer", UpdateSetType.ASSIGN, question.getAnswer())
				.update("question_update_time", UpdateSetType.ASSIGN, System.currentTimeMillis())
				.where("question_id", WhereType.EQUALS, question.getQuestionId()).table(Question.class).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult getQuestionCountDao(Question question) {
		kumaSqlDao.selectMode();
		IntegerSqlResult theResult = kumaSqlDao.select(SelectClause.COUNT).from(Question.class)
				.where("question_status", WhereType.EQUALS, question.getQuestionId()).endForNumber();
		return new DaoResult(true, theResult.getResult());
	}

}
