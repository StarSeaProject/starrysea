package top.starrysea.object.view.out;

public class QuestionForAll {
	private String questionId;
	private String question;
	private long questionUpdateTime;
	private String answer;
	private short questionStatus;

	public QuestionForAll(String questionId, String question, long questionUpdateTime, String answer,
			short questionStatus) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.questionUpdateTime = questionUpdateTime;
		this.answer = answer;
		this.questionStatus = questionStatus;
	}

	public String getQuestionId() {
		return questionId;
	}

	public String getQuestion() {
		return question;
	}

	public long getQuestionUpdateTime() {
		return questionUpdateTime;
	}

	public String getAnswer() {
		return answer;
	}

	public short getQuestionStatus() {
		return questionStatus;
	}

}
