package top.starrysea.object.view.in;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.object.dto.Question;

public class QuestionForAsk {
	@NotEmpty(message="问题或意见不能为空")
	@Length(max=150,message="质问长度不能超过150")
	private String question;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	public Question toDTO(){
		return new Question.Builder().question(question).build();
	}
}
