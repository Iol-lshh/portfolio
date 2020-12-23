package main.java.com.model.board;

import main.java.com.model.DTO;

public class CommentDTO implements DTO {

	private long commentId;
	private String commentContents;
	private long writerId;
	private String writer;
	private String nickName;
	private String email;
	private String grade;
	private long postId;
	private String regdate;

	public CommentDTO() {
	}

	public CommentDTO(long commentId, String commentContents, long postId, String regdate) {
		this.commentId = commentId;
		this.commentContents = commentContents;
		this.postId = postId;
		this.regdate = regdate;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public long getWriterId() {
		return writerId;
	}

	public void setWriterId(long writerId) {
		this.writerId = writerId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", commentContents=" + commentContents + ", writerId=" + writerId
				+ ", writer=" + writer + ", nickName=" + nickName + ", email=" + email + ", grade=" + grade
				+ ", postId=" + postId + ", regdate=" + regdate + "]";
	}

}
