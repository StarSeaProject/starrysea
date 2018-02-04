package top.starrysea.object.view.out;

import java.util.Date;

import top.starrysea.common.Common;

public class QuestionForAll {
	private String questionId;
	private String question;
	private String questionCreateTime;
	private String answer;
	private String questionStatus;

	public QuestionForAll(String questionId, String question, long questionCreateTime, String answer,
			short questionStatus) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.questionCreateTime = Common.time2String(new Date(questionCreateTime));
		this.answer = answer;
		String status = "";
		if (questionStatus == (short) 1) {
			status = "未回答";
		} else if (questionStatus == (short) 2) {
			status = "已回答";
		}
		this.questionStatus = status;
	}

	public String getQuestionId() {
		return questionId;
	}

	public String getQuestion() {
		return question;
	}

	public String getQuestionCreateTime() {
		return questionCreateTime;
	}

	public String getAnswer() {
		return answer;
	}

	public String getQuestionStatus() {
		return questionStatus;
	}

}
