package com.board.web.service;

import java.util.List;

import com.board.web.entity.Board;
import com.board.web.entity.Post;
import com.board.web.entity.PostView;

public interface PostService {
	int registPost(String writerId, String title, String content, String password, String[] hashTag);
	Board getBoard();
	Board getBoard(String field, String searchContent);
	Post getPost(int postNumber);
	int deletePost(String password, int postNumber);
	int registComment(String writerId, String content, String password, int postNumber);
	int deleteComment(String password, int commentNumber);
	int updatePost(String title, String content, String[] hashTag, int postNumber);
	int isYourPost(String password, int postNumber);
}
