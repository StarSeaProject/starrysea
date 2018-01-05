package top.starrysea.mail;

public class Mail {

	private String to;
	private String title;
	private String content;

	public Mail(String to, String title, String content) {
		this.to = to;
		this.title = title;
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
