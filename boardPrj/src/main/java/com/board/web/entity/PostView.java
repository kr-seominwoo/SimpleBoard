package com.board.web.entity;

import java.util.Date;

public class PostView {
	private int postNumber;
	private String title;
	private String writerId;
	private Date postDate;
	private int commentCount;
	private int hit;
	private int like;
	
	public PostView(int postNumber, String title, String writerId, Date postDate, int commentCount, int hit, int like) {
		this.postNumber = postNumber;
		this.title = title;
		this.writerId = writerId;
		this.postDate = postDate;
		this.commentCount = commentCount;
		this.hit = hit;
		this.like = like;
	}

	public int getPostNumber() {
		return postNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getWriterId() {
		return writerId;
	}

	public Date getPostDate() {
		return postDate;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public int getHit() {
		return hit;
	}

	public int getLike() {
		return like;
	}
}
