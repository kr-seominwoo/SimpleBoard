package com.board.web.controller.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post/")
public class PostController {
	
	@RequestMapping("detail")
	public String detail() {
		return "/post/detail";
	}
	
	@RequestMapping("edit")
	public String edit() {
		return "/post/edit";
	}
	
	@RequestMapping("update")
	public String update() {
		return "redirect:/post/detail";
	}
	
	@RequestMapping("delete")
	public String delete() {
		return "redirect:/index";
	}
}
