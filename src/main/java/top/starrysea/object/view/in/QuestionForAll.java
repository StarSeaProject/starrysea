package top.starrysea.object.view.in;

import top.starrysea.common.Condition;
import top.starrysea.object.dto.Question;

public class QuestionForAll {
	private short questionStatus;
	private Integer page;

	public short getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(short questionStatus) {
		this.questionStatus = questionStatus;
	}

	public Condition getCondition() {
		return new Condition(page != null ? page : 1);
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Question toDTO() {
		return new Question.Builder().questionStatus(questionStatus).build();
	}

}
