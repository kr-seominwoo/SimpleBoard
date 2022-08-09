package com.board.web.controller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.web.entity.Board;
import com.board.web.entity.Post;
import com.board.web.service.jdbc.JDBCPostService;

@Controller
@RequestMapping("/post/")
public class PostController {
	@Autowired
	private JDBCPostService service;

	@GetMapping("list")
	public String list(Model model) {
		Board board = this.service.getBoard();
		model.addAttribute("board", board);
		return "/post/list";
	}
	
	@PostMapping("list")
	public String search(@RequestParam(defaultValue="")String field, @RequestParam(defaultValue="")String searchContent, Model model) {
		Board board = this.service.getBoard(field, searchContent);
		model.addAttribute("board", board);		
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
	public String edit(String title, String content, String[] hashTag, String password, int postNumber, Model model) {
		if (this.service.isYourPost(password, postNumber) == 1) {
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			model.addAttribute("hashTag", hashTag);
			model.addAttribute("postNumber", postNumber);
			return "/post/edit";
		} else {
			return "redirect:/post/detail?postNumber=" + postNumber;
		}
	}

	@PostMapping("update")
	public String update(String title, String content, String[] hashTag, int postNumber) {
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
		
		this.service.updatePost(title, content, hashTags, postNumber);		
		return "redirect:/post/detail?postNumber=" + postNumber;
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
	
	@PostMapping("deleteComment")
	public String deleteComment(String password, int commentNumber, int postNumber) {
		this.service.deleteComment(password, commentNumber);
		
		return "redirect:/post/detail?postNumber=" + postNumber;
	}
}
