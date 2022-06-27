package main.java.com.model.board;

public class CommentDeclarationJSON {
	private long declarationCommentId;
	private String declarationDate;
	private String declarationNickname;
	private String declarationTitle;
	private String declarationContent;

	public long getDeclarationCommentId() {
		return declarationCommentId;
	}

	public void setDeclarationCommentId(long declarationCommentId) {
		this.declarationCommentId = declarationCommentId;
	}

	public String getDeclarationDate() {
		return declarationDate;
	}

	public void setDeclarationDate(String declarationDate) {
		this.declarationDate = declarationDate;
	}

	public String getDeclarationNickname() {
		return declarationNickname;
	}

	public void setDeclarationNickname(String declarationNickname) {
		this.declarationNickname = declarationNickname;
	}

	public String getDeclarationTitle() {
		return declarationTitle;
	}

	public void setDeclarationTitle(String declarationTitle) {
		this.declarationTitle = declarationTitle;
	}

	public String getDeclarationContent() {
		return declarationContent;
	}

	public void setDeclarationContent(String declarationContent) {
		this.declarationContent = declarationContent;
	}

	@Override
	public String toString() {
		return "CommentDeclarationJSONModel [declarationCommentId=" + declarationCommentId + ", declarationDate="
				+ declarationDate + ", declarationNickname=" + declarationNickname + ", declarationTitle="
				+ declarationTitle + ", declarationContent=" + declarationContent + "]";
	}

}
