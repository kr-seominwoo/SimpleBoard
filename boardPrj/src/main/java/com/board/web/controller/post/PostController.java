package com.board.web.controller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.web.entity.Post;
import com.board.web.entity.PostView;
import com.board.web.service.jdbc.JDBCPostService;

@Controller
@RequestMapping("/post/")
public class PostController {
	@Autowired
	private JDBCPostService service;

	@GetMapping("list")
	public String list(Model model) {
		List<PostView> list = service.getPostViewList();
		model.addAttribute("list", list);
		return "/post/list";
	}

	@GetMapping("write")
	public String write() {
		return "/post/write";
	}

	@PostMapping("regist")
	public String regist(String writerId, String title, String content, String password, String[] hashTag) {
		String hashTags = "";
		if (hashTag != null) {
			StringBuilder builder = new StringBuilder();
			for (int idx = 0; idx < hashTag.length; ++idx) {
				builder.append(hashTag[idx]);

				if (idx != hashTag.length - 1) {
					builder.append(",");
				}
			}

			hashTags = builder.toString();
		}

		this.service.registPost(writerId, title, content, password, hashTags);
		return "redirect:/post/list";
	}

	@GetMapping("detail")
	public String detail(int postNumber, Model model) {
		Post post = this.service.getPost(postNumber);
		model.addAttribute("post", post);
		return "/post/detail";
	}

	@PostMapping("edit")
	public String edit(String password, int postNumber) {
		
		return "/post/edit";
	}

	@PostMapping("update")
	public String update(String title, String content, String[] hashTag) {
		return "redirect:/post/detail";
	}

	@PostMapping("delete")
	public String delete(String password, int postNumber) {
		this.service.deletePost(password, postNumber);
		return "redirect:/post/list";
	}
	
	@PostMapping("registComment")
	public String registComment(String writerId, String content, String password, int postNumber) {
		this.service.registComment(writerId, content, password, postNumber);
		
		return "redirect:/post/detail?postNumber=" + postNumber;
	}
}
