package com.board.web.entity;

import java.util.Date;

public class Comment {
	private String userId;
	private String content;
	private Date commentDate;
	private int postNumber;
	
	public Comment(String userId, String content, Date commentDate, int postNumber) {
		this.userId = userId;
		this.content = content;
		this.commentDate = commentDate;
		this.postNumber = postNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getContent() {
		return content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public int getPostNumber() {
		return postNumber;
	}
}
