package com.board.web.entity;

import java.util.List;

public class Board {
	private List<PostView> list;
	private int totalCommentCount;
	
	public Board(List<PostView> list, int totalCommentCount) {
		this.list = list;
		this.totalCommentCount = totalCommentCount;
	}

	public List<PostView> getList() {
		return list;
	}

	public int getTotalCommentCount() {
		return totalCommentCount;
	}
	
	public void setTotalCommentCount(int totalCommentCount) {
		this.totalCommentCount = totalCommentCount;
	}
	
	public int getTotalPostCount() {
		return this.list.size();
	}
}
