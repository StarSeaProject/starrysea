package top.starrtsea.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarrtseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IQuestionDao;
import top.starrysea.object.dto.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarrtseaApplication.class)
public class QuestionDaoTest {
	@Autowired
	private IQuestionDao questionDao;

	@Test
	public void getAllQuestionDaoTest() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(questionDao.getAllQuestionDao(condition, new Question.Builder().build()).getTheResult());
	}

	@Test
	public void saveQuestionDaoTest() {
		DaoResult result = questionDao.saveQuestionDao(new Question.Builder().question("kuma nv zhuang").build());
		System.out.println(result);
	}

	@Test
	public void updateQuestionDaoTest() {
		System.out.println(
				questionDao.updateQuestionDao(new Question.Builder().questionId("Q-T4mSaPgA").answer("haode").build()));
	}

	@Test
	public void getQuestionCountDaoTest() {
		System.out.println(questionDao.getQuestionCountDao(new Question.Builder().questionStatus((short) 1).build())
				.getResult(Integer.class));
	}
}
