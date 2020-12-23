package main.java.com.model.board;

public class CommentJSONModel {
	private String comment;
	private String userId;
	private String postId;
	private String page;
	private String commentId;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@Override
	public String toString() {
		return "CommentInsertModel [comment=" + comment + ", userId=" + userId + ", postId=" + postId + ", page=" + page
				+ ", commentId=" + commentId + "]";
	}

}
