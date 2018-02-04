package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;
import top.starrysea.object.view.out.QuestionForAll;

public class Question implements Entity {

	private String questionId;
	private String question;
	private long questionCreateTime;
	private long questionUpdateTime;
	private String answer;
	private short questionStatus;

	private Question(Builder builder) {
		this.questionId = builder.questionId;
		this.question = builder.question;
		this.questionCreateTime = builder.questionCreateTime;
		this.questionUpdateTime = builder.questionUpdateTime;
		this.answer = builder.answer;
		this.questionStatus = builder.questionStatus;
	}

	public static class Builder implements IBuilder<Question> {
		private String questionId;
		private String question;
		private long questionCreateTime;
		private long questionUpdateTime;
		private String answer;
		private short questionStatus;

		public Builder questionId(String questionId) {
			this.questionId = questionId;
			return this;
		}

		public Builder question(String question) {
			this.question = question;
			return this;
		}

		public Builder questionCreateTime(long questionCreateTime) {
			this.questionCreateTime = questionCreateTime;
			return this;
		}

		public Builder questionUpdateTime(long questionUpdateTime) {
			this.questionUpdateTime = questionUpdateTime;
			return this;
		}

		public Builder answer(String answer) {
			this.answer = answer;
			return this;
		}

		public Builder questionStatus(short questionStatus) {
			this.questionStatus = questionStatus;
			return this;
		}

		@Override
		public Question build() {
			return new Question(this);
		}

	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public long getQuestionCreateTime() {
		return questionCreateTime;
	}

	public void setQuestionCreateTime(long questionCreateTime) {
		this.questionCreateTime = questionCreateTime;
	}

	public long getQuestionUpdateTime() {
		return questionUpdateTime;
	}

	public void setQuestionUpdateTime(long questionUpdateTime) {
		this.questionUpdateTime = questionUpdateTime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public short getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(short questionStatus) {
		this.questionStatus = questionStatus;
	}

	public QuestionForAll toVoForAll() {
		return new QuestionForAll(questionId, question, questionCreateTime, answer, questionStatus);
	}

}
