package com.board.web.service;

import java.util.Date;
import java.util.List;

import com.board.web.entity.Post;
import com.board.web.entity.PostView;

public interface PostService {
	int registPost(String writerId, String title, String content, String password, String hashtags);
	List<PostView> getPostViewList();
	Post getPost(int postNumber);
	int deletePost(String password, int postNumber);
	int registComment(String writerId, String content, String password, int postNumber);
	int deleteComment(String password, int commentNumber);
}
