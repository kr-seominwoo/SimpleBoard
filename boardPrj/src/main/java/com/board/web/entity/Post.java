package com.board.web.entity;

import java.util.Date;
import java.util.List;

public class Post {
	private String userId;
	private String title;
	private String content;
	private Date postDate;
	private Date modifyDate;
	private List<String> hashtags;
	private int like;
	private int hit;
	private int postNumber;
	private List<Comment> commentList;
	
	public Post(String userId, String title, String content, Date postDate, Date modifyDate, List<String> hashtags,
			int like, int hit, int postNumber, List<Comment> commentList) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.postDate = postDate;
		this.modifyDate = modifyDate;
		this.hashtags = hashtags;
		this.like = like;
		this.hit = hit;
		this.postNumber = postNumber;
		this.commentList = commentList;
	}

	public String getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getPostDate() {
		return postDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public int getLike() {
		return like;
	}

	public int getHit() {
		return hit;
	}

	public int getPostNumber() {
		return postNumber;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}
}
