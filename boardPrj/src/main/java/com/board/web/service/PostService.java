package com.board.web.service;

import java.util.List;

import com.board.web.entity.Post;
import com.board.web.entity.PostView;

public interface PostService {
	int registPost(String writerId, String title, String content, String password, String hashtags, int hashtagsCount);
	List<PostView> getPostViewList();
	Post getPost(int postNumber);
	int registComment(String writerId, String content, String password, int postNumber);
}
