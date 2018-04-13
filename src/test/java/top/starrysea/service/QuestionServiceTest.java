package top.starrysea.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.StarryseaApplication;
import top.starrysea.common.Condition;
import top.starrysea.object.dto.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarryseaApplication.class)
public class QuestionServiceTest {
	@Autowired
	private IQuestionService questionService;

	@Test
	public void queryAllQuestionServiceTest() {
		Condition condition = new Condition();
		condition.setPage(1);
		System.out.println(questionService.queryAllQuestionService(condition,
				new Question.Builder().questionStatus((short) 2).build()));
	}

	@Test
	public void askQuestionServiceTest() {
		System.out.println(
				questionService.askQuestionService(new Question.Builder().question("zuzhangnvzhuang").build()));
	}

	@Test
	public void answerQuestionServiceTest() {
		System.out.println(questionService
				.answerQuestionService(new Question.Builder().answer("haode").questionId("Q-0T2d4Wc1").build()));
	}
}
